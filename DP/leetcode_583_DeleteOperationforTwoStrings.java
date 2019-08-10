/*
Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same, where in each step you can delete one character in either string.

Example 1:
Input: "sea", "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
Note:
The length of given words won't exceed 500.
Characters in given words can only be lower-case letters.
*/
// DP solution
/*
We make use of a 2-D dpdp, in which dp[i][j]dp[i][j] represents the length of the longest common subsequence among the strings s1s1 and s2s2 considering their lengths upto (i-1)^{th}(i−1)th
index and (j-1)^{th}(j−1) th
index only respectively. We fill the dpdp array in row-by-row order.
*/

// https://leetcode.com/problems/delete-operation-for-two-strings/solution/

// DP solution 1: Using Longest Common Subsequence- Dynamic Programming
// We make use of a 2-D dpdp, in which dp[i][j] represents the length of the longest
// common subsequence among the strings s1s1 and s2s2 considering their lengths
// upto (i-1)th index and (j-1)th index only respectively. We fill the dpdp array in row-by-row order.
// 这个思路我想到了，但是corner case处理的不够完美，出了一些问题
class Solution {
    public int minDistance(String word1, String word2) {
        // use extra roll
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++) {
            for (int j = 0; j <= word2.length(); j++) {
                if (i == 0 || j == 0)
                    continue;
                if (word1.charAt(i-1) == word2.charAt(j-1))
                    dp[i][j] = 1 + dp[i-1][j-1];
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return word1.length() + word2.length() - 2 * dp[word1.length()][word2.length()];
    }
}

// DP solution 2: Without using LCS Dynamic Programmming
public class Solution {
    public int minDistance(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = i + j;
                else if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[s1.length()][s2.length()];
    }
}
