/*
There are n houses in a village. We want to supply water for all the houses by building wells and laying pipes.

For each house i, we can either build a well inside it directly with cost wells[i], or pipe in water from another well to it. The costs to lay pipes between houses are given by the array pipes, where each pipes[i] = [house1, house2, cost] represents the cost to connect house1 and house2 together using a pipe. Connections are bidirectional.

Find the minimum total cost to supply water to all houses.
*/
// https://leetcode.com/problems/optimize-water-distribution-in-a-village/
class Solution {
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        if(n <= 0) return 0;
        // add a virtual node 0, this node connects to all villages with the wells value
        // we want to build a minimum spanning tree

        // we want to run kruskal algorithm on all the edges
        // we can transform this problem into a graph problem!
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return o1[2] - o2[2];
        });

        // add all direct edges
        for(int[] pipe : pipes){
            pq.offer(pipe);
        }

        // add the edge from the virtual node to the villages
        for(int i = 0; i < n; i++){
            pq.offer(new int[]{0, i+1, wells[i]});
        }

        int[] roots = new int[n+1];
        int[] size = new int[n+1];

        for(int i = 0; i < roots.length; i++){
            roots[i] = i;
        }

        Arrays.fill(size, 1);

        // should reach n, we need n edges to connect (n+1) components in my graph
        int unionCount = 0;
        int totalCost = 0;

        while(unionCount < n){
            int[] edge = pq.poll();
            int start = edge[0];
            int end = edge[1];
            int cost = edge[2];

            int rootStart = find(roots, start);
            int rootEnd = find(roots, end);
            if(rootStart != rootEnd){
                union(roots, size, start, end);
                unionCount++;
                totalCost += cost;
            }
        }

        return totalCost;
    }

    private int find(int[] roots, int node){
        while(roots[node] != node){
            roots[node] = roots[roots[node]];
            node = roots[node];
        }
        return node;
    }

    private void union(int[] roots, int[] size, int start, int end){
        int startRoot = find(roots, start);
        int endRoot = find(roots, end);
        int startSize = size[startRoot];
        int endSize = size[endRoot];

        if(startSize >= endSize){
            roots[endRoot] = startRoot;
            size[startRoot] += endSize;
        } else {
            roots[startRoot] = endRoot;
            size[endRoot] += startSize;
        }
    }
}
