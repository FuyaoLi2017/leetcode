/*
Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

Example 1:

Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
Output: true
Example 2:

Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
Output: false
Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0,1] is the same as [1,0] and thus will not appear together in edges.
*/

// DFS
public class Solution {
    public boolean validTree(int n, int[][] edges) {
        // initialize adjacency list
        List<List<Integer>> adjList = new ArrayList<List<Integer>>(n);

        // initialize vertices
        for (int i = 0; i < n; i++)l
            adjList.add(i, new ArrayList<Integer>());

        // add edges
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0], v = edges[i][1];
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        boolean[] visited = new boolean[n];

        // make sure there's no cycle
        if (hasCycle(adjList, 0, visited, -1))
            return false;

        // make sure all vertices are connected
        for (int i = 0; i < n; i++) {
            if (!visited[i])
                return false;
        }

        return true;
    }

    // check if an undirected graph has cycle started from vertex u
    boolean hasCycle(List<List<Integer>> adjList, int u, boolean[] visited, int parent) {
        visited[u] = true;

        for (int i = 0; i < adjList.get(u).size(); i++) {
            int v = adjList.get(u).get(i);
            /*
            The (visited[v] && parent != v) means, the node v has been visited, but not because it's the parent that's passed in from the previous recursion, so that it means it has a cycle.

            The second half (!visited[v] && hasCycle(adjList, v, visited, u) means, v has not been visited, so let's check with v, to see if there's cycle starting from v.

            Both condition would mean there's a cycle in the graph, thus return true.
            */
            if ((visited[v] && parent != v) || (!visited[v] && hasCycle(adjList, v, visited, u)))
                return true;
        }

        return false;
    }
}


// UNION FIND
class UF {
	// union-find, bfs
	int N;
	int[] id;
	int[] sz;
	int segments;
	public UF(int n){
		N = n;
		segments = n;
		id = new int[N];
	    sz = new int[N];
		for(int i=0; i<N; i++){
			id[i] = i;
			sz[i] = 1;
		}
	}
	public int root(int i) {
		while(i!=id[i]){
			id[i] = id[id[i]]; // path compression
			i = id[i];
		}
		return i;
	}
	public void connect(int p, int q){
		int i = root(p);
		int j = root(q);
		if(i == j) return;
		if(sz[i]<sz[j]) { // if-else: weighted quick-union
			id[i] = j;
			sz[j] += sz[i];
		}
		else if(sz[i]>sz[j]) {
			id[j] = i;
			sz[i] += sz[j];
		}
		else {
			id[j] = i;
			sz[i] += sz[j];
		}
		segments--;
	}
	public boolean connected(int p, int q) {
		if(root(p)==root(q)){
			return true;
		}
		return false;
	}
}
public class Solution {
	public boolean validTree(int n, int[][] edges) {
		UF uf = new UF(n);
		int M = edges.length;
		for(int i=0; i<M; i++){
			int p = edges[i][0], q = edges[i][1];
			if(uf.root(p)==uf.root(q)){
				return false;
			}
			uf.connect(p, q);
		}

		if (uf.segments!=1){
			return false;
		}

		return true;
	}
}
