/*
There are G people in a gang, and a list of various crimes they could commit.

The i-th crime generates a profit[i] and requires group[i] gang members to participate.

If a gang member participates in one crime, that member can't participate in another crime.

Let's call a profitable scheme any subset of these crimes that generates at least P profit, and the total number of gang members participating in that subset of crimes is at most G.

How many schemes can be chosen?  Since the answer may be very large, return it modulo 10^9 + 7.
*/

// 这个背包问题其实很明显，能想到，主要是具体来说怎么去写这个knapsack, 2D 背包
// Well, it is a Knapsack problem and my first intuition is dp.
//
// dp[i][j] means the count of schemes with i profit and j members.
//
// The dp equation is simple here:
// dp[i + p][j + g] += dp[i][j]) if i + p < P
// dp[P][j + g] += dp[i][j]) if i + p >= P
//
// Don't forget to take care of overflow.
//
// For each pair (p, g) of (profit, group), I update the count in dp.
//
// Time Complexity:
// O(NPG)


public int profitableSchemes(int G, int P, int[] group, int[] profit) {
    int[][] dp = new int[P + 1][G + 1];
    dp[0][0] = 1;
    int res = 0, mod = (int)1e9 + 7;
    for (int k = 0; k < group.length; k++) {
        int g = group[k], p = profit[k];
        for (int i = P; i >= 0; i--)
            for (int j = G - g; j >= 0; j--)
                dp[Math.min(i + p, P)][j + g] = (dp[Math.min(i + p, P)][j + g] + dp[i][j]) % mod;
    }
    for (int x : dp[P]) res = (res + x) % mod;
    return res;
}
