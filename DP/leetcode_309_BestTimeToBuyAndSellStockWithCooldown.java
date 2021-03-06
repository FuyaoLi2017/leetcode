/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:

Input: [1,2,3,0,2]
Output: 3
Explanation: transactions = [buy, sell, cooldown, buy, sell]
*/

// state machine DP algorithm
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/discuss/75928/Share-my-DP-solution-(By-State-Machine-Thinking)
// s0[i] = max(s0[i - 1], s2[i - 1]); // Stay at s0, or rest from s2
// s1[i] = max(s1[i - 1], s0[i - 1] - prices[i]); // Stay at s1, or buy from s0
// s2[i] = s1[i - 1] + prices[i]; // Only one way from s1
class Solution {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length <= 1) return 0;
        int len =prices.length;
        int[] s0 = new int[len];
        int[] s1 = new int[len];
        int[] s2 = new int[len];

        // intialize values
        s1[0] = -prices[0];
        s0[0] = 0;
        s2[0] = Integer.MIN_VALUE;


        for(int i = 1; i < len; i++){
            s0[i] = Math.max(s0[i-1], s2[i-1]);
            s1[i] = Math.max(s1[i-1], s0[i-1] - prices[i]);
            s2[i] = s1[i-1] + prices[i];
        }

        return Math.max(s0[len-1], s2[len-1]);
    }
}

// Not fully understand, need to learn more
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/discuss/75927/Share-my-thinking-process
public int maxProfit(int[] prices) {
    int sell = 0, prev_sell = 0, buy = Integer.MIN_VALUE, prev_buy;
    for (int price : prices) {
        prev_buy = buy;
        buy = Math.max(prev_sell - price, prev_buy);
        prev_sell = sell;
        sell = Math.max(prev_buy + price, prev_sell);
    }
    return sell;
}
