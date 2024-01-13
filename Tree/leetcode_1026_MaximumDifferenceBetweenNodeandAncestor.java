/**
 * Given the root of a binary tree, find the maximum value v for which there exist different nodes a and b where v = |a.val - b.val| and a is an ancestor of b.

A node a is an ancestor of b if either: any child of a is equal to b or any child of a is an ancestor of b.
 */

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


 // my original solution
 class Solution {
    int maxDiff = 0;
    public int maxAncestorDiff(TreeNode root) {
        List<Integer> parents = new ArrayList<>();
        parents.add(root.val);
        dfs(root, parents);
        return maxDiff;
    }

    private void dfs(TreeNode root, List<Integer> parents) {
        if (root == null) return;
        // we can just keep track of max and min here to simplify the logic
        for (int i = 0; i < parents.size(); i++) {
            maxDiff = Math.max(maxDiff, Math.abs(root.val - parents.get(i)));
        }
        parents.add(root.val);
        dfs(root.left, parents);
        dfs(root.right, parents);
        parents.remove(parents.size()-1);
    }
}

// enhanced solution

class Solution {
    public int maxAncestorDiff(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return dfs(root, root.val, root.val);
    }

    private int dfs(TreeNode root, int curMin, int curMax) {
        if (root == null) {
            return curMax - curMin;
        }

        curMax = Math.max(root.val, curMax);
        curMin = Math.min(root.val, curMin);
        
        int left = dfs(root.left, curMin, curMax);
        int right = dfs(root.right, curMin, curMax);
        return Math.max(left, right);
    }
}