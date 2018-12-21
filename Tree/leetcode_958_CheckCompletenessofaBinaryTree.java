/*
Given a binary tree, determine if it is a complete binary tree.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.


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
 // my solution
 public class Solution {
   public boolean isCompleted(TreeNode root) {
     if (root == null) return true;
     Queue<TreeNode> q = new LinkedList<>();
     q.offer(root);
     while (!q.isEmpty()) {
       TreeNode cur = q.poll();
       if (cur.left != null && cur.right != null) {
         q.offer(cur.left);
         q.offer(cur.right);
       } else if (cur.left == null && cur.right != null) {
         return false;
       } else {
         if(cur.left != null) {
           q.offer(cur.left);
         }
         while (!q.isEmpty()) {
           TreeNode lastLevel = q.poll();
           if (lastLevel.left != null || lastLevel.right != null) {
             return false;
           }
         }
         return true;
       }
     }
     return true;
   }
 }
 // tricky solution
class Solution {
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> bfs = new LinkedList<TreeNode>();
        bfs.offer(root);
        while (bfs.peek() != null) {
            TreeNode node = bfs.poll();
            bfs.offer(node.left);
            bfs.offer(node.right);
        }
        while (!bfs.isEmpty() && bfs.peek() == null)
            bfs.poll();
        return bfs.isEmpty();
    }
}

// general idea, level order
