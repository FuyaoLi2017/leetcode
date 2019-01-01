/*
Search for a target number in a bitonic array, return the index of the target number if found in the array, or return -1.

A bitonic array is a combination of two sequence: the first sequence is a monotonically increasing one and the second sequence is a monotonically decreasing one.

Assumptions:

The array is not null.
Examples:

array = {1, 4, 7, 11, 6, 2, -3, -8}, target = 2, return 5.
*/
// my solution, just find the peak and do binary search in both sides


// 1. Find the bitonic point in the given array, i.e the maximum element in the given bitonic array. This can be done in log(n) time by modifying the binary search algorithm. You can refer to this post on how to do this.
// 2. If the element to be searched is equal to the element at bitonic point then print the index of bitonic point.
// 3. If the element to be searched is greater than element at bitonic point then element does not exist in the array.
// 4. If the element to be searched is less than element at bitonic point then search for element in both half of the array using binary search.
public class Solution {
  public int search(int[] array, int target) {
    if (array.length == 0) {
      return -1;
    }
    // find the max value point
    int left = 0;
    int right = array.length - 1;
    while (left + 1 < right) {
      int mid = left + (right - left) / 2;
      if (array[mid] < array[mid + 1]) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }
    int peak = left;
    // no requirement for for which position of target to return?
    left = 0;
    right = peak;
    // could do some preprocessing
    // find the real peak value, at left or right. If it is smaller than target,
    // no answer will be found
    // the monotonically increasing part
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (array[mid] == target) {
        return mid;
      } else if (array[mid] < target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    // the  monotonically decreasing part
    left = peak + 1;
    right = array.length - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (array[mid] == target) {
        return mid;
      } else if (array[mid] < target) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return -1;
  }
}
