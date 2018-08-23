class Solution {
    public void setZeroes(int[][] matrix) {
        //use first row and first column as a reminder to set zeros of the matrix.
        //use boolean value to represent the whether the first row or the first
        //row or first column should be all zeros.
        boolean firstrow =false, firstcolumn = false;
        //traverse all the numbers in the matrix and set the number in the
        //corresponding row or column to be zero
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == 0){
                    if(i == 0) firstrow = true;
                    if(j == 0) firstcolumn = true;
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        //set all the rows and columns to the request state exception for the first
        //row and first column
        for(int i = 1; i < matrix.length; i++){
            for(int j = 1; j < matrix[0].length; j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }
        //set the first row to be all zeros if needed
        if(firstrow){
            for(int j = 0; j < matrix[0].length; j++){
                matrix[0][j] = 0;
            }
        }
        //set the first column to be all zeros if needed
        if(firstcolumn){
            for(int i = 0; i < matrix.length; i++){
                matrix[i][0] = 0;
            }
        }
    }
}
