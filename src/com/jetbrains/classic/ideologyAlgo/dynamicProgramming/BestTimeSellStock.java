package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

import java.util.PriorityQueue;

public class BestTimeSellStock {
    /*
        sell stock 1: just
        We need to find out the maximum difference (which will be the maximum profit) between two numbers in the given array.
        Also, the second number (selling price) must be larger than the first one (buying price).
        Example 1:

        Input: [7,1,5,3,6,4]
        Output: 5
        Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
             Not 7-1 = 6, as selling price needs to be larger than buying price.


        time: O(n)
        space: O(1)
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;

        for (int i = 0; i < prices.length; ++i) {
            maxprofit = Math.max(maxprofit, prices[i] - minprice);
            minprice = Math.min(minprice, prices[i]);
        }
        return maxprofit;
    }

    /*
        sell stock 2:
        You may complete as many transactions as you like
        You may not engage in multiple transactions at the same time
        Example 1:

        Input: [7,1,5,3,6,4]
        Output: 7
        Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
                     Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
     */
    /*
        If we plot the numbers of the given array on a graph, we get ...
        If we analyze the graph, we notice that the points of interest are the consecutive valleys and peaks.
        the total profit = sum(height(peaki) - height(valleyj))
        The key point is we need to consider every peak immediately following a valley to maximize the profit.

        Peak Valley Approach
        how to determine there is a valley or a peak?
        if prices[i] < prices[i+1]  there is a valley
        if prices[i] > prices[i+1]  there is a peak

        note that valley and peak appears alternatively...

        time: O(n)
        space: O(1)
     */
    public int maxProfit2(int[] prices) {
        int i = 0;
        int valley = prices[0];
        int peak = prices[0];
        int maxprofit = 0;

        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) {   // i == prices.length - 1
                i++;
            }

            valley = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            peak = prices[i];
            maxprofit += peak - valley;
        }

        return maxprofit;
    }

    /*
        In this case, instead of looking for every peak following a valley, we can simply go on crawling over the slope and keep on adding the profit obtained from every consecutive transaction.
        In the end,we will be using the peaks and valleys effectively, but we need not track the costs corresponding to the peaks and valleys along with the maximum profit,
     */
    public int maxProfit21(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }

    /*
        sell stock 3,4 :

        i is transaction, j is the day
        T[i][j] = max(T[i][j-1],       -> not transacting on jth day
                      best you can get by completing transaction on jth day   ->   (price[j] - price[m]) + T[i-1][m]

        for details:  https://www.youtube.com/watch?v=oDhu5uGq_ic&feature=youtu.be&ab_channel=TusharRoy-CodingMadeSimple
        for teh sake of simplicity,
        T[i][j] = max(T[i][j-1],
                      price[j] + maxDiff,
                      maxDiff = max(maxDiff, T[i-1] - price[j])
     */
    public int maxProfit3(int[] prices) {
        if (prices.length <= 1) return 0;
        int m = 2;
        int n = prices.length;
        int[][] dp = new int[m+1][n];
        for (int i = 0; i < n; i++) {
            dp[0][i] = 0;
        }
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 0;
        }
        for (int i = 1; i <= m; i++) {
            int maxdiff = Integer.MIN_VALUE; //maxdiff is maximum difference of dp[i-1][k] - prices[k] before and include j-2;
            for (int j = 1; j < n; j++) {
                maxdiff = Math.max(maxdiff, dp[i-1][j-1] - prices[j-1]);
                dp[i][j] = Math.max(dp[i][j-1], prices[j] + maxdiff);
            }
        }
        return dp[m][n-1];
    }
}
