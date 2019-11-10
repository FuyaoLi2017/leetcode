/*
Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

Example:

Input:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4
*/
// a more concise solution
public class Solution {
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[][] dp = new int[rows + 1][cols + 1];
        int maxsqlen = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i-1][j-1] == '1'){
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[i][j]);
                }
            }
        }
        return maxsqlen * maxsqlen;
    }
}

// my a little bit slower solution
class Solution {
    public int maximalSquare(char[][] matrix) {
        int maxSquare = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int row = 0; row < matrix.length; row++) {
            dp[row][0] = matrix[row][0] - '0';
            maxSquare = Math.max(dp[row][0], maxSquare);
        }
        for (int col = 0; col < matrix[0].length; col++) {
            dp[0][col] = matrix[0][col]- '0';
            maxSquare = Math.max(dp[0][col], maxSquare);
        }
        for (int row = 1; row < dp.length; row++) {
            for (int col = 1; col < dp[0].length; col++) {
                if (matrix[row][col] == '0') dp[row][col] = 0;
                else {
                    int temp = Math.min(dp[row-1][col-1], Math.min(dp[row][col-1], dp[row-1][col]));
                    dp[row][col] = temp + 1;
                    maxSquare = Math.max(maxSquare, dp[row][col]);
                }
            }
        }
        return maxSquare * maxSquare;
    }
}


// 如果问一共有多少个小正方形
再把matrix所有数字加起来就可以，因为是相当于每层剥去一个1，这样正好就是dp的值，加起来就正好是对的
