/*
https://leetcode.com/problems/find-peak-element/
*/
// 直接对比检查下一个element来确保正确性
class Solution {
    public int findPeakElement(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int mid1 = low + (high - low) / 2;
            int mid2 = mid1 + 1;
            if (nums[mid1] > nums[mid2]) {
                high = mid1;
            } else {
                low = mid2;
            }
        }
        return low;
    }
}
