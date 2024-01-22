/*
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.
*/

class Solution {
    public int rob(int[] num) {
        int prevMax = 0;                             //the maxmium value not considering the current house
        int currMax = 0;                             //the maxmium value considering the current house
        for (int x : num) {                          //go through every number
            int temp = currMax;                      //temp is the value that not consider the current house
            currMax = Math.max(prevMax + x, currMax);//dp solution:plus the current house / leave the previous total value
            prevMax = temp;                          //update the previous value
        }
        return currMax;
    }
}

// my solution
class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        int res = 0;

        for (int i = 0; i < nums.length; i++) {
            int candidate1 = (i >= 3) ? dp[i-3] : 0;
            int candidate2 = (i >= 2) ? dp[i-2] : 0;
            int candidate3 = (i >= 2) ? dp[i-1] : 0;

            dp[i] = Math.max(Math.max(candidate1 + nums[i], candidate2 + nums[i]), candidate3);
            res = Math.max(res, dp[i]);
        }

        return res;
    }
}

// editorial solution dp

class Solution {
    
    public int rob(int[] nums) {
        
        int N = nums.length;
        
        // Special handling for empty array case.
        if (N == 0) {
            return 0;
        }
        
        int[] maxRobbedAmount = new int[nums.length + 1];
        
        // Base case initializations.
        maxRobbedAmount[N] = 0;
        maxRobbedAmount[N - 1] = nums[N - 1];
        
        // DP table calculations.
        for (int i = N - 2; i >= 0; --i) {
            
            // Same as the recursive solution.
            maxRobbedAmount[i] = Math.max(maxRobbedAmount[i + 1], maxRobbedAmount[i + 2] + nums[i]);
        }
        
        return maxRobbedAmount[0];
    }
}

// optimized DP
class Solution {
    
    public int rob(int[] nums) {
        
        int N = nums.length;
        
        // Special handling for empty array case.
        if (N == 0) {
            return 0;
        }
        
        int robNext, robNextPlusOne;
        
        // Base case initializations.
        robNextPlusOne = 0;
        robNext= nums[N - 1];
        
        // DP table calculations. Note: we are not using any
        // table here for storing values. Just using two
        // variables will suffice.
        for (int i = N - 2; i >= 0; --i) {
            
            // Same as the recursive solution.
            int current = Math.max(robNext, robNextPlusOne + nums[i]);
            
            // Update the variables
            robNextPlusOne = robNext;
            robNext = current;
        }
        
        return robNext;
    }
}

// fixed the wrong solution, I skipped the second element, it should also be considered in DP calculation

class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = Math.max(nums[0], nums[1]);
        // previously directly initialize dp[1] to be nums[1]
        dp[1] = res;

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
            res = Math.max(dp[i], res);
        }

        return res;
    }
}