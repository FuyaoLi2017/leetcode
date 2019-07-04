/*
Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Note:

All numbers will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]
Example 2:

Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]
*/
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, new ArrayList<>(), 1, 0, k, n);
        return result;
    }

    private void dfs(List<List<Integer>> result, List<Integer> cur, int index, int currentSum, int k, int target) {
        if (cur.size() >= k) {
            if (currentSum != target) return;
            else {
                result.add(new ArrayList<>(cur));
                return;
            }
        }
        for (int i = index; i <= 9; i++) {
            cur.add(i);
            dfs(result, cur, i + 1, currentSum + i, k, target);
            cur.remove(cur.size() - 1);
        }
    }
}
