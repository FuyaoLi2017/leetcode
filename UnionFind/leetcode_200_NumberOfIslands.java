/**
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 */

 // this problem can also be resolved with DFS with potentially better performance, added in DFS section
 // My solution, union find with path compression and union by rank
 class Solution {
    public int numIslands(char[][] grid) {
        // only check 1s to the right and bottom
        // using m / n and m % n to get the location of the element
        int m = grid.length;
        int n = grid[0].length;

        int[] parent = new int[m*n];
        int[] rank = new int[m*n];
        for (int i = 0; i < m * n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m * n; i++) {
            int x = i / n;
            int y = i % n;
            char c = grid[x][y];
            
            if (c == '0') continue;
            if (c == '1') {
                if (y < n-1 && grid [x][y+1] == '1') {
                    union(parent, rank, i, i+1);
                }
                if (x < m-1 && grid[x+1][y] == '1') {
                    union(parent, rank, i, i+n);
                }
            }
        }

        Set<Integer> res = new HashSet<>();

        for (int i = 0; i < m * n; i++) {
            int x = i / n;
            int y = i % n;
            char c = grid[x][y];
            if (grid[x][y] == '1') {
                res.add(find(parent, i));
            }
        }

        return res.size();
    }

    private int find(int[] parent, int num) {
        if (parent[num] == num) {
            return num;
        }
        else {
            // path compression
            int result = find(parent, parent[num]);
            parent[num] = result;
            return result;
        }
    }

    private void union(int[] parent, int[] rank, int first, int second) {
        int firstParent = find(parent, first);
        int secondParent = find(parent, second);

        if (firstParent == secondParent) return;

        // 
        int firstRank = rank[firstParent];
        int secondRank = rank[secondParent];

        if (firstRank < secondRank) {
            parent[firstParent] = secondParent;
        } else if (firstRank > secondRank) {
            parent[secondParent] = firstParent;
        } else {
            parent[firstParent] = secondParent;
            rank[secondParent]++;
        }
    }

    
}


// enhanced UF, maintain the count in the middle of computation
class Solution {
  class UnionFind {
    int count; // # of connected components
    int[] parent;
    int[] rank;

    public UnionFind(char[][] grid) { // for problem 200
      count = 0;
      int m = grid.length;
      int n = grid[0].length;
      parent = new int[m * n];
      rank = new int[m * n];
      for (int i = 0; i < m; ++i) {
        for (int j = 0; j < n; ++j) {
          if (grid[i][j] == '1') {
            parent[i * n + j] = i * n + j;
            ++count;
          }
          rank[i * n + j] = 0;
        }
      }
    }

    public int find(int i) { // path compression
      if (parent[i] != i) parent[i] = find(parent[i]);
      return parent[i];
    }

    public void union(int x, int y) { // union with rank
      int rootx = find(x);
      int rooty = find(y);
      if (rootx != rooty) {
        if (rank[rootx] > rank[rooty]) {
          parent[rooty] = rootx;
        } else if (rank[rootx] < rank[rooty]) {
          parent[rootx] = rooty;
        } else {
          parent[rooty] = rootx; rank[rootx] += 1;
        }
        --count;
      }
    }

    public int getCount() {
      return count;
    }
  }

  public int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }

    int nr = grid.length;
    int nc = grid[0].length;
    int num_islands = 0;
    UnionFind uf = new UnionFind(grid);
    for (int r = 0; r < nr; ++r) {
      for (int c = 0; c < nc; ++c) {
        if (grid[r][c] == '1') {
          grid[r][c] = '0';
          if (r - 1 >= 0 && grid[r-1][c] == '1') {
            uf.union(r * nc + c, (r-1) * nc + c);
          }
          if (r + 1 < nr && grid[r+1][c] == '1') {
            uf.union(r * nc + c, (r+1) * nc + c);
          }
          if (c - 1 >= 0 && grid[r][c-1] == '1') {
            uf.union(r * nc + c, r * nc + c - 1);
          }
          if (c + 1 < nc && grid[r][c+1] == '1') {
            uf.union(r * nc + c, r * nc + c + 1);
          }
        }
      }
    }

    return uf.getCount();
  }
}