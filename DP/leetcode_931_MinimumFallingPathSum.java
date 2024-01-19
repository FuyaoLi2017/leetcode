/**
 * Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.

A falling path starts at any element in the first row and chooses the element in the next row that is either directly below or diagonally left/right. 
Specifically, the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).
 */

 // My solution - in place DP, actually more clean
 class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        System.out.println(n);
        if (n == 1) {
            return matrix[0][0];
        }
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int minPrev = Integer.MAX_VALUE;
                for (int k = Math.max(0, j-1); k <= Math.min(n-1, j+1); k++) {
                    minPrev = Math.min(minPrev, matrix[i-1][k]);
                }
                matrix[i][j] = matrix[i][j] + minPrev;
                if (i == n - 1) {
                    res = Math.min(res, matrix[i][j]);
                }
            }
        }
        return res;
    }
}

// Editorial top down DP
class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int minFallingSum = Integer.MAX_VALUE;
        Integer memo[][] = new Integer[matrix.length][matrix[0].length];

        // start a DFS (with memoization) from each cell in the top row
        for (int startCol = 0; startCol < matrix.length; startCol++) {
            minFallingSum = Math.min(minFallingSum,
                findMinFallingPathSum(matrix, 0, startCol, memo));
        }
        return minFallingSum;
    }

    public int findMinFallingPathSum(int[][] matrix, int row, int col, Integer[][] memo) {
        //base cases
        if (col < 0 || col == matrix.length) {
            return Integer.MAX_VALUE;
        }
        //check if we have reached the last row
        if (row == matrix.length - 1) {
            return matrix[row][col];
        }
        //check if the results are calculated before
        if (memo[row][col] != null) {
            return memo[row][col];
        }

        // calculate the minimum falling path sum starting from each possible next step
        int left = findMinFallingPathSum(matrix, row + 1, col, memo);
        int middle = findMinFallingPathSum(matrix, row + 1, col + 1, memo);
        int right = findMinFallingPathSum(matrix, row + 1, col - 1, memo);

        memo[row][col] = Math.min(left, Math.min(middle, right)) + matrix[row][col];
        return memo[row][col];
    }
}

// bottom up editorial DP
class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int dp[][] = new int[matrix.length + 1][matrix.length + 1];
        for (int row = matrix.length - 1; row >= 0; row--) {
            for (int col = 0; col < matrix.length; col++) {
                if (col == 0) {
                    dp[row][col] =
                        Math.min(dp[row + 1][col], dp[row + 1][col + 1]) + matrix[row][col];
                } else if (col == matrix.length - 1) {
                    dp[row][col] =
                        Math.min(dp[row + 1][col], dp[row + 1][col - 1]) + matrix[row][col];
                } else {
                    dp[row][col] = Math.min(dp[row + 1][col],
                        Math.min(dp[row + 1][col + 1], dp[row + 1][col - 1])) + matrix[row][col];
                }
            }
        }
        int minFallingSum = Integer.MAX_VALUE;
        for (int startCol = 0; startCol < matrix.length; startCol++) {
            minFallingSum = Math.min(minFallingSum, dp[0][startCol]);
        }
        return minFallingSum;
    }
}