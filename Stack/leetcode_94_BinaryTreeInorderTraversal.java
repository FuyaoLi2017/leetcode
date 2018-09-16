/*
Given a binary tree, return the inorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]
Follow up: Recursive solution is trivial, could you do it iteratively?
*/
// recursive method
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
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            helper(root, res);
            return res;
        }

        public void helper(TreeNode root, List<Integer> res){
            if(root != null){
                if(root.left != null){
                    helper(root.left, res);
                }
            res.add(root.val);
            if(root.right != null){
                helper(root.right, res);
            }
        }
    }
}

// iterative method using stack
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 /*
 利用stack
 （1）当前元素非空/stack非空时，将curr push进入stack, curr坐标移动至curr.left
 (2)当不满足上述条件时候，pop()出curr, 将元素值加入res,同时将curr指向right支
 */
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while(curr != null || !stack.isEmpty()){
            while(curr != null){
            stack.push(curr);
            curr = curr.left;
            }
        curr = stack.pop();
        res.add(curr.val);
        curr = curr.right;
        }
        return res;
    }
}
