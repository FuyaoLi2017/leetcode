/*
Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
Example 2:

Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
*/

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        // Arrays.sort(candidates);
        // index: added elements must after this index; cur: the current sum of the elements
        dfs(candidates, target, res, 0, new ArrayList<Integer>());
        return res;
    }

    private void dfs(int[] candidates, int target, List<List<Integer>> res, int index, List<Integer> cur) {
        if (target < 0) return;
        else if (target == 0) {
            res.add(new ArrayList(cur));
        }
        else {
            for (int i = index; i < candidates.length; i++) {
                // first add element and then update sum
                cur.add(candidates[i]);
                System.out.println(candidates[i]);
                dfs(candidates, target - candidates[i], res, i, cur);
                // backtracking
                cur.remove(cur.size() - 1);
            }
        }
    }
}
