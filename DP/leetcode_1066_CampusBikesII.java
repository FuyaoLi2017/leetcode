/*
On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D coordinate on this grid.

We assign one unique bike to each worker so that the sum of the Manhattan distances between each worker and their assigned bike is minimized.

The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.

Return the minimum possible sum of Manhattan distances between each worker and their assigned bike.
*/
// https://leetcode.com/problems/campus-bikes-ii/discuss/305218/DFS-%2B-Pruning-And-DP-Solution

class Solution {
    // state : dp[i][s] = the min distance for first i workers to build the state s ,
    // transit: dp[i][s] = min(dp[i][s], dp[i - 1][prev] + dis(w[i -1], bike[j)) | 0 < j <m, prev = s ^ (1 << j)
    // init:dp[0][0] = 0;
    // result: dp[n][s] s should have n bit
  public int assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length;
        int m = bikes.length;
        int[][] dp = new int[n + 1][1 << m];
        for (int[] d : dp) {
            Arrays.fill(d, Integer.MAX_VALUE / 2);
        }
        dp[0][0] = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int s = 1; s < (1 << m); s++) {
                // traverse all bike choices
                for (int j = 0; j < m; j++) {
                    // if the j th bike is not occupied (j th bit is 0), continue
                    if ((s & (1 << j)) == 0) {
                        continue;
                    }

                    // set j th bit to be the reverse state, which is 0
                    int prev = s ^ (1 << j);
                    // try to update it with a new state at j th position
                    dp[i][s] = Math.min(dp[i - 1][prev] + dis(workers[i - 1], bikes[j]), dp[i][s]);
                    // all the bits have been set, update it to the final result
                    if (i == n) {
                        min = Math.min(min, dp[i][s]);
                    }
                }
            }
        }
        return min;
    }

    public int dis(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }
}


// using DFS + Pruning
int min = Integer.MAX_VALUE;
public int assignBikes(int[][] workers, int[][] bikes) {
    dfs(new boolean[bikes.length], workers, 0, bikes, 0);
    return min;
}
public void dfs(boolean[] visit, int[][] workers, int i, int[][] bikes, int distance) {
    if (i >= workers.length) {
        min = Math.min(distance, min);
        return ;
    }
    // if distance is more than min, we can stop further traverse it
    if (distance > min) {
        return ;
    }
    for (int j = 0; j < bikes.length; j++) {
        if (visit[j]) {
            continue;
        }
        visit[j] = true;
        dfs(visit, workers, i + 1, bikes, distance + dis(bikes[j], workers[i]));
        visit[j] = false;
    }

}
public int dis(int[] p1, int[] p2) {
    return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
}
