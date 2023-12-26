/**
 * You have n dice, and each die has k faces numbered from 1 to k.
Given three integers n, k, and target, return the number of possible ways (out of the kn total ways) to roll the dice, so the sum of the face-up numbers equals target. Since the answer may be too large, return it modulo 109 + 7.
 */

// simple DFS won't pass
class Solution {
    public int numRollsToTarget(int n, int k, int target) {
        int mod = (int)Math.pow(10, 9) + 7;
        return dfs(n, k, target, mod) % mod;
    }

    private int dfs(int diceCount, int k, int target, int mod) {
        if (diceCount == 0 && target > 0) {
            return 0;
        }

        if (diceCount == 1 && target <= k) {
            return 1;
        }

        int curRes = 0;
        for (int i = 1; i <= k; i++) {
            int cur = dfs(diceCount - 1, k, target - i, mod) % mod;
            curRes += cur % mod;
        }
        return curRes;
    }
}

// top-down DP - similar to DFS - essentially DFS with memo
/**
 * Here, N is the number of dice, K is the number of faces each dice have, and T is the target value.

Time complexity: O(N * T * K)

Each state is defined by the diceIndex and the currSum. 
Hence, there will be N * T states, and in the worst, we must visit most of the states to solve the original problem. 
Each recursive call will require O(K)O(K)O(K) time as we iterate over the possible values from 111 to KKK. 
Therefore, the total time required will be equal to O(N * T * K).

Space complexity: O(N * T)

The memoization results are stored in the table memo with size N * T. 
Also, stack space in the recursion is equal to the maximum number of active functions. 
The maximum number of active functions will be at most NNN, i.e., one function call for every die. Hence, the space complexity is O(N * T).
 */
class Solution {
    final int MOD = 1000000007;
    
    int waysToTarget(Integer[][] memo, int diceIndex, int n, int currSum, int target, int k) {
        // All the n dice are traversed, the sum must be equal to target for valid combination
        if (diceIndex == n) {
            return currSum == target ? 1 : 0;
        }
        
        // We have already calculated the answer so no need to go into recursion
        if (memo[diceIndex][currSum] != null) {
            return memo[diceIndex][currSum];
        }
        
        int ways = 0;
        // Iterate over the possible face value for current dice
        for (int i = 1; i <= Math.min(k, target - currSum); i++) {
            ways = (ways + waysToTarget(memo, diceIndex + 1, n, currSum + i, target, k)) % MOD;
        }
        return memo[diceIndex][currSum] = ways;
    }
    
    public int numRollsToTarget(int n, int k, int target) {
        Integer[][] memo = new Integer[n + 1][target + 1];
        return waysToTarget(memo, 0, n, 0, target, k);
    }
}


// bottom-up approach
//  initialize memo[n][target] to 1,
// this is because there is only one way if no dice left and the sum is equal to target. 
class Solution {
    final int MOD = 1000000007;
    
    public int numRollsToTarget(int n, int k, int target) {
        int[][] memo = new int[n + 1][target + 1];
        // Intialize the base case
        memo[n][target] = 1;
        
        for (int diceIndex = n - 1; diceIndex >= 0; diceIndex--) {
            for (int currSum = 0; currSum <= target; currSum++) {
               int ways = 0;
                
                // Iterate over the possible face value for current dice
                for (int i = 1; i <= Math.min(k, target - currSum); i++) {
                    ways = (ways + memo[diceIndex + 1][currSum + i]) % MOD;
                }
                
                memo[diceIndex][currSum] = ways;
            }
        }
        
        return memo[0][0];
    }
}