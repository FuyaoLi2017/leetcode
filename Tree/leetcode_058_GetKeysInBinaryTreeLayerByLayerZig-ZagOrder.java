/*
Get the list of keys in a given binary tree layer by layer in zig-zag order.

Examples

        5

      /    \

    3        8

  /   \        \

 1     4        11

the result is [5, 3, 8, 11, 4, 1]

Corner Cases

What if the binary tree is null? Return an empty list in this case.
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
 // my solution,
public class Solution {
  public List<Integer> zigZag(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    // 用BFS, 在奇数层反序排列
    if (root == null)
      return res;
    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);
    int level = 1;
    while (!q.isEmpty()) {
      int size = q.size();
      int resSize = res.size();
      while (size > 0) {
        TreeNode cur = q.poll();
        if (cur != null)
          res.add(cur.key);
        if (cur.left != null)
          q.offer(cur.left);
        if (cur.right != null)
          q.offer(cur.right);
        size--;
      }
      if (level % 2 == 1) {
        reverse(res, resSize, res.size() - 1);
      }
      level++;
    }
    return res;
  }

   private void reverse(List<Integer> array, int start, int end) {
      // reverse the sequence between the array
      while (start < end) {
        int temp = array.get(start);
        array.set(start, array.get(end));
        array.set(end, temp);
        start++;
        end--;
      }
   }
}

// laicode answer
