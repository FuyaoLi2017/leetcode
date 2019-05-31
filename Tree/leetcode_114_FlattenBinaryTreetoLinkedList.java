/*
Given a binary tree, flatten it to a linked list in-place.

For example, given the following tree:

    1
   / \
  2   5
 / \   \
3   4   6
The flattened tree should look like:

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6s
*/
// a recusive solution similar to post order
// https://leetcode.com/problems/flatten-binary-tree-to-linked-list/discuss/36977/My-short-post-order-traversal-Java-solution-for-share
public void flatten(TreeNode root) {
    flatten(root,null);
}
private TreeNode flatten(TreeNode root, TreeNode pre) {
    if(root==null) return pre;
    pre=flatten(root.right,pre);
    pre=flatten(root.left,pre);
    root.right=pre;
    root.left=null;
    pre=root;
    return pre;
}

// iterative solution
public void flatten(TreeNode root) {
	TreeNode cur = root;
	while (cur != null) {
		if (cur.left != null) {
			TreeNode last = cur.left;
			while (last.right != null) last = last.right;
			last.right = cur.right;
			cur.right = cur.left;
			cur.left = null;
		}
		cur = cur.right;
	}
}

// my solution
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
    public void flatten(TreeNode root) {
        if (root == null) return;
        List<TreeNode> list = new ArrayList<>();
        // first do a preorder
        preorder(root, list);
        // build the tree
        TreeNode prev = root;
        for (int i = 1; i < list.size(); i++) {
            prev.left = null;
            prev.right = list.get(i);
            prev = prev.right;
        }
    }

    private void preorder(TreeNode root, List<TreeNode> result) {
        if (root == null) {
            return;
        }
        result.add(root);
        preorder(root.left, result);
        preorder(root.right, result);
    }
}
