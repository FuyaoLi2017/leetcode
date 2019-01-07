/*
Given a binary tree where all the right nodes are leaf nodes, flip it upside down and turn it into a tree with left leaf nodes as the root.

Examples

        1

      /    \

    2        5

  /   \

3      4

is reversed to

        3

      /    \

    2        4

  /   \

1      5

How is the binary tree represented?

We use the pre order traversal sequence with a special symbol "#" denoting the null node.

For Example:

The sequence [1, 2, #, 3, 4, #, #, #] represents the following binary tree:

    1

  /   \

 2     3

      /

    4
*/
// 右边的tree不进行调整！！！
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
 // recursive method
public class Solution {
  public TreeNode reverse(TreeNode root) {
    // the subproblem is to change the tree to the required shape
    // based on the current root
    if (root == null || root.left == null) {
      return root;
    }
    TreeNode newRoot = reverse(root.left);
    // assign new value to root.left's right node
    root.left.right = root.right;
    // assign new value to root.left's left node
    root.left.left = root;
    root.left = null;
    root.right = null;
    return newRoot;
  }
}

// iterative method
// iterative method
public class Solution {
  public TreeNode reverse(TreeNode root) {
    TreeNode prev = null;
    TreeNode prevRight = null;
    while (root != null) {
      TreeNode next = root.left;
      TreeNode right = root.right;
      // 本层的点是链接到上层的点的，下面这几行是应该做的事情
      root.right = prevRight;
      root.left = prev;
      prevRight = right;
      prev = root;
      root = next;
    }
    return prev;
  }
}
