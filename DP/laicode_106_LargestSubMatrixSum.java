/*
Given a matrix that contains integers, find the submatrix with the largest sum.

Return the sum of the submatrix.

Assumptions

The given matrix is not null and has size of M * N, where M >= 1 and N >= 1
Examples

{ {1, -2, -1, 4},

  {1, -1,  1, 1},

  {0, -1, -1, 1},

  {0,  0,  1, 1} }

the largest submatrix sum is (-1) + 4 + 1 + 1 + (-1) + 1 + 1 + 1 = 7.
*/
public class Solution {
  public int largest(int[][] matrix) {
    int N = matrix.length;
    int M = matrix[0].length;
    int result = Integer.MIN_VALUE;
    for (int i = 0; i < N; i++) {
      int[] cur = new int[M];
      for (int j = i; j < N; j++) {
        add(cur, matrix[j]);
        result = Math.max(result, max(cur));
      }
    }
    return result;
  }

  private void add(int[] cur, int[] add) {
    for (int i = 0; i < cur.length; i++) {
      cur[i] += add[i];
    }
  }

  // largest subarray sum
  private int max(int[] cur) {
    int result = cur[0];
    int tmp = cur[0];
    for (int i = 1; i < cur.length; i++) {
      tmp = Math.max(tmp + cur[i], cur[i]);
      result = Math.max(result, tmp);
    }
    return result;
  }
}
