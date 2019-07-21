/*
Given a sorted integer array without duplicates, return the summary of its ranges.

Example 1:

Input:  [0,1,2,4,5,7]
Output: ["0->2","4->5","7"]
Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.
Example 2:

Input:  [0,2,3,4,6,8,9]
Output: ["0","2->4","6","8->9"]
Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.
*/
class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        int cur = 0;
        int prev = 0;
        while (cur < nums.length) {
            int start = cur;
            while (cur < nums.length - 1 && nums[cur+1] == nums[cur] + 1) {
                cur++;
            }
            if (start < cur) result.add("" + nums[start] + "->" + nums[cur]);
            else result.add("" + nums[start]);
            cur++;
        }
        return result;
    }
}
