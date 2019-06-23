/*
https://leetcode.com/problems/find-in-mountain-array/
*/
/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */
// 借用lc 162， find peak element的思想重新在两侧进行二分
class Solution {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int low = 0;
        int high = mountainArr.length() - 1;
        System.out.println(high);
        while (low < high) {
            int mid1 = low + (high - low) / 2;
            int mid2 = mid1 + 1;
            if (mountainArr.get(mid1) > mountainArr.get(mid2)) {
                high = mid1;
            } else {
                low = mid2;
            }
        }
        int peak = low;
        if (target == mountainArr.get(peak)) return peak;

        // first search in the first half
        low = 0;
        high = peak;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (mountainArr.get(mid) > target) {
                high = mid - 1;
            } else if (mountainArr.get(mid) < target) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        // then search the second half
        low = peak;
        high = mountainArr.length() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (mountainArr.get(mid) > target) {
                low = mid + 1;
            } else if (mountainArr.get(mid) < target) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
