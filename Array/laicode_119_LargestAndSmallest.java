/*
Use the least number of comparisons to get the largest and smallest number in the given integer array. Return the largest number and the smallest number.

Assumptions

The given array is not null and has length of at least 1
Examples

{2, 1, 5, 4, 3}, the largest number is 5 and smallest number is 1. return [5, 1].
*/
public class Solution {
  // 这种解法一开始把array分成了两堆，然后narrow down了比较的range.
  // 一共需要n/2(分成两堆)+n/2+n/2=1.5n的比较。而正常比较的话需要loop两遍。会需要2n次比较
  // 如果再继续细分并不能减少比较的次数
  public int[] largestAndSmallest(int[] array) {
    int n = array.length;
    for (int i = 0; i < n / 2; i++) {
      if (array[i] < array[n - 1 - i]) {
        swap(array, i, n - 1 - i);
      }
    }
    return new int[] {largest(array, 0, (n - 1) / 2), smallest(array, n / 2, n - 1)};
  }

  private int largest(int[] array, int left, int right) {
    int largest = array[left];
    for (int i = left + 1; i <= right; i++) {
      largest = Math.max(largest, array[i]);
    }
    return largest;
  }

  private int smallest(int[] array, int left, int right) {
    int smallest = array[left];
    for (int i = left + 1; i <= right; i++) {
      smallest = Math.min(smallest, array[i]);
    }
    return smallest;
  }

  private void swap(int[] array, int i, int j) {
    int tmp = array[i];
    array[i] = array[j];
    array[j] = tmp;
  }
}
