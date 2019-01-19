/*
Find all pairs of elements in a given array that sum to the pair the given target number. Return all the distinct pairs of values.

Assumptions

The given array is not null and has length of at least 2
The order of the values in the pair does not matter
Examples

A = {2, 1, 3, 2, 4, 3, 4, 2}, target = 6, return [[2, 4], [3, 3]]
*/
public class Solution {
  public List<List<Integer>> allPairs(int[] array, int target) {
    Arrays.sort(array);
    List<List<Integer>> result = new ArrayList<>();
    int left = 0;
    int right = array.length - 1;
    //!!! 注意只能左边的cursor移动，2侧都移动就会忽略可以跟后面重复构成结果
    while (left < right) {
      // ignore all the consecutive duplicate values when we want
      // to determine the smaller element of the pair
      if (left > 0 && array[left] == array[left - 1]) {
        left++;
        continue;
      }
      int cur = array[left] + array[right];
      if (cur == target) {
        result.add(Arrays.asList(array[left], array[right]));
        left++;
        right--;
      } else if (cur < target) {
        left++;
      } else {
        right--;
      }
    }
    return result;
  }
}
