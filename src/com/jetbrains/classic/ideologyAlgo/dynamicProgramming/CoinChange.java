package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

import java.util.Arrays;

public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        /*
            dp[amount] represents the fewest number of coins that we need to make up to the amount
                      i
            0 1 2 3 4 5 6 7 8 9 10 11
        dp  0 1 1 2
              j
            1 2 5

            dp[i] + 1
            dp[i-coins[j]]

            store all the coins in set
            (1, 2, 5)

            dp[1] = min(dp[0] + 1) = 1

            dp[2] = min(dp[1] + 1, dp[0] + 1)    // scan through all the possible coins and look for the minimum number of coins needed

            dp[3] = min(dp[2] + 1, dp[1] + 1)

            ...

            dp[i] = min( dp(i-coins[j]) +1 )      if 0 < i <= amount and i - coins[j] >=0

            time: O(S*n)
            space: O(S) S is the amount, n is the denomination count.
        */

        int n = coins.length;

        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);

        dp[0] = 0;

        for (int i = 1; i <= amount; ++i) {
            for (int j = 0; j < coins.length; ++j) {
                if (i - coins[j] >= 0) {
                    dp[i] = Math.min(dp[i], dp[i-coins[j]]+1);
                }
            }
        }
        return dp[amount] == amount+1 ? -1 : dp[amount];
    }
}
