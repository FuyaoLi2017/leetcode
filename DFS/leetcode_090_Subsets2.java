/*
Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
*/
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // need to be sorted to avoid duplicate at first
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        dfs(res, new ArrayList<>(), 0, nums);
        return res;
    }

    private void dfs(List<List<Integer>> result, List<Integer> current, int index, int[] nums) {
        result.add(new ArrayList<>(current));
        for (int i = index; i < nums.length; i++) {
            /*
            two functions for the skip loop below:
            1. Avoid index out of bound for getting nums[i-1] operation.
            2. only skip the iteration when the duplicated elements has been counted.
            (only two cases exist, i == index and i > index, the case when i == index will
            only happen once and this time, it will be considered, otherwise, it will not be considered. )
            */
            if (i > index && nums[i - 1] == nums[i]) {
                continue;
            }
            current.add(nums[i]);
            dfs(result, current, i + 1, nums);
            current.remove(current.size() - 1);
        }
    }
}


/*
when
*/
