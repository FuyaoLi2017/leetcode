/*
Determine the largest square of 1s in a binary matrix (a binary matrix only contains 0 and 1), return the length of the largest square.

Assumptions

The given matrix is not null and guaranteed to be of size N * N, N >= 0
Examples

{ {0, 0, 0, 0},

  {1, 1, 1, 1},

  {0, 1, 1, 1},

  {1, 0, 1, 1}}

the largest square of 1s has length of 2
*/
// 2D DP
// also can solve rectangle shape
public class Solution {
  public int largest(int[][] matrix) {
    int[][] map = new int[matrix.length][matrix[0].length];
    int globalMax = 0;
    for (int i = 0; i < matrix.length; i++) {
      map[i][0] = matrix[i][0] == 1 ? 1 : 0;
      globalMax = Math.max(map[i][0], globalMax);
    }

    for (int j = 0; j < matrix[0].length; j++) {
      map[0][j] = matrix[0][j] == 1 ? 1 : 0;
      globalMax = Math.max(map[0][j], globalMax);
    }


    for (int i = 1; i < matrix.length; i++) {
      for (int j = 1; j < matrix[0].length; j++) {
        // find the value map[i - 1][j - 1],map[i - 1][j],map[i][j - 1]
        // and then process it
        if (matrix[i][j] == 1) {
          map[i][j] = Math.min(map[i - 1][j - 1], map[i - 1][j]);
          map[i][j] = Math.min(map[i][j], map[i][j - 1]) + 1;
        } else {
          map[i][j] = 0;
        }
        globalMax = Math.max(globalMax, map[i][j]);
      }
    }
    return globalMax;
  }
}

// laicode answer, a more concise version
public class Solution {
  public int largest(int[][] matrix) {
    int N = matrix.length;
    if (N == 0) {
      return 0;
    }
    int result = 0;
    int[][] largest = new int[N][N];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (i == 0 || j == 0) {
          largest[i][j] = matrix[i][j] == 1 ? 1 : 0;
        } else if (matrix[i][j] == 1) {
          largest[i][j] = Math.min(largest[i][j - 1] + 1,largest[i - 1][j] + 1);
          largest[i][j] = Math.min(largest[i - 1][j - 1] + 1, largest[i][j]);
        }
        result = Math.max(result, largest[i][j]);
      }
    }
    return result;
  }
}
