/**
 * Given an array of integers arr, 
 * return true if the number of occurrences of each value in the array is unique 
 * or false otherwise.
 */

 class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        Set<Integer> set = new HashSet<>(map.values());
        return set.size() == map.size();
    }
}

// Improvement - instead of directly create a set, we can add numbers to the set 1 by 1, 
// when return value is false. We can return false directly.