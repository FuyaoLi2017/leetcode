/*
Given a sorted integer array, remove duplicate elements. For each group of elements with the same value do not keep any of them. Do this in-place, using the left side of the original array and and maintain the relative order of the elements of the array. Return the array after deduplication.

Assumptions

The given array is not null
Examples

{1, 2, 2, 3, 3, 3} → {1}
*/


public class Solution {
  public int[] dedup(int[] array) {
    if (array.length <= 1) {
      return array;
    }
    // the position after i
    int end = 0;
    // preprocessing
    if (array[0] != array[1]) {
        end++;
      }
    for (int i = 1; i < array.length - 1; i++) {

      if (array[i - 1] < array[i] && array[i] < array[i + 1]) {
        array[end] = array[i];
        end++;
      }
    }
    // process the last node
    if (array[array.length - 1] != array[array.length - 2]) {
      array[end] = array[array.length - 1];
      end++;
    }
    int[] res = new int[end];
    for (int i = 0; i < end; i++) {
      res[i] = array[i];
    }
    return res;
  }
}
