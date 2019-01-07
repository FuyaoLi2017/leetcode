/*
Given K nodes in a binary tree, find their lowest common ancestor.

Assumptions

K >= 2

There is no parent pointer for the nodes in the binary tree

The given K nodes are guaranteed to be in the binary tree

Examples

        5

      /   \

     9     12

   /  \      \

  2    3      14

The lowest common ancestor of 2, 3, 14 is 5

The lowest common ancestor of 2, 3, 9 is 9
*/
/**
 * public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * }
 */
 public class Solution {
   public TreeNode lowestCommonAncestor(TreeNode root, List<TreeNode> nodes) {
     Set<TreeNode> set = new HashSet<>(nodes);
     return helper(root, set);
   }

   private TreeNode helper(TreeNode root, Set<TreeNode> set) {
     if (root == null) {
       return null;
     }
     if (set.contains(root)) {
       return root;
     }
     TreeNode l = helper(root.left, set);
     TreeNode r = helper(root.right, set);
     if (l != null && r != null) {
       return root;
     }
     return l != null ? l : r;
   }
 }
