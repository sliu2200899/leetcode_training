package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

import java.util.ArrayDeque;
import java.util.Queue;

public class PerfectSquares {
    /*
        recursion + memo

        time: O(sqrt(n))
        space:  O(n)
     */
    int[] memo = null;
    public int numSquares(int n) {
        memo = new int[n+1];
        return helper(n);
    }

    private int helper(int n) {
        if (n <= 3) return n;
        if (memo[n] != 0) return memo[n];

        int ans = n;
        for (int i = 1; i * i <= n; ++i) {
            ans = Math.min(ans, 1 + helper(n - i*i));
        }

        memo[n] = ans;

        return ans;
    }

    /*
        dp solution
        dp[i]  represents the least number of the ps that can sum up to i
             dp[i] = i  initially
             for (j = 1; j*j <= i; ++j)
                   dp[i] = min(dp[i], 1 + dp[i-j*j])
        time: O(n* sqrt(n))
        space: O(n)
     */
    public int numSquares2(int n) {
        int[] dp = new int[n+1];

        for (int i = 0; i <= n; ++i) {
            dp[i] = i;
            for (int j = 1; j*j <= i; ++j) {
                dp[i] = Math.min(dp[i], 1 + dp[i-j*j]);
            }
        }
        return dp[n];
    }
    /*
        Legendre's theorem:
        every natural no, can be separated as the sum of 4 int.

     */

    /*
        BFS to find the least number of ps that sum to n
     */
    public int numSquares3(int n) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n+1];
        queue.offer(0);
        int step = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int curr = queue.poll();
                for (int i = 1; i * i <= n; ++i) {
                    int square = i*i;

                    if (curr + square == n) return step + 1;
                    else if (curr + square > n) break;

                    if (visited[curr + square]) continue;
                    visited[curr + square] = true;
                    queue.offer(curr + square);
                }
            }
            step += 1;
        }
        return -1;
    }
}
