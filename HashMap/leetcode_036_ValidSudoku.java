/*
Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.

A partially filled sudoku which is valid.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

Example 1:

Input:
[
  ["5","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
Output: true
Example 2:

Input:
[
  ["8","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
Output: false
Explanation: Same as Example 1, except with the 5 in the top left corner being
    modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
Note:

A Sudoku board (partially filled) could be valid but is not necessarily solvable.
Only the filled cells need to be validated according to the mentioned rules.
The given board contain only digits 1-9 and the character '.'.
The given board size is always 9x9.
*/

// an answer with calling one function designed to test a area
// I wrote it once again
public class Solution {
public boolean isValidSudoku(char[][] board) {
    for (int i=0; i<9; i++) {
        if (!isParticallyValid(board,i,0,i,8)) return false;
        if (!isParticallyValid(board,0,i,8,i)) return false;
    }
    for (int i=0;i<3;i++){
        for(int j=0;j<3;j++){
            if (!isParticallyValid(board,i*3,j*3,i*3+2,j*3+2)) return false;
        }
    }
    return true;
}
private boolean isParticallyValid(char[][] board, int x1, int y1,int x2,int y2){
    Set singleSet = new HashSet();
    for (int i= x1; i<=x2; i++){
        for (int j=y1;j<=y2; j++){
            if (board[i][j]!='.') if(!singleSet.add(board[i][j])) return false;
        }
    }
    return true;
}
}

// a tricky solution using i, j
/*
The 95th, 96th lines is intended to get all top left corner index of nine cubics. It is a trick here. When you traverse i from 0 to 8.
Here RowIndex and ColIndex will traverse through all the possible top left index of the cubics.

The 97th line is intended to get traverse through all the elements in the cubic confirmed by the i in the first lines.
when j traverse from 0 - 8, it will just traverse through all possible index of the cubic assigned by the fixed i value in the outer loop.
*/
public boolean isValidSudoku(char[][] board) {
    for(int i = 0; i<9; i++){
        HashSet<Character> rows = new HashSet<Character>();
        HashSet<Character> columns = new HashSet<Character>();
        HashSet<Character> cube = new HashSet<Character>();
        for (int j = 0; j < 9;j++){
            if(board[i][j]!='.' && !rows.add(board[i][j]))
                return false;
            if(board[j][i]!='.' && !columns.add(board[j][i]))
                return false;
            int RowIndex = 3*(i/3);
            int ColIndex = 3*(i%3);
            if(board[RowIndex + j/3][ColIndex + j%3]!='.' && !cube.add(board[RowIndex + j/3][ColIndex + j%3]))
                return false;
        }
    }
    return true;
}
