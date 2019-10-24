/*
Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note:

You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
*/


// Top down approach using DP
// To deal with the edges of our array we can reframe the problem into only bursting the non-edge balloons and adding ones on the sides.
//
// We define a function dp to return the maximum number of coins obtainable on the open interval (left, right).
// Our base case is if there are no integers on our interval (left + 1 == right), and therefore there are no more balloons that can be added.
// We add each balloon on the interval, divide and conquer the left and right sides, and find the maximum score.
//
// The best score after adding the ith balloon is given by:
//
// nums[left] * nums[i] * nums[right] + dp(left, i) + dp(i, right)
// nums[left] * nums[i] * nums[right] is the number of coins obtained from adding the ith balloon,
// and dp(left, i) + dp(i, right) are the maximum number of coins obtained from solving the left and right sides of that balloon respectively.
class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length + 2;
        int[] new_nums = new int[n];

        // fill the middle of the array with input, start and end is 1
        for(int i = 0; i < nums.length; i++){
            new_nums[i+1] = nums[i];
        }

        new_nums[0] = new_nums[n-1] = 1;

        int[][] memo = new int[n][n];

        return dp(memo, new_nums, 0, n-1);
    }

    public int dp(int[][] memo, int[] nums, int left, int right) {
        // no more balloons can be added
        if(left+1 == right) return 0;
        if(memo[left][right] > 0) return memo[left][right];

        int ans = 0;

        for(int i= left+1; i < right; i++){
            ans = Math.max(ans, nums[left] * nums[i] * nums[right]
                           + dp(memo, nums, left, i) + dp(memo, nums, i, right));
        }

        // add current result to cache
        memo[left][right] = ans;
        return ans;
    }
}


// Bottom up approach using DP
// the method of thinking is the same as the top down approach, we just select the left and right bound using
// two for loop and then select choices in the middle, pay attention to tthe range of the three for loops
public class Solution{

    public int maxCoins(int[] nums) {
        // reframe the problem
        int n = nums.length + 2;
        int[] new_nums = new int[n];

        for(int i = 0; i < nums.length; i++){
            new_nums[i+1] = nums[i];
        }

        new_nums[0] = new_nums[n - 1] = 1;

        // dp will store the results of our calls
        int[][] dp = new int[n][n];

        // iterate over dp and incrementally build up to dp[0][n-1]
        for (int left = n-2; left > -1; left--)
            for (int right = left+2; right < n; right++) {
                for (int i = left + 1; i < right; ++i)
                    // same formula to get the best score from (left, right) as before
                    dp[left][right] = Math.max(dp[left][right],
                    new_nums[left] * new_nums[i] * new_nums[right] + dp[left][i] + dp[i][right]);
            }

        return dp[0][n - 1];
    }
}
