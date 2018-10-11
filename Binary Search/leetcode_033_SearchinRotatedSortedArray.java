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
