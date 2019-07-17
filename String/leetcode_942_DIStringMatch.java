/*
Given a string S that only contains "I" (increase) or "D" (decrease), let N = S.length.

Return any permutation A of [0, 1, ..., N] such that for all i = 0, ..., N-1:

If S[i] == "I", then A[i] < A[i+1]
If S[i] == "D", then A[i] > A[i+1]


Example 1:

Input: "IDID"
Output: [0,4,1,3,2]
Example 2:

Input: "III"
Output: [0,1,2,3]
Example 3:

Input: "DDI"
Output: [3,2,0,1]


Note:

1 <= S.length <= 10000
S only contains characters "I" or "D".
*/
// my solution
class Solution {
    public int[] diStringMatch(String S) {
        int len = S.length();
        int[] res = new int[len + 1];
        for (int i = 0; i <= len; i++) {
            res[i] = i;
        }

        // reverse consecutive decrease
        int end = 0;
        while (end < len) {
            if (S.charAt(end) == 'I') {
                end++;
            } else {
                int start = end;
                int count = 0;
                while (start + count < len && S.charAt(start + count) == 'D') {
                    count++;
                }
                // reverse one more position afterwards
                reverse(res, start, start + count);
                end += count;
            }
        }
        return res;
    }

    private void reverse(int[] res, int start, int end) {
        while (start < end) {
            int temp = res[start];
            res[start] = res[end];
            res[end] = temp;
            start++;
            end--;
        }
    }
}


// a very advanced solution
public int[] diStringMatch(String S) {
    int n = S.length(), left = 0, right = n;
    int[] res = new int[n + 1];
    for (int i = 0; i < n; ++i)
        res[i] = S.charAt(i) == 'I' ? left++ : right--;
    res[n] = left;
    return res;
}
