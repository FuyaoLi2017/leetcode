/*
Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.

If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple such minimum-length windows, return the one with the left-most starting index.

Example 1:

Input:
S = "abcdebdde", T = "bde"
Output: "bcde"
Explanation:
"bcde" is the answer because it occurs before "bdde" which has the same length.
"deb" is not a smaller window because the elements of T in the window must occur in order.


Note:

All the strings in the input will only contain lowercase letters.
The length of S will be in the range [1, 20000].
The length of T will be in the range [1, 100].
*/

// https://leetcode.com/problems/minimum-window-subsequence/discuss/109362/Java-Super-Easy-DP-Solution-(O(mn))
class Solution {
    public String minWindow(String S, String T) {
        int m = T.length(), n = S.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int j = 0; j <= n; j++) {
            // cancel out previous element, start matching from a j+1 in T.
            dp[0][j] = j + 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (T.charAt(i - 1) == S.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        int start = 0, len = n + 1;
        for (int j = 1; j <= n; j++) {
            if (dp[m][j] != 0) {
                if (j - dp[m][j] + 1 < len) {
                    start = dp[m][j] - 1;
                    len = j - dp[m][j] + 1;
                }
            }
        }
        return len == n + 1 ? "" : S.substring(start, start + len);
    }
}


// a two pointer solution
// https://leetcode.com/problems/minimum-window-subsequence/discuss/109356/JAVA-two-pointer-solution-(12ms-beat-100)-with-explaination
/*.*/
public String minWindow(String S, String T) {
    if (S.length() == 0 || T.length() == 0) {
        return "";
    }

    /**
     * we can conduct two steps by using two pointers for this probelm:
     * 1. check feasibility from left to right
     * 2. check optimization from right to left
     * we can traverse from left to right, find a possible candidate until reach the first ending character of T
     * eg: for the string s = abcdebdde and t = bde, we should traverse s string until we find first e,
     * i.e. abcde, then traverse back from current "e" to find if we have other combination of bde with smaller
     * length.
     * @param right: fast pointer that always points the last character of T in S
     * @param left: slow pointer that used to traverse back when right pointer find the last character of T in S
     * @param tIndex: third pointer used to scan string T
     * @param minLen: current minimum length of subsequence
     * */
    int right = 0;
    int minLen = Integer.MAX_VALUE;
    String result = "";

    while (right < S.length()) {
        int tIndex = 0;
        // use fast pointer to find the last character of T in S
        while (right < S.length()) {
            if (S.charAt(right) == T.charAt(tIndex)) {
                tIndex++;
            }
            if (tIndex == T.length()) {
                break;
            }
            right++;
        }

        // if right pointer is over than boundary
        if (right == S.length()) {
            break;
        }

        // use another slow pointer to traverse from right to left until find first character of T in S
        int left = right;
        tIndex = T.length() - 1;
        while (left >= 0) {
            if (S.charAt(left) == T.charAt(tIndex)) {
                tIndex--;
            }
            if (tIndex < 0) {
                break;
            }
            left--;
        }
        // if we found another subsequence with smaller length, update result
        if (right - left + 1 < minLen) {
            minLen = right - left + 1;
            result = S.substring(left, right + 1);
        }
        // WARNING: we have to move right pointer to the next position of left pointer, NOT the next position
        // of right pointer
        right = left + 1;
    }
    return result;
}
