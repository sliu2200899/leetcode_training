package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

public class ClimbingStairs {
    /*
        brute force
        time: O(2 ^ n). Size of recursion tree will be 2 ^ n
        space: O(n)  the depth of recursion tree
     */

    public int climbStairs1(int n) {
        if (n == 1 || n == 0) return 1;

        return climbStairs1(n - 1) + climbStairs1(n - 2);
    }

    /*
        recursion with memo
        time: O(n)  size of recursion tree can go upto n
        space: O(n)
     */
    Map<Integer, Integer> map = new HashMap<>();
    public int climbStairs2(int n) {
        if (n == 1 || n == 0) return 1;

        if (map.containsKey(n)) return map.get(n);

        int steps = climbStairs2(n - 1) + climbStairs2(n - 2);
        map.put(n, steps);

        return steps;
    }

    /*
        Dynamic Programming
        As we can see this problem can be broken into subproblems, and it contains the optimal substructure property i.e.
        its optimal solution can be constructed efficiently from optimal solutions of its subproblems, we can use dynamic
        programming to solve this problem.

        One can reach ith step in one of the two ways:
            1. taking a single step from (i - 1)th step
            2. taking a step of 2 from (i - 2)th step

        Let dp[i] denotes the number of ways to reach on ith step:
            dp[i] = dp[i - 1] + dp[i - 2]
     */
    public int climbStairs3(int n) {
        if (n == 1) return 1;

        // initialize the dp array
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    /*
        Fibonacci number
        improve the space complexity further
        time: O(n)  single loop upto n is required to calculate nth fibonacci number
        space: O(1)  constant space is used
     */
    public int climbStairs4(int n) {
        if (n == 1) {
            return 1;
        }
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }




    // 746 min cost Climbing Stairs
    // I: O(n), S = O(n), T = O(n)
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;

        int[] dp = new int[len];
        // i is the index of the floor, and dp[i] represents the min cost before leaving n-th step
        // dp[n] = min(dp[n - 1], dp[n - 2]) + cost[n]
        // ans = min(dp(n -1), dp(n-2))
        dp[0] = cost[0];
        dp[1] = cost[1];

        for (int i = 2; i < len; ++i) {
            dp[i] = Math.min(dp[i-1], dp[i-2]) + cost[i];
        }

        return Math.min(dp[len - 1], dp[len - 2]);
    }


}
