/*
Let's play the minesweeper game (Wikipedia, online game)!

You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine, 'E' represents an unrevealed empty square, 'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines, digit ('1' to '8') represents how many mines are adjacent to this revealed square, and finally 'X' represents a revealed mine.

Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'), return the board after revealing this position according to the following rules:

If a mine ('M') is revealed, then the game is over - change it to 'X'.
If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.
If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
Return the board when no more squares will be revealed.
*/
class Solution {
    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        int M = board.length, N = board[0].length;

        Queue<int[]> q = new LinkedList();
        q.offer(click);

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int i = curr[0], j = curr[1];

            char count = '0';
            for (int[] dir : dirs) {
                int nextX = i + dir[0];
                int nextY = j + dir[1];
                if (nextX < 0 || nextX >= M || nextY < 0 || nextY >= N) {
                    continue;
                }
                if (board[nextX][nextY] == 'M') count++;
            }

            if (count == '0') {
                board[i][j] = 'B';
                for (int[] dir : dirs) {
                    int nextX = i + dir[0];
                    int nextY = j + dir[1];
                    if (nextX >= 0 && nextX < M && nextY >= 0 && nextY < N && board[nextX][nextY] == 'E') {
                        q.offer(new int[]{nextX, nextY});
                        // 相当于是加了visited，以后不会再去重复添加到queue
                        board[nextX][nextY] = 'B';
                    }
                }
            } else {
                board[i][j] = count;
            }
        }
        return board;
    }
}
