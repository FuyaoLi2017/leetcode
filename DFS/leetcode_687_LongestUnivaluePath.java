/*
Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.

Note: The length of path between two nodes is represented by the number of edges between them.

Example 1:

Input:

              5
             / \
            4   5
           / \   \
          1   1   5
Output:

2
Example 2:

Input:

              1
             / \
            4   5
           / \   \
          4   4   5
Output:

2
Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.
*/
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
    int longest = 0;
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        helper(root);
        return longest;
    }

    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = 0;
        int right = 0;
        if (root.left != null) {
            if (root.left.val == root.val) {
                left = helper(root.left) + 1;
            } else {
                helper(root.left);
            }
        }
        if (root.right != null) {
            if (root.right.val == root.val) {
                right = helper(root.right) + 1;
            } else {
                helper(root.right);
            }
        }
        // update the longest
        longest = Math.max(longest, left + right);
        return Math.max(left, right);
    }
}
