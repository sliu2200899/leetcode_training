package com.jetbrains.master;

public class MaxStockProfit {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        // just maintain two variables maxprofit and minprice corresponding to
        // the maximum of the profit and minimum of the price.

        int maxprofit = 0, minprice = prices[0];
        for (int i = 1; i < prices.length; ++i) {
            maxprofit = Math.max(maxprofit, prices[i] - minprice);
            minprice = Math.min(minprice, prices[i]);
        }

        return maxprofit;
    }
}
