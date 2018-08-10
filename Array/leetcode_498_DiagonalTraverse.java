// https://leetcode.com/problems/diagonal-traverse/discuss/97787/Highly-Intuitive-Java-Solution
class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
    if(matrix.length == 0)
        return new int[0];

    int result[] = new int[matrix.length * matrix[0].length];
    int curRow = 0;
    int curCol = 0;
    int index = 0;
    boolean isUp = true;
    for(int i = 0; i < matrix.length + matrix[0].length; i++) {
        if(isUp) {
            while(curRow >= 0 && curCol < matrix[0].length) {
                result[index++] = matrix[curRow--][curCol++];
            }
            if(curCol == matrix[0].length)
                curCol = matrix[0].length - 1;
            curRow = i + 1 - curCol;
            isUp = !isUp;
        }
        // change the direction of the array
        else {
            while(curRow < matrix.length && curCol >= 0) {
                result[index++] = matrix[curRow++][curCol--];
            }
            if(curRow == matrix.length)
                curRow = matrix.length - 1;
            curCol = i + 1 - curRow;
            isUp = !isUp;
        }
    }
    return result;
}
}
