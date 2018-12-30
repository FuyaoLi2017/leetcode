/*
Given a 2D integer matrix M representing the gray scale of an image, you need to design a smoother to make the gray scale of each cell becomes the average gray scale (rounding down) of all the 8 surrounding cells and itself. If a cell has less than 8 surrounding cells, then use as many as you can.

Example 1:
Input:
[[1,1,1],
 [1,0,1],
 [1,1,1]]
Output:
[[0, 0, 0],
 [0, 0, 0],
 [0, 0, 0]]
Explanation:
For the point (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
For the point (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
For the point (1,1): floor(8/9) = floor(0.88888889) = 0
Note:
The value in the given matrix is in the range of [0, 255].
The length and width of the given matrix are in the range of [1, 150].
*/
class Solution {
    public int[][] imageSmoother(int[][] M) {
        // use bit manipulation, we can only set the bit to 1 when all the number around it is zero
        int R = M.length;
        int C = M[0].length;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                M[i][j] += setValue(M, i, j, R, C) << 8;
            }
        }
        // right shift one bit
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                M[i][j] >>= 8;
            }
        }
        return M;
    }

    private int setValue(int[][] M, int x, int y, int R, int C) {
        int count = 0;
        int xMin = Math.max(0, x - 1);
        int xMax = Math.min(x + 1, R - 1);
        int yMin = Math.max(0, y - 1);
        int yMax = Math.min(y + 1, C - 1);
        for (int i = xMin; i <= xMax; i++) {     // 注意边界的等号
            for (int j = yMin; j <= yMax; j++) {
                count += M[i][j] & 0xFF;
            }
        }
        return count / ((xMax - xMin + 1) * (yMax - yMin + 1));
    }
}
