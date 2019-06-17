/*
Given a sorted integer array nums, where the range of elements are
in the inclusive range [lower, upper], return its missing ranges.

Example:

Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99,
Output: ["2", "4->49", "51->74", "76->99"]
*/
// 处理nums后面的那个数字更加简洁
// my solution
class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            if (lower == upper) result.add(""+lower);
            else result.add("" + lower + "->" + upper);
            return result;
        }
        int left = lower;

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i-1] == nums[i]) {

            }
            if (nums[i] > left) {
                if (nums[i] - left == 1) {
                    result.add("" + left);
                    left = nums[i] + 1;
                } else {
                    int rightBound = nums[i] - 1;
                    if (i > 0 && nums[i] > nums[i-1]) {
                        result.add("" + left + "->" + rightBound);
                    } else if (i == 0) {
                        result.add("" + left + "->" + rightBound);
                    }
                    left = nums[i] + 1;
                }
            } else {
                left = nums[i] + 1;
            }
        }

        // post processing
        if (nums[nums.length - 1] < upper) {
            if (nums[nums.length - 1] == upper - 1) {
                result.add("" + upper);
            } else {
                result.add("" + left + "->" + upper);
            }
        }

        return result;
    }
}
