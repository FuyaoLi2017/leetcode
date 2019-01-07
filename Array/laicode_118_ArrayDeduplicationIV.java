/*
Given an unsorted integer array, remove adjacent duplicate elements repeatedly, from left to right. For each group of elements with the same value do not keep any of them.

Do this in-place, using the left side of the original array. Return the array after deduplication.

Assumptions

The given array is not null
Examples

{1, 2, 3, 3, 3, 2, 2} → {1, 2, 2, 2} → {1}, return {1}
*/
public class Solution {
  public int[] dedup(int[] array) {
    // assume input is not null
    int end = -1;
    // use the left part of the array as a stack
    // the top of the stack is end
    for (int i = 0; i < array.length; i++) {
      if (end == -1 || array[end] != array[i]) {
        array[++end] = array[i];
      } else {
        while (i + 1 < array.length && array[i + 1] == array[end]) {
          i++;
        }
        end--;
      }
    }
    return Arrays.copyOf(array, end + 1);
  }
}
