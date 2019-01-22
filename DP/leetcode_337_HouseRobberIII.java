/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// 最为直接的想法
class Solution {
    public int rob(TreeNode root) {
        if (root == null)
            return 0;
        // if we don't rob the current root
        int notRob = rob(root.left) + rob(root.right);
        // if we rob the current node
        int left = 0, right = 0;
        if (root.left != null) {
            left = rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
            right = rob(root.right.left) + rob(root.right.right);
        }
        int rob = left + right + root.val;
        return Math.max(rob, notRob);
    }
}

// 加上hashmap保存看过的点
class Solution {
    public int rob(TreeNode root) {
    return robSub(root, new HashMap<>());
}

private int robSub(TreeNode root, Map<TreeNode, Integer> map) {
    if (root == null) return 0;
    if (map.containsKey(root)) return map.get(root);

    int val = 0;

    if (root.left != null) {
        val += robSub(root.left.left, map) + robSub(root.left.right, map);
    }

    if (root.right != null) {
        val += robSub(root.right.left, map) + robSub(root.right.right, map);
    }

    val = Math.max(val + root.val, robSub(root.left, map) + robSub(root.right, map));
    map.put(root, val);

    return val;
}
}

// DP, 定义返回值的结构
class Solution {
public int rob(TreeNode root) {
    int[] res = robSub(root);
    return Math.max(res[0], res[1]);
}

private int[] robSub(TreeNode root) {
    if (root == null) return new int[2];

    int[] left = robSub(root.left);
    int[] right = robSub(root.right);
    int[] res = new int[2];

    res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
    res[1] = root.val + left[0] + right[0];

    return res;
}
}
