/*

You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

Example:

Given the 2D grid:

INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
After running your function, the 2D grid should be:

  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
*/

// my enhanced bfs
class Solution {
    int[][] dirs = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) return;
        // 1. find the gates
        List<int[]> gates = new ArrayList<>();

        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if(rooms[i][j] == 0) gates.add(new int[]{i, j});
            }
        }

        // do bfs for all nodes
        for (int i = 0; i < gates.size(); i++) {
            boolean[][] visited = new boolean[rooms.length][rooms[0].length];
            int[] point = gates.get(i);
            bfs(rooms, point[0], point[1], visited);
        }
    }

    private void bfs(int[][] rooms, int x, int y, boolean[][] visited) {
        // x, y coordinate, distance
        LinkedList<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y, 0});

        while (!q.isEmpty()) {
            int[] point = q.poll();
            for (int[] dir : dirs) {
                int nextX = point[0] + dir[0];
                int nextY = point[1] + dir[1];
                if (nextX < 0 || nextX >= rooms.length || nextY < 0 || nextY >= rooms[0].length
                    || rooms[nextX][nextY] == -1 || visited[nextX][nextY] == true) {
                    continue;
                }
                visited[nextX][nextY] = true;
                if (point[2]+1 < rooms[nextX][nextY]) {
                    rooms[nextX][nextY] = point[2] + 1;
                    q.offer(new int[]{nextX, nextY, point[2] + 1});
                }
            }
        }
    }
}

// a better solution, using bfs for all nodes at the same time
class Solution {
    private static final int EMPTY = Integer.MAX_VALUE;
    private static final int GATE = 0;
    private static final List<int[]> DIRECTIONS = Arrays.asList(
            new int[] { 1,  0},
            new int[] {-1,  0},
            new int[] { 0,  1},
            new int[] { 0, -1}
    );

    public void wallsAndGates(int[][] rooms) {
        int m = rooms.length;
        if (m == 0) return;
        int n = rooms[0].length;
        Queue<int[]> q = new LinkedList<>();
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (rooms[row][col] == GATE) {
                    q.add(new int[] { row, col });
                }
            }
        }
        while (!q.isEmpty()) {
            int[] point = q.poll();
            int row = point[0];
            int col = point[1];
            for (int[] direction : DIRECTIONS) {
                int r = row + direction[0];
                int c = col + direction[1];
                if (r < 0 || c < 0 || r >= m || c >= n || rooms[r][c] != EMPTY) {
                    continue;
                }
                rooms[r][c] = rooms[row][col] + 1;
                q.add(new int[] { r, c });
            }
        }
    }
}
