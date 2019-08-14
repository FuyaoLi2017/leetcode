/*
Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

Note:
The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.

Example 1:

Input: nums = [1, -1, 5, -2, 3], k = 3
Output: 4
Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
Example 2:

Input: nums = [-2, -1, 2, 1], k = 1
Output: 2
Explanation: The subarray [-1, 2] sums to 1 and is the longest.
Follow Up:
Can you do it in O(n) time?
*/

// my solution
class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        int[] memo = new int[nums.length+1];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            memo[i+1] = sum;
        }
        // key: ending index, value: maxmium length of the array
        //Map<Integer, Integer> map = new HashMap<>();
        // [1, 0, 5, 3, 6]
        int max = 0;
        for (int i = nums.length-1; i >= 0; i--) {
            int cur = memo[i+1];
            int start = 0;
            while (start <= i) {
                if (cur - memo[start] == k) {
                    max = Math.max(max, i-start+1);
                }
                start++;
            }
        }
        return max;
    }
}

// a stanard solution, SUPER SIMILAR TO TWO SUM!!!!
class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        int sum = 0, max = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            if (sum == k) max = i + 1;
            else if (map.containsKey(sum - k)) max = Math.max(max, i - map.get(sum - k));
            if (!map.containsKey(sum)) map.put(sum, i);
        }
        return max;
    }
}
