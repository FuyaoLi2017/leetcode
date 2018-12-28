/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.


The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

Example:

Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
*/
class Solution {
    public int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;    // 注意这个地方初始值的设置，最低肯定也是不会低于0的，所以设置为0，如果代入left, right的话可能index out of bound
        int rightMax = 0;
        int max = 0;
        while (left <= right) {
            // move left, right towards middle till they meet
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (leftMax <= rightMax) {
                max += leftMax - height[left];
                left++;
            } else {
                max += rightMax - height[right];
                right--;
            }
        }
        return max;
    }
}
