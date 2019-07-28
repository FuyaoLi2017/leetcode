/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

 // recursive method top down approach
 /*
 For the current node root, calling depth() for its left and right children actually has to access all of its children, thus the complexity is O(N).
 We do this for each node in the tree, so the overall complexity of isBalanced will be O(N^2). This is a top down approach.
 */
class Solution {
    public int depth(TreeNode root){
        if(root == null) return 0;
        return Math.max(depth(root.right), depth(root.left)) + 1;
    }
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        int left  = depth(root.left);
        int right = depth(root.right);
        return Math.abs(left-right) <= 1 ? (isBalanced(root.right) && isBalanced(root.left)) : false;

// DFS method bottom up approach
/*
The second method is based on DFS. Instead of calling depth() explicitly for each child node, we return the height of the current node in DFS recursion.
When the sub tree of the current node (inclusive) is balanced, the function dfsHeight() returns a non-negative value as the height. Otherwise -1 is returned.
According to the leftHeight and rightHeight of the two children, the parent node could check if the sub tree
is balanced, and decides its return value.
In this bottom up approach, each node in the tree only need to be accessed once.
Thus the time complexity is O(N), better than the first solution.
*/
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
    public int dfsHeight(TreeNode root){
        if(root == null) return 0;
        int leftHeight = dfsHeight(root.left);
        if(leftHeight == -1) return -1;   //把-1回溯到最开始的root
        int rightHeight = dfsHeight(root.right);
        if(rightHeight == -1) return -1;  //把-1回溯到最开始的root
        if(Math.abs(leftHeight - rightHeight) > 1) return -1;
        return Math.max(leftHeight, rightHeight) + 1;
    }
    public boolean isBalanced(TreeNode root) {
        return dfsHeight(root) != -1;
    }
}
