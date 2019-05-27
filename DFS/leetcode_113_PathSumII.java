/*
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1
Return:

[
   [5,4,11,2],
   [5,8,4,5]
]
*/
// my dfs solution
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        // using dfs to solve the problem
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        dfs(res, root, new ArrayList<>(), sum, 0);
        return res;
    }

    private void dfs(List<List<Integer>> result, TreeNode currentNode, List<Integer> current, int sum, int previousSum) {
        if (previousSum == sum && current.size() > 0 && currentNode == null) {
            result.add(new ArrayList<>(current));
        }
        if (currentNode == null) {
            return;
        }
        current.add(currentNode.val);
        if (currentNode.left != null && currentNode.right != null) {
            dfs(result, currentNode.left, current, sum, previousSum + currentNode.val);
            dfs(result, currentNode.right, current, sum, previousSum + currentNode.val);
        } else if (currentNode.left != null) {
            dfs(result, currentNode.left, current, sum, previousSum + currentNode.val);
        } else {
            dfs(result, currentNode.right, current, sum, previousSum + currentNode.val);
        }
        current.remove(current.size() - 1);
    }
}

// similar, but more concise, stack solution
public class Solution {
    private List<List<Integer>> resultList = new ArrayList<List<Integer>>();

    public void pathSumInner(TreeNode root, int sum, Stack<Integer>path) {
        path.push(root.val);
        if(root.left == null && root.right == null)
            if(sum == root.val) resultList.add(new ArrayList<Integer>(path));
        if(root.left!=null) pathSumInner(root.left, sum-root.val, path);
        if(root.right!=null)pathSumInner(root.right, sum-root.val, path);
        path.pop();
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root==null) return resultList;
        Stack<Integer> path = new Stack<Integer>();
        pathSumInner(root, sum, path);
        return resultList;
    }
}
