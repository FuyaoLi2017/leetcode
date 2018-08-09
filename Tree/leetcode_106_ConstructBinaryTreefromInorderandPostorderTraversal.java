// similar solution with #105
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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return helper(postorder.length-1, 0, inorder.length-1, postorder, inorder);
    }
    public TreeNode helper(int preStart, int inStart, int inEnd, int[] postorder, int[] inorder){
        if(preStart < 0 || inStart > inEnd){
            return null;
        }
        TreeNode root = new TreeNode(postorder[preStart]);
        int inIndex = 0;
        for(int i = inStart; i <= inEnd; i++){
            if(inorder[i] == root.val){
                inIndex = i;
            }
        }
        // preorder is the backward sequence compared with preorder
        /** compare to see the difference
        *  root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder);
        *  root.right = helper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);
        */
        root.left = helper(preStart-(inEnd-inIndex)-1, inStart, inIndex-1, postorder, inorder);
        root.right = helper(preStart-1, inIndex+1, inEnd, postorder, inorder);
        return root;
    }
}
