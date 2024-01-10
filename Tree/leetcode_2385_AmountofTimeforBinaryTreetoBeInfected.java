/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
/**
 * 
 * 
 * You are given the root of a binary tree with unique values, and an integer start. At minute 0, an infection starts from the node with value start.

Each minute, a node becomes infected if:

The node is currently uninfected.
The node is adjacent to an infected node.
Return the number of minutes needed for the entire tree to be infected.
 */

 // I have proposed both solutions, wasn't able to finally propose the code for the optimal solution
 // for the first solution, I should have directly convert to undirected graph instead of maintaining the 
 // tree structure - that causes TLE



class Solution {
    public int amountOfTime(TreeNode root, int start) {
        Map<Integer, Set<Integer>> map = new HashMap<>();  
        convert(root, 0, map);
        Queue<Integer> queue = new LinkedList<>(); 
        queue.add(start);
        int minute = 0;
        Set<Integer> visited = new HashSet<>();
        visited.add(start);
          
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            while (levelSize > 0) {
                int current = queue.poll();
                for (int num : map.get(current)) {
                    if (!visited.contains(num)) {
                        visited.add(num);
                        queue.add(num);
                    }
                }
                levelSize--;
            }
            minute++;
        }
        return minute - 1;
    }
 
    public void convert(TreeNode current, int parent, Map<Integer, Set<Integer>> map){
        if (current == null) {
            return;
        } 
        if (!map.containsKey(current.val)) {
            map.put(current.val, new HashSet<>());
        } 
        Set<Integer> adjacentList = map.get(current.val);
        if (parent != 0) {
            adjacentList.add(parent);
        } 
        if (current.left != null) {
            adjacentList.add(current.left.val);
        } 
        if (current.right != null) {
            adjacentList.add(current.right.val);
        } 
        convert(current.left, current.val, map);
        convert(current.right, current.val, map);
    }      
}

// One pass solution
/**
 * There are four main cases:

1. If root is null, return 0.
2. root.val = start. If so, we return depth = -1 to signify this is the start node. 
In this way, in subsequent recursive calls, the parent node of the start node will know whether 
its child nodes contain the start node. Here we are also able to calculate 
the maxDistance of any node in the start node's subtree by finding the max of the left and right depth.
3. The left and right depth are both positive. If they are, we know the start node is not in this subtree,
 and we can set depth = max(leftDepth, rightDepth) just like with the basic max depth.
4. The final case is when the root is not the start node, but its subtree contains the start node. 
In this case, we will set depth = min(leftDepth, rightDepth) - 1, which will give us a negative number,
 the absolute value of which represents the distance of the start node to the root node. 
 To calculate the distance from the start node to the furthest node in the other subtree,
  we will add the absolute value of the negative depth of the subtree that contains the start node, 
  and the positive depth of the other subtree, for convenience, we can directly take the absolute value of two values. 
  Then, we update maxDistance with distance if it is larger.

 */

class Solution {
    private int maxDistance = 0;
    public int amountOfTime(TreeNode root, int start) {
        traverse(root, start);
        return maxDistance;
    }

    public int traverse(TreeNode root, int start) {
        int depth = 0;
        if (root == null) {
            return depth;
        }

        int leftDepth = traverse(root.left, start);
        int rightDepth = traverse(root.right, start);

        if (root.val == start) {
            maxDistance = Math.max(leftDepth, rightDepth);
            depth = -1;
        } else if (leftDepth >= 0 && rightDepth >= 0) {
            depth = Math.max(leftDepth, rightDepth) + 1;
        } else {
            int distance = Math.abs(leftDepth) + Math.abs(rightDepth);
            maxDistance = Math.max(maxDistance, distance);
            depth = Math.min(leftDepth, rightDepth) - 1;
        }

        return depth;
    }
}