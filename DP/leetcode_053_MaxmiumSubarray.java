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

// Divid and Conquer
class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return maxSubArray(nums, 0, nums.length-1);
    }

    private int maxSubArray(int[] nums, int left, int right) {
        if (left > right) return Integer.MIN_VALUE;
        if (left == right) return nums[left];
        int mid = (left + right) / 2;
        int L = maxSubArray(nums, left, mid-1);
        int R = maxSubArray(nums, mid+1, right);
        int leftSum = 0;
        int tmp = 0;
        for (int i=mid-1; i>=left; i--) {
            tmp += nums[i];
            if (tmp > leftSum) leftSum = tmp;
        }
        tmp = 0;
        int rightSum = 0;
        for (int i=mid+1; i<=right; i++) {
            tmp += nums[i];
            if (tmp > rightSum) rightSum = tmp;
        }
        return Math.max(Math.max(L, R), leftSum + rightSum + nums[mid]);
    }
}
