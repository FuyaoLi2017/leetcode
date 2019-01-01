/*
Given an integer array of size N - 1 sorted by ascending order, containing all the numbers from 1 to N except one, find the missing number.

Assumptions

The given array is not null, and N >= 1
Examples

A = {1, 2, 4}, the missing number is 3
A = {1, 2, 3}, the missing number is 4
A = {}, the missing number is 1
*/
// O(N) solution
public class Solution {
  public int missing(int[] array) {
    for (int i = 0; i < array.length; i++) {
      if (array[i] != i + 1) {
        return i + 1;
      }
    }
    return array.length + 1;
  }
}

// my O(logn) solution
public class Solution {
  public int missing(int[] array) {
    if (array.length == 0) {
      return 1;
    }
    int left = 0;
    int right = array.length - 1;
    while (left < right) {
      int mid = left + (right - left) / 2;
      if (array[mid] == mid + 1 && array[mid + 1] == mid + 2) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }
    // post processing
    if (array[right] == right + 1) {
      return right + 2;
    }
    return right + 1;
  }
}


// instructor
public class Solution {
  public int missing(int[] array) {
    int left = 0;
    int right = array.length - 1;
    if (array.length == 0) {
      return 1;
    }
    if (array[right] == right + 1) {
      return array[right] + 1;
    }
    while (left < right) {
    	int mid = left + (right - left) / 2;
      if (array[mid] <= mid + 1) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }
    return array[right] - 1;
  }
}
