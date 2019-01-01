/*
Given an unsorted integer array, return any of the local minimum's index.

An element at index i is defined as local minimum when it is smaller than all its possible two neighbors a[i - 1] and a[i + 1]

(you can think a[-1] = +infinite, and a[a.length] = +infinite)

Assumptions:

The given array is not null or empty.
There are no duplicate elements in the array.
There is always one and only one result for each case.
*/
public class Solution {
  public int localMinimum(int[] array) {
    int left = 0;
    int right = array.length - 1;
    while (left + 1 < right) {
      int mid = left + (right - left) / 2;
      if (array[mid] < array[mid - 1] && array[mid] < array[mid + 1]) {
        return mid;
      } else if (array[mid] > array[mid - 1]) { // 相邻就退出，不存在arrayIndexOutOfBound
        right = mid - 1;
      } else {
        left = mid + 1;m
      }
    }
    // post processing
    if (array[left] < array[right]) {
      return left;
    }
    return right;
  }
}
