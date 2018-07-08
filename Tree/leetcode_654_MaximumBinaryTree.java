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
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if(nums == null) return null;
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int start, int end){
        if(start > end) return null;    //set the condition to jump out of the loop\
        int maxIndex = start;
        for(int i = start; i <= end; i++){
            if(nums[i] > nums[maxIndex]){
                maxIndex = i;
            }
        }
        TreeNode root = new TreeNode(nums[maxIndex]);
        //recursion, if the maxIndex is in the right part of last maxIndex, put it to the right child node
        root.left = build(nums, start, maxIndex - 1);
        //recursion, if the maxIndex is in the left part of last maxIndex, put it to the left child node
        root.right = build(nums, maxIndex + 1, end);
        return root;
    }
}
