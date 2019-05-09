/*

Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

Example:

Input: 3
Output:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
*/
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int[][] res = new int[n][n];
        boolean[][] visited = new boolean[n][n];
        int x = 0, y = 0;
        int direction = 0;
        for (int i = 1; i <= n * n; i++) {
            res[x][y] = i;
            visited[x][y] = true;
            int nextX = x + dir[direction][0];
            int nextY = y + dir[direction][1];
            if (nextX < n && nextX >= 0 && nextY < n && nextY >= 0 && visited[nextX][nextY] == false) {
                x = nextX;
                y = nextY;
            } else {
                direction = (direction + 1) % 4;
                nextX = x + dir[direction][0];
                nextY = y + dir[direction][1];
                x = nextX;
                y = nextY;
            }
        }
        return res;
    }
}s
