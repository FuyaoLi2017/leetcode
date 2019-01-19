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

// my Solution
class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        // use a count to traverse from 1 to m + n - 1
        // count is odd : start from left (where column is small)
        // count is even: start from right (where column is large)
        // leftBound: count <= n ? 0 : counter - n
        // rightBound: count <= m ? counter - 1 : m - 1
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return new int[]{};
        int m = matrix.length;
        int n = matrix[0].length;
        int[] res = new int[m * n];
        int index = 0;
        for (int count = 1; count <= m + n - 1; count++) {
            // the sum of row and column index
            int sumIndex = count - 1;
            int leftBound = count <= n ? 0 : count - n;
            int rightBound = count <= m ? count - 1 : m - 1;
            if (count % 2 == 0) {
                // start from left
                for (int j = leftBound; j <= rightBound; j++) {
                    res[index++] = matrix[j][sumIndex - j];
                }
            } else {
                // start from right
                for (int j = rightBound; j >= leftBound; j--) {
                    res[index++] = matrix[j][sumIndex - j];
                }
            }
        }
        return res;
    }
}
