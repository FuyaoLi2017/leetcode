/*
In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.

The given input is a directed graph that started as a rooted tree with N nodes (with distinct values 1, 2, ..., N), with one additional directed edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] that represents a directed edge connecting nodes u and v, where u is a parent of child v.

Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given directed graph will be like this:
  1
 / \
v   v
2-->3
Example 2:
Input: [[1,2], [2,3], [3,4], [4,1], [1,5]]
Output: [4,1]
Explanation: The given directed graph will be like this:
5 <- 1 -> 2
     ^    |
     |    v
     4 <- 3
Note:
The size of the input 2D-array will be between 3 and 1000.
Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.
*/

// UnionFind
// https://www.youtube.com/watch?v=lnmJT5b4NlM&t=643s
class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int[] can1 = {-1, -1};
        int[] can2 = {-1, -1};
        int[] parent = new int[edges.length + 1];
        for (int i = 0; i < edges.length; i++) {
            if (parent[edges[i][1]] == 0) {
                parent[edges[i][1]] = edges[i][0];
            } else {
                // 找到有2个parent的点
                // can2是后加进来的candidate，一会下面就被删除了
                can2 = new int[] {edges[i][0], edges[i][1]};
                // can1是之前的candidate
                can1 = new int[] {parent[edges[i][1]], edges[i][1]};
                // 把第二个边删除
                edges[i][1] = 0;
            }
        }
        for (int i = 0; i < edges.length; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < edges.length; i++) {
            if (edges[i][1] == 0) {
                continue;
            }
            int child = edges[i][1], father = edges[i][0];
            // the following sentence is showing the tree has a cycle
            // 不断的往parent方向取值，找到union find的root
            if (root(parent, father) == child) {
                if (can1[0] == -1) {
                    // case 1: 没有入度为2的节点，可以直接return当前造成环的边
                    return edges[i];
                }
                // case2.2, 删除的是造成环的重复顶点的那条边
                // 因为原来的那个第二条边已经被删除了
                return can1;
            }
            parent[child] = father;
        }
        // 第一层含义：环是由ans2构成的，之前已经删除了，在55行的位置continue了，所以应该返回ans2
        // 忽略这行，第二层含义：case2.1, 没有成环，返回can2
        return can2;
    }

    int root(int[] parent, int i) {
        while (i != parent[i]) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }
}

// huahua 加上path compression
class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] parents = new int[n + 1];
        int[] roots = new int[n + 1];
        int[] sizes = new int[n + 1];
        int[] ans1 = {-1, -1};
        int[] ans2 = {-1, -1};
        for (int i = 1; i <= n; i++) {
            roots[i] = i;
        }
        Arrays.fill(sizes, 1);
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            if (parents[v] > 0) {
                ans1 = new int[]{parents[v], v};
                ans2 = new int[]{u, v};
                edge[0] = -1;
                edge[1] = -1;
            }
            parents[v] = u;
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            if (u < 0 && v < 0) continue;

            int root_u = find(u, roots);
            int root_v = find(v, roots);
            if (root_u == root_v) {
                return ans1[0] < 0 ? edge : ans1;
            }
            if (sizes[root_u] < sizes[root_v]) {
                roots[root_u] = root_v;
                sizes[root_v] += sizes[root_u];
            } else {
                roots[root_v] = root_u;
                sizes[root_u] += sizes[root_v];
            }

        }
        return ans2;
    }

    // path compression的做法，直接赋给上面的数字
    private int find(int node, int[] roots) {\
        while (roots[node] != node) {
            roots[node] = roots[roots[node]];
            node = roots[node];
        }
        return node;
    }
}
