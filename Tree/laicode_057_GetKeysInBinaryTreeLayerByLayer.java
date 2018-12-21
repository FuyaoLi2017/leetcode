/*
Get the list of list of keys in a given binary tree layer by layer. Each layer is represented by a list of keys and the keys are traversed from left to right.

Examples

        5

      /    \

    3        8

  /   \        \

 1     4        11

the result is [ [5], [3, 8], [1, 4, 11] ]

Corner Cases

What if the binary tree is null? Return an empty list of list in this case.
How is the binary tree represented?

We use the level order traversal sequence with a special symbol "#" denoting the null node.

For Example:

The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

    1

  /   \

 2     3

      /

    4
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
  public List<List<Integer>> layerByLayer(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null)
      return res;
    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);
    while (!q.isEmpty()) {
      int size = q.size();       // 每次用一个size区维持长度就可以
      List<Integer> cur = new ArrayList<>();
      for (int i = 0; i < size; i++) {
        TreeNode n = q.poll();
        if (n.left != null)
          q.offer(n.left);
        if (n.right != null)
          q.offer(n.right);
        cur.add(n.key);
      }
      res.add(cur);
    }
    return res;
  }
}
