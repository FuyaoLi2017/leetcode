/*
You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Note:

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

Example 1:

Given input matrix =
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

rotate the input matrix in-place such that it becomes:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
Example 2:

Given input matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
],

rotate the input matrix in-place such that it becomes:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]
*/
// general method to rotate image
/*
 * clockwise rotate
 * first reverse up to down, then swap the symmetry
 * 1 2 3     7 8 9     7 4 1
 * 4 5 6  => 4 5 6  => 8 5 2
 * 7 8 9     1 2 3     9 6 3
*/

// my new solution, beat 100% 
class Solution {
    public void rotate(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        // rotate the row
        for(int i = 0; i < matrix.length/2; i++){
            int[] temp = Arrays.copyOf(matrix[i], matrix[0].length);
            matrix[i] = Arrays.copyOfRange(matrix[matrix.length-i-1], 0, matrix[0].length);
            matrix[matrix.length-i-1] = Arrays.copyOfRange(temp, 0, matrix[0].length);
        }
        // rotate the diagonal
        for(int i = 0;  i < matrix.length; i++){
            for(int j = i+1; j < matrix[0].length; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}

// My solution
// copy array is very slow, don't use this!!!
class Solution {
    public void rotate(int[][] matrix) {
        // swap the row sequence
        for (int i = 0; i < matrix.length / 2; i++) {
            int temp[] = matrix[i];
            matrix[i] = matrix[matrix.length - i - 1];
            matrix[matrix.length - i - 1] = temp;
        }
        // swap symmetry
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[0].length; j++) {
                System.out.println(i + " " + j);
                swap(matrix, i, j);
            }
        }
    }

    private void swap(int[][] matrix, int i, int j) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = temp;
    }
}
// high vote answer, just swap it directly
class Solution {
    public void rotate(int[][] matrix) {
        // swap the row sequence
        for (int i = 0; i < matrix.length / 2; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[matrix.length - i - 1][j];
                matrix[matrix.length - i - 1][j] = temp;
            }
        }
        // swap symmetry
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[0].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}

//C++
void rotate(vector<vector<int> > &matrix) {
    reverse(matrix.begin(), matrix.end());
    for (int i = 0; i < matrix.size(); ++i) {
        for (int j = i + 1; j < matrix[i].size(); ++j)
            swap(matrix[i][j], matrix[j][i]);
    }
}

/*
 * anticlockwise rotate
 * first reverse left to right, then swap the symmetry
 * 1 2 3     3 2 1     3 6 9
 * 4 5 6  => 6 5 4  => 2 5 8
 * 7 8 9     9 8 7     1 4 7
*/
void anti_rotate(vector<vector<int> > &matrix) {
    for (auto vi : matrix) reverse(vi.begin(), vi.end());
    for (int i = 0; i < matrix.size(); ++i) {
        for (int j = i + 1; j < matrix[i].size(); ++j)
            swap(matrix[i][j], matrix[j][i]);
    }
}

// rotate layer by layer
// http://javabypatel.blogspot.com/2016/11/rotate-matrix-by-90-degrees-inplace.html
class Solution {
    public void rotate(int[][] matrix) {
  int length = matrix.length-1;

  for (int i = 0; i <= (length)/2; i++) {
      for (int j = i; j < length-i; j++) {

       //Coordinate 1
       int p1 = matrix[i][j];

       //Coordinate 2
       int p2 = matrix[j][length-i];

       //Coordinate 3
       int p3 = matrix[length-i][length-j];

       //Coordinate 4
       int p4 = matrix[length-j][i];

       //Swap values of 4 coordinates.
       matrix[j][length-i] = p1;
       matrix[length-i][length-j] = p2;
       matrix[length-j][i] = p3;
       matrix[i][j] = p4;
      }
  }
 }
}
