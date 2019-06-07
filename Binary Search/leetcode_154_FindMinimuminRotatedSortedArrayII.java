/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

The array may contain duplicates.

Example 1:

Input: [1,3,5]
Output: 1
Example 2:

Input: [2,2,2,0,1]
Output: 0
Note:

This is a follow up problem to Find Minimum in Rotated Sorted Array.
Would allow duplicates affect the run-time complexity? How and why?
 => 需要向前寻找到底这个递增序列从哪里开始
*/
// a high vote O(n), similar to my solution
public int findMin(int[] nums) {

    int lo = 0, hi = nums.length - 1;
    while (lo < hi) {
        int mi = lo + (hi - lo) / 2;
        if (nums[mi] > nums[hi]) {
            lo = mi + 1;
        }
        else if (nums[mi] < nums[lo]) {
            hi = mi;
            lo++;
        }
        else { // nums[lo] <= nums[mi] <= nums[hi]
            hi--;
        }
    }

    return nums[lo];
}
// my solution, totally similar idea
class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 2. get the right bound number
            // (1) if the number is less than current mid, the minimum is at the right half => left = mid + 1
            // (2) if the number is equal to the current mid, go back and
            // find the largest number smaller than the right bound number, compare it
            // (i)if the number position is becoming smaller than the current mid
            // the minimum number should be to the right side, => left = mid + 1
            // (ii) if larger than current min, midNumber should be the result
            // (iii)if the number is smaller than current min, the minimum number should be to right side => left = mid + 1
            int midNumber = nums[mid];
            int rightNumber = nums[right];
            if (midNumber > rightNumber) {
                left = mid + 1;
            } else if (midNumber < rightNumber) {
                right = mid;
            } else {
                // find left most position of this number using binary search
                int cursor = right;
                while (cursor > mid) {
                    int current = nums[cursor];
                    if (current < midNumber) {
                        left = mid + 1;
                        break;
                    } else if (current > midNumber){
                        return midNumber;
                    }
                    cursor--;
                }
                // the cursor moved to mid, result should be on the left side
                if (cursor == mid) {
                    right = mid;
                }
            }
        }
        return nums[left];
    }
}
