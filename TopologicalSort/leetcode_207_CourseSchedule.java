/*
There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:

Input: 2, [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
             To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: 2, [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take.
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
*/
//BFS
// if we find a cycle in the graph, we need to return false
// we use a queue to realize BFS. we traverse all the points,
// if we got all the numbers from the source, we can return true

// The idea is Topological sort. every Entry(matrix[i][j]) in the matrix designates the dependency of the course j on the course i.
// if matrix[i][j] == 1 => course j is dependent on course i.
// The indegree[j] denotes the no. of dependencies for course j.
// Then we search for the courses whose indegree is zero (all such independent courses can be finished first.)
// Next for each of such courses mark them as finished(increase count), and subsequently check if other courses were dependent on this course.
// If yes, reduce the dependency count i.e. indegree for those courses.
// if any of their indegree becomes zero after reduction, that course becomes a candidate to be finished next (no more pre-requisite).
// At the end we see of count equals total no. of courses.
// Hope this helps!
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        boolean[][] graph = new boolean[numCourses][numCourses];
        int[] inDegreeArr = new int[numCourses];
        for(int i = 0;i< prerequisites.length;i++){
            graph[prerequisites[i][1]][prerequisites[i][0]] = true;
            inDegreeArr[prerequisites[i][0]]++;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        for(int i = 0;i<inDegreeArr.length;i++){
            if(inDegreeArr[i] == 0) queue.add(i);
        }
        int count = 0;
        while(!queue.isEmpty()){
            count++;
            int courseTaken = queue.poll();
            for(int i = 0;i< graph.length;i++){
                if(graph[courseTaken][i]){
                    inDegreeArr[i]--;
                    if(inDegreeArr[i] == 0) queue.add(i);
                }
            }
        }
        return count == numCourses;
    }
}

// BFS,最多吧indegree变成1的做法
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        List<List<Integer>> adj = new ArrayList<>(numCourses);

        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {
            adj.get(prerequisite[1]).add(prerequisite[0]);
            indegree[prerequisite[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        // Push all the nodes with indegree zero in the queue.
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int nodesVisited = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            nodesVisited++;

            for (int neighbor : adj.get(node)) {
                // Delete the edge "node -> neighbor".
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        return nodesVisited == numCourses;
    }
}

// 用ArrayList做DFS,比用boolean可以少很多支路
public class Solution {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            ArrayList[] graph = new ArrayList[numCourses];
            for(int i=0;i<numCourses;i++)
                graph[i] = new ArrayList();

            boolean[] visited = new boolean[numCourses];
            for(int i=0; i<prerequisites.length;i++){
                graph[prerequisites[i][1]].add(prerequisites[i][0]);
            }

            for(int i=0; i<numCourses; i++){
                if(!dfs(graph,visited,i))
                    return false;
            }
            return true;
        }

        private boolean dfs(ArrayList[] graph, boolean[] visited, int course){
            if(visited[course])
                return false;
            else
                visited[course] = true;

            for(int i=0; i<graph[course].size();i++){
                if(!dfs(graph,visited,(int)graph[course].get(i)))
                    return false;
            }
            visited[course] = false;
            return true;
        }
    }

// dfs, 用boolean不好,每个都检查，太慢了
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        boolean[][] graph = new boolean[numCourses][numCourses];
        int[] inDegreeArr = new int[numCourses];
        for(int i = 0;i< prerequisites.length;i++){
            graph[prerequisites[i][1]][prerequisites[i][0]] = true;
            inDegreeArr[prerequisites[i][0]]++;
        }

        boolean[] visited = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(graph, visited, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(boolean[][] graph, boolean[] visited, int course) {
        if (visited[course]) {
            return false;
        }
        visited[course] = true;
        for (int i = 0; i < graph[course].length; i++) {
            if (graph[course][i] && !dfs(graph, visited, i)) {
                return false;
            }
        }
        visited[course] = false;
        return true;
    }
}
