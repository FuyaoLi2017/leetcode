/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
*/

// a high-vote solution, update along the array
/*
From the chart and figure of variables in each step, we can see that lowestBuyPrice1
is always the lowest price in the input array, maxProfit1 keeps track of the biggest difference between prices and lowest price so far,
value change of lowestBuyPrice2 reflects the local valley in the input prices
array and variable maxProfit2 maintains the maximum profit until the current price.

lowestBuyPrice1 and maxProfit1 are easy to understand.
But how does lowestBuyPrice2 and maxProfit2 works?
First, we shall see that lowestBuyPrice2 decreases whenever we hit a local minimum price.
It indirectly (since it is negative) reflects the lowest price that is closest to the current price.
When the current price is bigger than -lowestBuyPrice2,
maxProfit2i = price i - (price (i-1) -maxProfit1 (i-1))= price i - price (i-1) +maxProfit1 (i-1),
which means the accrued maximum profit until now.
*/
public class Solution {
    public int maxProfit(int [] prices){
	    int maxProfit1 = 0;
	    int maxProfit2 = 0;
	    int lowestBuyPrice1 = Integer.MAX_VALUE;
	    int lowestBuyPrice2 = Integer.MAX_VALUE;

	    for(int p:prices){
	    	maxProfit2 = Math.max(maxProfit2, p-lowestBuyPrice2);
	    	lowestBuyPrice2 = Math.min(lowestBuyPrice2, p-maxProfit1);
            // taking the first transaction into account
	    	maxProfit1 = Math.max(maxProfit1, p-lowestBuyPrice1);
	    	lowestBuyPrice1 = Math.min(lowestBuyPrice1, p);
	    }
	    return maxProfit2;
    }
}

// my solution, very fast, usng dp
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int max = 0;
        int[] dp = new int[prices.length];
        int lowest = prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i] = max;
            if (prices[i] - lowest >= max) {
                max = prices[i] - lowest;
                dp[i] = max;
            }
            if (prices[i] < lowest) {
                lowest = prices[i];
            }
        }
        // if there are two transaction process
        int newMax = max;
        int secondMax = 0;
        int highest = prices[prices.length - 1];
        // cursor i is the starting point of the second transaction
        for (int i = prices.length - 2; i >= 2; i--) {
            if (highest - prices[i] > secondMax) {
                secondMax = highest - prices[i];
                if (secondMax + dp[i-1] > newMax) {
                    newMax = Math.max(newMax, secondMax + dp[i-1]);
                }
            }
            if (prices[i] > highest) {
                highest = prices[i];
            }
        }
        return newMax;
    }
}
