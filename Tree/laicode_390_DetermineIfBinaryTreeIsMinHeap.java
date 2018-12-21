/*
Determine if the given binary tree is min heap.
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
  public boolean isMinHeap(TreeNode root) {
    if (root == null)
      return true;
    Deque<TreeNode> q = new ArrayDeque<>();
    q.offerFirst(root);

    while (!q.isEmpty()) {
      TreeNode cur = q.pollLast();
      if (cur.left != null && cur.right != null) {
        if (cur.left.key >= cur.key && cur.right.key >= cur.key) {
          q.offerFirst(cur.left);
          q.offerFirst(cur.right);
        } else {
          return false;
        }
      } else if (cur.left == null && cur.right != null) {  // their is a bubble in the binary tree -> return false
        return false;
      } else if(cur.left != null && cur.right == null){
        // a node just have it's left node, no right node, it should be the last one
        while (!q.isEmpty()) {
          TreeNode check = q.pollLast();
          if (check.left != null || check.right != null) {
            return false;
          }
        }
        return true;
      }
    }
    return true;
  }
}
