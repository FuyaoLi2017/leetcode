/*
Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it. If no such solution, return -1.

For example, with A = "abcd" and B = "cdabcdab".

Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; and B is not a substring of A repeated two times ("abcdabcd").

Note:
The length of A and B will be between 1 and 10000.
*/
// ad hoc Solution
// indexOf(): Returns the index within this string of the first occurrence of the specified substring.
// check the least lenth which is not shorter than string B and add q once more
class Solution {
    public int repeatedStringMatch(String A, String B) {
        int q = 1;
        StringBuilder S = new StringBuilder(A);
        for (; S.length() < B.length(); q++) S.append(A);
        if (S.indexOf(B) >= 0) return q;
        if (S.append(A).indexOf(B) >= 0) return q+1;
        return -1;
    }
}
// Time Complexity: O(N*(N+M))O(N∗(N+M)), where M, NM,N are the lengths of strings A, B. We create two strings A * q, A * (q+1) which have length at most O(M+N).
// When checking whether B is a substring of A, this check takes naively the product of their lengths.
// Space complexity: As justified above, we created strings that used O(M+N)O(M+N) space.



// Rabin-Karp (Rolling Hash)
// https://leetcode.com/problems/repeated-string-match/solution/
import java.math.BigInteger;

class Solution {
    public boolean check(int index, String A, String B) {
        for (int i = 0; i < B.length(); i++) {
            if (A.charAt((i + index) % A.length()) != B.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    public int repeatedStringMatch(String A, String B) {
        int q = (B.length() - 1) / A.length() + 1;
        int p = 113, MOD = 1_000_000_007;
        int pInv = BigInteger.valueOf(p).modInverse(BigInteger.valueOf(MOD)).intValue();

        long bHash = 0, power = 1;
        for (int i = 0; i < B.length(); i++) {
            bHash += power * B.codePointAt(i);
            bHash %= MOD;
            power = (power * p) % MOD;
        }

        long aHash = 0; power = 1;
        for (int i = 0; i < B.length(); i++) {
            aHash += power * A.codePointAt(i % A.length());
            aHash %= MOD;
            power = (power * p) % MOD;
        }

        if (aHash == bHash && check(0, A, B)) return q;
        power = (power * pInv) % MOD;

        for (int i = B.length(); i < (q + 1) * A.length(); i++) {
            aHash -= A.codePointAt((i - B.length()) % A.length());
            aHash *= pInv;
            aHash += power * A.codePointAt(i % A.length());
            aHash %= MOD;
            if (aHash == bHash && check(i - B.length() + 1, A, B)) {
                return i < q * A.length() ? q : q + 1;
            }
        }
        return -1;
    }
}

//KMP
// https://en.wikipedia.org/wiki/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm
// https://leetcode.com/problems/repeated-string-match/discuss/108084/C++-4-lines-O(m-*-n)-or-O(1)-and-KMP-O(m-+-n)-or-O(n)
class Solution {
public:
    int repeatedStringMatch(string a, string b)
    {
        vector<int> kmp(b.size()+1);
        for (int i = 1, j = 0; i < b.size();)
        {

            //printf("j%c, i%c\n",b[j],b[i]);
            if(b[j] == b[i])
            {
                kmp[i++] = ++j;
            }
            else
            {
                if(j == 0)
                    i++;
                else
                    j = kmp[j -1];
            }
        }

        for(int i : kmp)
        {
            printf("%d",i);
        }
        //printf("\n");
        for (auto i = 0, j = 0; i < a.size(); i ++, j = kmp[j-1])
        {

            while (j < b.size() && a[(i + j) % a.size()] == b[j])
            {
                printf("match i%d j%d\n",i,j);
                ++j;
            }
            if (j == b.size()) return ceil((float)(i + j) / a.size());
            else
                printf("unmatch i%d j%d\n",i,j);
        }

        return -1;
    }
};
