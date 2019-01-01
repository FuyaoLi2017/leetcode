/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
*/

// Analysis
// use binary search to find out the rotate point
// use int realmid = (mid + rotate) % nums.length; to recover the intital sequence of the array
// and apply binary search to find whether the target is in the array
// then we can get a O(log(n)) solution

class Solution {
    public int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if(nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
//         the point to split the two half is at low/high
//         we can use the value as rotate, to recover the origin array
        int rotate = low;
        low = 0;
        high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int realmid = (mid + rotate) % nums.length;
            if (nums[realmid] == target) return realmid;
            if (nums[realmid] < target) low = mid + 1;
            else high = mid - 1; //no same element, so mid - 1,not mid
        }
        return -1;
    }
}

// https://leetcode.windliang.cc/leetCode-33-Search-in-Rotated-Sorted-Array.html
// 参考解法三
// 一直更新有序的一段
public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (target == nums[mid]) {
                return mid;
            }
            //左半段是有序的
            if (nums[start] <= nums[mid]) {
                //target 在这段里
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            //右半段是有序的
            } else {
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }

        }
        return -1;
    }
