/*
Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
*/
// keep a previous max and min, since there could have negative numbers
// it will cause number to fluctuate between max and min
class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int maxProduct = nums[0];
        int minProduct = nums[0];
        int maxRes = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= 0) {
                maxProduct = Math.max(maxProduct * nums[i], nums[i]);
                minProduct = Math.min(minProduct * nums[i], nums[i]);
            } else {
                int temp = maxProduct;
                maxProduct = Math.max(minProduct * nums[i], nums[i]);
                minProduct = Math.min(temp * nums[i], nums[i]);
            }
            maxRes = Math.max(maxRes, maxProduct);
        }
        return maxRes;
    }
}
