/*
Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:

Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
Example:

Input: root = [4,2,5,1,3], target = 3.714286, and k = 2

    4
   / \
  2   5
 / \
1   3

Output: [4,3]
Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
*/
public class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new LinkedList<Integer>();
        helper(root, target, k, res);
        return res;
    }
    private void helper(TreeNode root, double target, int k, List<Integer> res) {
        if (root == null) {
            return;
        }
        // in order traversal
        helper(root.left,target,k,res);
        if (res.size()< k) {
            res.add(root.val);
        } else {
            if (Math.abs(res.get(0)-target) > Math.abs(root.val-target)) {
                res.remove(0);
                res.add(root.val);
            } else {
                return;
            }
        }
        helper(root.right,target,k,res);
    }
}
