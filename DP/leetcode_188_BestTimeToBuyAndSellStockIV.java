/*
Say you have an array for which the i-th element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
*/

class Solution {
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (k >= len / 2) return quickSolve(prices);

        // DP: t(i,j) is the max profit for up to i transactions by time j (0<=i<=K, 0<=j<=T).
        int[][] t = new int[k + 1][len];
        for (int i = 1; i <= k; i++) {
            int tmpMax =  -prices[0];
            for (int j = 1; j < len; j++) {
                t[i][j] = Math.max(t[i][j - 1], prices[j] + tmpMax);
                // tmpMax means the maximum profit of just doing at most i-1 transactions
                // using at most first j-1 prices
                // and buying the stock at price[j] - this is used for the next loop.
                tmpMax =  Math.max(tmpMax, t[i - 1][j - 1] - prices[j]);
            }
        }
        return t[k][len - 1];
    }


    private int quickSolve(int[] prices) {
        int len = prices.length, profit = 0;
        for (int i = 1; i < len; i++)
            // as long as there is a price gap, we gain a profit.
            if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
        return profit;
    }
}


// rolling 的做法
class Solution {
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length ==0 || k <= 0) {
            return 0;
        }
        int len = prices.length;
        if (k >= len / 2) {
            int result = 0;
            for (int i = 1; i < len; ++i) {
                result += prices[i] > prices[i - 1] ? prices[i] - prices[i - 1] : 0;
            }
            return result;
        }
        int[] buy = new int[k + 1];
        int[] sell= new int[k + 1];
        Arrays.fill(buy, Integer.MIN_VALUE);
        for (int price : prices) {
            for (int i = 1; i <= k; ++i) {
                buy[i] = Math.max(buy[i], sell[i - 1] - price);
                sell[i] = Math.max(sell[i], buy[i] + price);
            }
        }
        return sell[k];
    }
}
