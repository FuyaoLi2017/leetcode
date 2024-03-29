/*
162. Find Peak Element
Medium
A peak element is an element that is greater than its neighbors.

Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that nums[-1] = nums[n] = -∞.

Example 1:

Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.
Example 2:

Input: nums = [1,2,1,3,5,6,4]
Output: 1 or 5
Explanation: Your function can return either index number 1 where the peak element is 2,
             or index number 5 where the peak element is 6.
Note:

Your solution should be in logarithmic complexity.
*/

// recusive - narrow down the search range
public class Solution {
    public int findPeakElement(int[] nums) {
        return search(nums, 0, nums.length - 1);
    }
    public int search(int[] nums, int l, int r) {
        if (l == r)
            return l;
        int mid = (l + r) / 2;
        if (nums[mid] > nums[mid + 1])
            return search(nums, l, mid);
        return search(nums, mid + 1, r);
    }
}

// iterative - avoid overflow by starting condition
public class Solution {
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1])
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }
}

// my solution 1/10/2024
class Solution {
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length-1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            int midNum = nums[mid];
            
            int leftIndex = mid - 1;
            int rightIndex = mid + 1;
            
            boolean isPeak = true;
            if ((leftIndex >= 0 && nums[leftIndex] > midNum) 
                || (rightIndex < nums.length && nums[rightIndex] > midNum)){
                isPeak = false;
            }
            
            if (isPeak) return mid;
            else {
                if (rightIndex >= nums.length) { // corner case
                    right = mid - 1;
                }
                if (nums[rightIndex] > midNum) { // focus on right side
                    left = mid + 1;
                }
                else if (nums[rightIndex] < midNum) {
                    right = mid;
                }
            }
        }
        
        return right;
    }
}
