/*
In MATLAB, there is a very useful function called 'reshape', which can reshape a matrix into a new one with different size but keep its original data.

You're given a matrix represented by a two-dimensional array, and two positive integers r and c representing the row number and column number of the wanted reshaped matrix, respectively.

The reshaped matrix need to be filled with all the elements of the original matrix in the same row-traversing order as they were.

If the 'reshape' operation with given parameters is possible and legal, output the new reshaped matrix; Otherwise, output the original matrix.
*/

class Solution {
    public int[][] matrixReshape(int[][] nums, int r, int c) {

        int row = nums.length;
        int col = nums[0].length;

        if(row*col != r*c) return nums;

        int[][] result = new int[r][c];

        int index = 0;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                int current = nums[i][j];
                int nr = index / c;
                int nc = index % c;
                result[nr][nc] = current;
                index++;
            }
        }
        return result;
    }
}
