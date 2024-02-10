/**
 * Given a string s, return the number of palindromic substrings in it.

A string is a palindrome when it reads the same backward as forward.

A substring is a contiguous sequence of characters within the string.
 */

// no DP solution, expand from center also works well
public class Solution {
    int count = 0;

    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;

        for (int i = 0; i < s.length(); i++) { // i is the mid point
            extendPalindrome(s, i, i); // odd length;
            extendPalindrome(s, i, i + 1); // even length
        }

        return count;
    }

    private void extendPalindrome(String s, int left, int right) {
        while (left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++; left--; right++;
        }
    }
}

// DP
public int countSubstrings(String s) {
    int n = s.length();
    int res = 0;
    boolean[][] dp = new boolean[n][n];
    for (int i = n - 1; i >= 0; i--) {
        for (int j = i; j < n; j++) {
            dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);
            if(dp[i][j]) ++res;
        }
    }
    return res;
}

// the same method like the above solution, more clear but more redundant
public class Solution {
    public int countSubstrings(String s) {

        int sLen = s.length();
        char[] cArr = s.toCharArray();

        int totalPallindromes = 0;

        boolean[][] dp = new boolean[sLen][sLen];

        // Single length pallindroms
        for (int i = 0; i < sLen; i++) {
            dp[i][i] = true;
            totalPallindromes++;
        }

        // 2 length pallindromes
        for (int i = 0; i < sLen - 1; i++) {
            if (cArr[i] == cArr[i + 1]) {
                dp[i][i + 1] = true;
                totalPallindromes++;
            }
        }

        // Lengths > 3

        for (int subLen = 2; subLen < sLen; subLen++) {

            for (int i = 0; i < sLen - subLen; i++) {

                int j = i + subLen;

                if (dp[i + 1][j - 1] && cArr[i] == cArr[j]) {
                    dp[i][j] = true;
                    totalPallindromes++;
                }
            }
        }
        return totalPallindromes;

    }
}


// my solution 02/10/2024
class Solution {
    public int countSubstrings(String s) {
        int n = s.length();
        boolean[][] isP = new boolean[n][n];

        // i is end index, j is start index
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    isP[i][j]  = true;
                }
                else if  (j > i) continue;
                else {
                    char start = s.charAt(j);
                    char end = s.charAt(i);
                    if (j + 1 == i) {
                        if (start == end) {
                            isP[i][j]  = true;
                        } else {
                            continue;
                        }
                        
                    }
                    if (start == end && isP[i-1][j+1]) {
                        isP[i][j]  = true;
                    }
                }   
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j > i) continue;
                else if (isP[i][j]) {
                    count++;
                }
            }
        }

        return count;
    }
}