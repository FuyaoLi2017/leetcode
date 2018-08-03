// clear explanation
// https://leetcode.com/problems/unique-binary-search-trees/discuss/31666/DP-Solution-in-6-lines-with-explanation.-F(i-n)-G(i-1)-*-G(n-i)
class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = dp[1] = 1;
        // i represents the scale of the problem is i (dp[i])
        // j is the number of the elements which is smaller than the root,
        // we traverse i by j in the inner loop to find all possiblities in the the problem with scale i
        // we can use dp[j-1] and dp[i-j] to illustrate the scale of the left child side problem and right child side problem
        // the scale
        for(int i = 2; i <= n; i++){
            for(int j = 1; j <= i; j++){
                dp[i] += dp[j-1] * dp[i-j];
                // the sum is (j-1) and (i-j) is always (n-1)
            }
        }
        return dp[n];
    }
}
