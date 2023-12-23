/**
 * You are given a list of bombs. The range of a bomb is defined as the area where its effect can be felt. This area is in the shape of a circle with the center as the location of the bomb.

The bombs are represented by a 0-indexed 2D integer array bombs where bombs[i] = [xi, yi, ri]. xi and yi denote the X-coordinate and Y-coordinate of the location of the ith bomb, whereas ri denotes the radius of its range.

You may choose to detonate a single bomb. When a bomb is detonated, it will detonate all bombs that lie in its range. These bombs will further detonate the bombs that lie in their ranges.

Given the list of bombs, return the maximum number of bombs that can be detonated if you are allowed to detonate only one bomb.
 */

 // My Solution - make sure using long to handle overflow
 // I skipped some visited nodes to reduce the dfs triggered - faster
 // this is a directed graph, right bomb might trigger left, but might not work on the other side
 class Solution {
    public int maximumDetonation(int[][] bombs) {
        // key: index for bomb, connected index
        Map<Integer, List<Integer>> connectedBombs = new HashMap<>();

        for (int i = 0; i < bombs.length; i++) {
            int[] bomb1 = bombs[i];
            int x1 = bomb1[0];
            int y1 = bomb1[1];
            int r1 = bomb1[2];
            for (int j = 0; j < bombs.length; j++) {
                if (i == j) continue;
                int[] bomb2 = bombs[j];
                int x2 = bomb2[0];
                int y2 = bomb2[1];
                if ((long)(x1 - x2) * (x1 - x2) + (long)(y1 - y2) * (y1 - y2) <= (long)r1 * r1) { // connected
                    connectedBombs.computeIfAbsent(i, k -> new ArrayList<>()).add(j);
                }
            }
        }

        // dfs
        int res = 0;
        for (int i = 0; i < bombs.length; i++) {
            boolean[] visited = new boolean[bombs.length];
            int curMax = dfs(visited, connectedBombs, i);
            res = Math.max(res, curMax);
        }
        return res;
    }

    private int dfs(boolean[] visited, Map<Integer, List<Integer>> connectedBombs, int cur) {
        if (visited[cur]) return 0;
        visited[cur] = true;
        List<Integer> list = connectedBombs.get(cur);
        if (list == null) return 1;
        int res = 1;
        for (int i = 0; i < list.size(); i++) {
            res += dfs(visited, connectedBombs, list.get(i));
        }
        return res;
    }

}


// editorial Depth-First Search, Recursive
class Solution {
    public int maximumDetonation(int[][] bombs) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int n = bombs.length;

        // Build the graph
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                int xi = bombs[i][0], yi = bombs[i][1], ri = bombs[i][2];
                int xj = bombs[j][0], yj = bombs[j][1];

                // Create a path from node i to node j, if bomb i detonates bomb j.
                if ((long)ri * ri >= (long)(xi - xj) * (xi - xj) + (long)(yi - yj) * (yi - yj)) {
                    graph.computeIfAbsent(i, k -> new ArrayList<>()).add(j);
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            int count = dfs(i, new HashSet<>(), graph);
            answer = Math.max(answer, count);
        }

        return answer;
    }

    // DFS to get the number of nodes reachable from a given node cur
    private int dfs(int cur, Set<Integer> visited, Map<Integer, List<Integer>> graph) {
        visited.add(cur);
        int count = 1;
        for (int neib : graph.getOrDefault(cur, new ArrayList<>())) {
            if (!visited.contains(neib)) {
                count += dfs(neib, visited, graph);
            }
        }
        return count;
    }
}

// Editorial  Depth-First Search, Iterative
// We can also implement DFS iteratively using a stack to replicate recursive self-calls to dfs. Since the operations on a stack are performed in First In, Last Out (FILO) order. Therefore, the top node on the stack always leads to the next branch: whenever we reach the end of the current branch, we can get the node on the top of the stack and move along the branch that starts from it.
// A hash set visited is used to store all the visited nodes, so we don't need to take them into account. Once we add an unvisited node to the stack, we immediately add it to visited to prevent it from being revisited later.
class Solution {
    public int maximumDetonation(int[][] bombs) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int n = bombs.length;
        
        // Build the graph
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                int xi = bombs[i][0], yi = bombs[i][1], ri = bombs[i][2];
                int xj = bombs[j][0], yj = bombs[j][1];

                // Create a path from node i to node j, if bomb i detonates bomb j.
                if ((long)ri * ri >= (long)(xi - xj) * (xi - xj) + (long)(yi - yj) * (yi - yj)) {
                    graph.computeIfAbsent(i, k -> new ArrayList<>()).add(j);
                }
            }
        }
        
        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, dfs(i, graph));
        }
        
        return answer;
    }
    
    private int dfs(int i, Map<Integer, List<Integer>> graph) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        stack.push(i);
        visited.add(i);
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            for (int neib : graph.getOrDefault(cur, new ArrayList<>())) {
                if (!visited.contains(neib)) {
                    visited.add(neib);
                    stack.push(neib);
                }
            }
        }
        return visited.size();
    }
}


// BFS
class Solution {
    public int maximumDetonation(int[][] bombs) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int n = bombs.length;
        
        // Build the graph
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int xi = bombs[i][0], yi = bombs[i][1], ri = bombs[i][2];
                int xj = bombs[j][0], yj = bombs[j][1];
                
                // Create a path from node i to node j, if bomb i detonates bomb j.
                if ((long)ri * ri >= (long)(xi - xj) * (xi - xj) + (long)(yi - yj) * (yi - yj)) {
                    graph.computeIfAbsent(i, k -> new ArrayList<>()).add(j);
                }
            }
        }
        
        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, bfs(i, graph));
        }
        
        return answer;
    }
    
    private int bfs(int i, Map<Integer, List<Integer>> graph) {
        Deque<Integer> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(i);
        visited.add(i);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int neib : graph.getOrDefault(cur, new ArrayList<>())) {
                if (!visited.contains(neib)) {
                    visited.add(neib);
                    queue.offer(neib);
                }
            }
        }
        return visited.size();
    }
}