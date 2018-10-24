/*
Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
*/

// I implement the binary search three times and it got TLE, first confirm the existance and then calculate the right and left boundary
// using binary search respectively
// a triky solution, 实现C++里面的lower_bound, 先跑一次找到left, 然后跑一次（target + 1）,找到right bound
public class Solution {
	public int[] searchRange(int[] A, int target) {
		int start = Solution.firstGreaterEqual(A, target);
		if (start == A.length || A[start] != target) {
			return new int[]{-1, -1};
		}
		return new int[]{start, Solution.firstGreaterEqual(A, target + 1) - 1};
	}

	//find the first number that is greater than or equal to target.
	//could return A.length if target is greater than A[A.length-1].
	//actually this is the same as lower_bound in C++ STL.
	private static int firstGreaterEqual(int[] A, int target) {
		int low = 0, high = A.length;
		while (low < high) {
			int mid = low + ((high - low) >> 1);
			//low <= mid < high
			if (A[mid] < target) {
				low = mid + 1;
			} else {
				//should not be mid-1 when A[mid]==target.
				//could be mid even if A[mid]>target because mid<high.
				high = mid;
			}
		}
		return low;
	}
}

// given answer, use a boolean to find the left bond
class Solution {
    // returns leftmost (or rightmost) index at which `target` should be
    // inserted in sorted array `nums` via binary search.
    private int extremeInsertionIndex(int[] nums, int target, boolean left) {
        int lo = 0;
        int hi = nums.length;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] > target || (left && target == nums[mid])) {
                hi = mid;
            }
            else {
                lo = mid+1;
            }
        }

        return lo;
    }

    public int[] searchRange(int[] nums, int target) {
        int[] targetRange = {-1, -1};

        int leftIdx = extremeInsertionIndex(nums, target, true);

        // assert that `leftIdx` is within the array bounds and that `target`
        // is actually in `nums`.
        if (leftIdx == nums.length || nums[leftIdx] != target) {
            return targetRange;
        }

        targetRange[0] = leftIdx;
        targetRange[1] = extremeInsertionIndex(nums, target, false)-1;

        return targetRange;
    }
}
