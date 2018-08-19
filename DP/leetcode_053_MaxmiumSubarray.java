// DP solution
class Solution {
public int maxSubArray(int[] A) {
        int n = A.length;
        int[] dp = new int[n];//dp[i] means the maximum subarray ending with A[i];
        dp[0] = A[0];
        int max = dp[0];

        for(int i = 1; i < n; i++){
            dp[i] = A[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
            // update the result, compare with the previous maximum value, in this way to keep track of the maxmium value
            max = Math.max(max, dp[i]);
        }

        return max;
}
}
class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        //dp[i] means the maximum subarray ending with A[i];
        int[] dp = new int[n];
        dp[0] = nums[0];
        int max = nums[0];
        // be careful about the start point, i = 1, or the index will be out of bound
        for(int i = 1; i < nums.length; i++){
            dp[i] = nums[i] + (dp[i-1] > 0 ? dp[i-1] : 0);
            // update the result, compare with the previous maximum value, in this way to keep track of the maxmium value
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
