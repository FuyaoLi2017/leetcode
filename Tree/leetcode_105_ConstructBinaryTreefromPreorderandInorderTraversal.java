/**  前序遍历 preorder
*  https://baike.baidu.com/item/%E5%89%8D%E5%BA%8F%E9%81%8D%E5%8E%86/757319?fr=aladdin
* 中序遍历 inorder
* https://baike.baidu.com/item/%E4%B8%AD%E5%BA%8F%E9%81%8D%E5%8E%86/757281?fr=aladdin
* 后序遍历 postorder
* https://baike.baidu.com/item/%E5%90%8E%E5%BA%8F%E9%81%8D%E5%8E%86/1214806?fr=aladdin
**/
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
    return helper(0, 0, inorder.length - 1, preorder, inorder);
}

public TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
    if (preStart > preorder.length - 1 || inStart > inEnd) {
        return null;
    }
    // pick out the value of the nodes from preorder
    TreeNode root = new TreeNode(preorder[preStart]);
    int inIndex = 0; // Index of current root in inorder
    for (int i = inStart; i <= inEnd; i++) {
        if (inorder[i] == root.val) {
            inIndex = i;
        }
    }
    // figure out the recursive method
    root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder);
    root.right = helper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);
    return root;
}
}
