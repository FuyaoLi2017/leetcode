/*
Given a 2D matrix that contains integers only, which each row is sorted in ascending order and each column is also sorted in ascending order.

Given a target number, returning the position that the target locates within the matrix. If the target number does not exist in the matrix, return {-1, -1}.

Assumptions:

The given matrix is not null.
Examples:

matrix = { {1, 2, 3}, {2, 4, 5}, {6, 8, 10} }

target = 5, return {1, 2}

target = 7, return {-1, -1}
*/

// initialize to the top right corner
public class Solution {
  public int[] search(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return new int[]{-1, -1};
    }
    int m = matrix.length;
    int n = matrix[0].length;
    int i = 0, j = n - 1;
    while (i < m && j >= 0) {
      if (matrix[i][j] == target) {
        return new int[]{i, j};
      } else if (matrix[i][j] > target) {
        j--;
      } else {
        i++;
      }
    }
    return new int[]{-1, -1};
  }
}
