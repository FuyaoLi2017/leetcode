/**
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.

On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can buy it then immediately sell it on the same day.

Find and return the maximum profit you can achieve.
 */

class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
        return 0;
        }
        int i = 0;
        int valley = prices[0];
        int peak = prices[0];
        int maxprofit = 0;
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1])
                i++;
            valley = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1])
                i++;
            peak = prices[i];
            maxprofit += peak - valley;
        }
        return maxprofit;
    }
}

// The optimal solution is to skip some calculations and directly go find valley and peak, my solution is to find min and reset the min.
// Need to skip some computations here to reach the optimal solution.
class MySolution {
    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;

        int profit = 0;
        int min = prices[0];

        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);

            if ((i < prices.length - 1 
            && prices[i] >= prices[i-1] && prices[i] >= prices[i+1]) || 
            (i == prices.length - 1 && prices[i] >= prices[i-1])) {
                if (min < prices[i]) {
                    profit += prices[i] - min;
                    min = Integer.MAX_VALUE;
                }
            }
        }

        return profit;
    }
}