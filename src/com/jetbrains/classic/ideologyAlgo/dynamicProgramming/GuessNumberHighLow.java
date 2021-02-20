package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

import java.util.Arrays;

public class GuessNumberHighLow {

    /*
        guess number 1
        just a binary search
        time: O(logn)
        space: O(1)

     */
    public int guessNumber(int n) {

        int start = 1, end = n;
        while (start <= end) {
            int num = start + (end - start) / 2;
            if (guess(num) == 0) {
                return num;
            } else if (guess(num) == 1) {
                start = num + 1;
            } else {
                end = num - 1;
            }
        }
        return -1;
    }

    private int guess(int n) {
        return 0;
    }

    /*
        recursion + memoization

        The best strategy to play the game is to minimize the maximum loss you could possibly face.

Let's take an instance, for n = 3, we have 3 choices either to choose 1 or 2 or 3.
Let's say we choose 1. There are 2 possible chances,

[Case X]: 1 is the actual number so you pay 0$ or,
[Case Y]: 1 is not the actual number so you pay 1$ (now you know that the actual number is > 1 because for every guess we will
know if its less than or greater than, in our case it can only be greater than) and have the subproblem (2, 3). To choose from (2, 3)
again recursively applying the same method, you can choose either 2 or 3. If you pick 2, you have 2 possible outcomes again. 2 is the actual
number and you pay 0$ for this choice or 2 is not the actual number and you pay 2$ for this choice and you know 3 is the answer since that's the only one left.
On the other hand, if you had picked 3, then either 3 is correct or you pay 3$ and know 2 is the actual answer since it's the only one left.
So to sum up this, you pay 2$ in the worst case if you choose 2 or pay 3$ in the worst case if you pick 3$. So we will pick the min of the worst
cases which is 2$ and hence 2 is the answer for (2, 3) subproblem. (Notice the minimax? ;) ) So, the total cost paid in this is 1$ + 2$ = 3$.

Let's say you picked 2 initially. You have 2 possible outcomes.

2 is the actual number and you pay 0$ or,
2 is not the actual number and you pay 2$. At this point, you get to know if the actual number is less than or greater than the actual number. So, you will know the answer right away without another guess. So you end up paying 2$.
So, if you choose 2 initially, you risk paying 2$ at most.
Similarly, if you had chosen 3 initially, you risk paying 4$ at most. Hence picking 2 initially is the best option and you risk at most 2$.
This leads to a natural recursion, which you can find in the code below. I have memoized it in a matrix.


        time:  With memoization, O(n^2) as there can be at most n*n different combinations to be found.
        space: O(n^2)
     */
    int[][] dp;
    public int getMoneyAmount(int n) {
        if (n == 1) return 0;

        dp = new int[n+1][n+1];
        for (int[] row: dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        return dfs(1, n);
    }

    private int dfs(int start, int end) {
        if (start >= end) return 0;
        if (dp[start][end] != Integer.MAX_VALUE) return dp[start][end];

        for (int i = start; i <= end; ++i) {
            dp[start][end] = Math.min(dp[start][end], Math.max(i + dfs(start, i - 1), i + dfs(i + 1, end)));
        }
        return dp[start][end];
    }

    /*
        DP
     */
    public int getMoneyAmount2(int n) {
        // all intervals are inclusive
        // uninitialized cells are assured to be zero
        // the zero column and row will be uninitialized
        // the illegal cells will also be uninitialized
        // add 1 to the length just to make the index the same as numbers used
        int[][] dp = new int[n + 1][n + 1]; // dp[i][j] means the min cost in the worst case for numbers (i...j)

        // iterate the lengths of the intervals since the calculations of longer intervals rely on shorter ones
        for (int l = 2; l <= n; l++) {
            // iterate all the intervals with length l, the start of which is i. Hence the interval will be [i, i + (l - 1)]
            for (int i = 1; i <= n - (l - 1); i++) {
                dp[i][i + (l - 1)] = Integer.MAX_VALUE;
                // iterate all the first guesses g
                for (int g = i; g <= i + (l - 1); g++) {
                    int costForThisGuess;
                    // since if g is the last integer, g + 1 does not exist, we have to separate this case
                    // cost for [i, i + (l - 1)]: g (first guess) + max{the cost of left part [i, g - 1], the cost of right part [g + 1, i + (l - 1)]}
                    if (g == n) {
                        costForThisGuess = dp[i][g - 1] + g;
                    } else {
                        costForThisGuess = g + Math.max(dp[i][g - 1], dp[g + 1][i + (l - 1)]);
                    }
                    dp[i][i + (l - 1)] = Math.min(dp[i][i + (l - 1)], costForThisGuess); // keep track of the min cost among all first guesses
                }
            }
        }
        return dp[1][n];
    }
}
