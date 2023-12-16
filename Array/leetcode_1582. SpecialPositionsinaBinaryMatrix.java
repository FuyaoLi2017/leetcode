/**
 * Given an m x n binary matrix mat, return the number of special positions in mat.

A position (i, j) is called special if mat[i][j] == 1 and all other elements in row i and column j are 0 (rows and columns are 0-indexed).
Input: mat = [[1,0,0],[0,0,1],[1,0,0]]
Output: 1
Explanation: (1, 2) is a special position because mat[1][2] == 1 and all other elements in row 1 and column 2 are 0.

 */

// flat down the number to the left/right and get the result.
class Solution {
    public int numSpecial(int[][] mat) {
        int[] length = new int[mat.length];
        int[] width = new int[mat[0].length];

        int res = 0;

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 1) {
                    length[i]++;
                    width[j]++;
                }
            }
        }

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 1 && length[i] == 1 && width[j] == 1) {
                    res += 1;
                }
            }
        }

        return res;
    }
}