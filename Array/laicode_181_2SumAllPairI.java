/*
Find all pairs of elements in a given array that sum to the given target number. Return all the pairs of indices.

Assumptions

The given array is not null and has length of at least 2.

Examples

A = {1, 3, 2, 4}, target = 5, return [[0, 3], [1, 2]]

A = {1, 2, 2, 4}, target = 6, return [[1, 3], [2, 3]]
*/
public class Solution {
  public List<List<Integer>> allPairs(int[] array, int target) {
    List<List<Integer>> result = new ArrayList<>();
    // key: number, value: list of all possible indices
    Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
    for (int i = 0; i < array.length; i++) {
        // keep track of upper bound
      List<Integer> indices = map.get(target - array[i]);
      // i is the upper bound, get all possible pairs
      if (indices != null) {
        for (int j : indices) {
          result.add(Arrays.asList(j, i));
        }
      }
      // add current index i to all the possible indices for value of array[i]
      if (!map.containsKey(array[i])) {
        map.put(array[i], new ArrayList<Integer>());
      }
      map.get(array[i]).add(i);
    }
    return result;
  }
}
