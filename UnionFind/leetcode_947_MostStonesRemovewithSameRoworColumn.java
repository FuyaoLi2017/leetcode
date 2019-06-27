/*
On a 2D plane, we place stones at some integer coordinate points.  Each coordinate point may have at most one stone.

Now, a move consists of removing a stone that shares a column or row with another stone on the grid.

What is the largest possible number of moves we can make?



Example 1:

Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
Output: 5
Example 2:

Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
Output: 3
Example 3:

Input: stones = [[0,0]]
Output: 0


Note:

1 <= stones.length <= 1000
0 <= stones[i][j] < 10000
*/
// https://www.jianshu.com/p/30d2058db7f7
// 如果两个石头在同行或者同列，两个石头就是连接的。连在一起的石头，可以组成一个连通图。每一个连通图至少会剩下1个石头。
// 所以我们希望存在一种思路，每个连通图都只剩下1个石头。
// 这样这题就转化成了数岛屿的问题。

// Using DFS

class Solution {
    public int removeStones(int[][] stones) {
        Map<Integer, Set<Integer>> rSet = new HashMap<>(), cSet = new HashMap<>();
        for(int[] e : stones) {
            int r = e[0], c = e[1];
            rSet.putIfAbsent(r, new HashSet<>());
            cSet.putIfAbsent(c, new HashSet<>());
            rSet.get(r).add(c);
            cSet.get(c).add(r);
        }

        int count = 0;
        Set<String> v = new HashSet<>();
        for(int[] e : stones) {
            String key = e[0]+","+e[1];
            if(!v.contains(key)) {
                count++;
                v.add(key);
                dfs(e[0], e[1], rSet, cSet, v);
            }
        }
        return stones.length - count;
    }

    void dfs(int r, int c, Map<Integer, Set<Integer>> rSet, Map<Integer, Set<Integer>> cSet, Set<String> v) {
        for(int y : rSet.get(r)) {
            String key = r+","+y;
            if(!v.contains(key)) {
                v.add(key);
                dfs(r, y, rSet, cSet, v);
            }
        }

        for(int x : cSet.get(c)) {
            String key = x+","+c;
            if(!v.contains(key)) {
                v.add(key);
                dfs(x, c, rSet, cSet, v);
            }
        }
    }
}

// Standard Union Find
class Solution {
    int[] unions = new int[1000];
    public int removeStones(int[][] stones) {
        int len = stones.length;
        for (int i = 0; i < len; i++){
            unions[i] = i;
        }

        for (int i = 0; i < len; i++){
            for (int j = i + 1; j < len; j++){
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]){
                    union(i, j);
                }
            }
        }

        int count = 0;
        for (int i = 0; i < len; i++){
            // count the number of distinct islands
            if (unions[i] == i) count++;
        }

        return len - count;
    }

    private void union(int x, int y){
        x = find(x);
        y = find(y);
        if (x == y) return;
        unions[y] = x;
    }

    private int find(int x){
        if (unions[x] == x) return x;
        return find(unions[x]);
    }
}
