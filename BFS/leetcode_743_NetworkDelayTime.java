/*
There are N network nodes, labelled 1 to N.

Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.

Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.



Example 1:



Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
Output: 2


Note:

N will be in the range [1, 100].
K will be in the range [1, N].
The length of times will be in the range [1, 6000].
All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 0 <= w <= 100.
*/
// a standard solution
class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap();
        for (int[] edge: times) {
            if (!graph.containsKey(edge[0]))
                graph.put(edge[0], new ArrayList<int[]>());
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>(
                (info1, info2) -> info1[0] - info2[0]);
        heap.offer(new int[]{0, K});

        Map<Integer, Integer> dist = new HashMap();

        while (!heap.isEmpty()) {
            int[] info = heap.poll();
            int d = info[0], node = info[1];
            if (dist.containsKey(node)) continue;
            dist.put(node, d);
            if (graph.containsKey(node))
                for (int[] edge: graph.get(node)) {
                    int nei = edge[0], d2 = edge[1];
                    if (!dist.containsKey(nei))
                        heap.offer(new int[]{d+d2, nei});
                }
        }

        if (dist.size() != N) return -1;
        int ans = 0;
        for (int cand: dist.values())
            ans = Math.max(ans, cand);
        return ans;
    }
}

// a quick solution
import javafx.util.Pair;

class Solution {
    private int[][] graph;
    private int[] result;
    private boolean[] visited;

    public int networkDelayTime(int[][] times, int N, int K) {
        graph = new int[N + 1][N + 1];
        result = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(graph[i], -1);
        }
        Arrays.fill(result, Integer.MAX_VALUE);
        result[K] = 0;

        for (int i = 0; i < times.length; i++) {
            graph[times[i][0]][times[i][1]] = times[i][2];
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return a[1] - b[1];
            }
        });

        pq.offer(new int[]{K, 0});

        while (!pq.isEmpty()) {
            int[] temp = pq.poll();
            int node = temp[0];

            if (visited[node]) continue;
            visited[node] = true;

            for (int i = 0; i < N + 1; i++) {
                if (graph[node][i] != -1 && result[i] > result[node] + graph[node][i]) {
                    result[i] = result[node] + graph[node][i];
                    pq.add(new int[]{i, result[i]});
                }
            }
        }

        int max = 0;
        for (int i = 1; i < N + 1; i ++) {
            max = Math.max(max, result[i]);
        }

        if (max == Integer.MAX_VALUE) {
            return -1;
        }

        return max;
    }

}
