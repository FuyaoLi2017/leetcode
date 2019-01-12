/*
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
*/
class Solution {
    public boolean exist(char[][] board, String word) {
        char[] w = word.toCharArray();
        for(int y = 0; y < board.length; y++){
            for(int x = 0; x < board[y].length; x++){
                if(exist(board, y, x, w, 0)) return true;
            }
        }
        return false;
    }
    private boolean exist(char[][] board, int y, int x, char[] word, int i){
        if(i == word.length) return true;
        if(y<0 || x<0 || y == board.length || x == board[y].length) return false;
        if(board[y][x] != word[i]) return false;
        //in UNICODE/ASCII A~Z is from 65~89
        //they are less than 128, so when they do XOR operation with 256
        //with 9th bit 1, they must return a value which is not a between A~Z
        //this is a mark, so it will not count this letter again
        board[y][x] ^= 256;
        boolean exist = exist(board, y, x+1, word, i+1)
            || exist(board, y, x-1, word, i+1)
            || exist(board, y+1, x, word, i+1)
            || exist(board, y-1, x, word, i+1);
        //resume the original letter
            board[y][x] ^=256;
        return exist;
    }
}
