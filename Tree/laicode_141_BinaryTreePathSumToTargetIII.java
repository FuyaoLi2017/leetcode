/*
Given a binary tree in which each node contains an integer number. Determine if there exists a path (the path can only be from one node to itself or to any of its descendants), the sum of the numbers on the path is the given target number.

Examples

    5

  /    \

2      11

     /    \

    6     14

  /

 3

If target = 17, There exists a path 11 + 6, the sum of the path is target.

If target = 20, There exists a path 11 + 6 + 3, the sum of the path is target.

If target = 10, There does not exist any paths sum of which is target.

If target = 11, There exists a path only containing the node 11.

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
//
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
 // O(n^2)
public class Solution {
  public boolean exist(TreeNode root, int target) {
    if (root == null) {
      return false;
    }
    // pass down the prefix of the path
    List<TreeNode> path = new ArrayList<TreeNode>();
    return helper(root, path, target);
  }

  private boolean helper(TreeNode root, List<TreeNode> path, int sum) {
    path.add(root);
    int tmp = 0;
    for (int i = path.size() - 1; i >= 0; i--) {
      tmp += path.get(i).key;
      if (tmp == sum) {
        return true;
      }
    }
    if (root.left != null && helper(root.left, path, sum)) {
      return true;
    }
    if (root.right != null && helper(root.right, path, sum)) {
      return true;
    }
    // don't forget for the cleanup when return to the previous level.
    path.remove(path.size() - 1);
    return false;
  }
}

// O(n)
public class Solution {
  public boolean exist(TreeNode root, int target) {
    if (root == null) {
      return false;
    }
    Set<Integer> prefixSums = new HashSet<>();
    prefixSums.add(0);
    return helper(root, prefixSums, 0, target);
  }

  private boolean helper(TreeNode root, Set<Integer> prefixSums, int prevSum, int sum) {
    prevSum += root.key;
    // check if it contains the value path
    if (prefixSums.contains(prevSum - sum)) {
      return true;
    }
    boolean needRemove = prefixSums.add(prevSum);
    if (root.left != null && helper(root.left, prefixSums, prevSum, sum)) {
      return true;
    }
    if (root.right != null && helper(root.right, prefixSums, prevSum, sum)) {
      return true;
    }
    if (needRemove) {
      prefixSums.remove(prevSum);
    }
    return false;
  }
}
