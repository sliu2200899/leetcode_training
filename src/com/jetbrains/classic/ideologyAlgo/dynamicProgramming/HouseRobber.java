package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

import java.util.Arrays;

public class HouseRobber {
    /*
        version 1: simple just require not adjcent houses can be robbed

        money: 2 1 1 2
        can be converted into a binary string, which represents the operation rob(1), not rob(0)
        ...
        0, 1, 1, 0 | invalid
        0, 1, 0, 1 | 3
        1, 0, 1, 0 | 3
        1, 0, 0, 1 | 4
        ...

        if Brute Force: O(2^n)  TLE

        Recursion + Memorization
        for a given house i, we have two options
        1. skip it
        2. take the money if we didn't robber house i - 1

        rob(n) = max(rob(n-2) + money[n], rob(n-1))

        time: O(n)
        space: O(n)
     */

    public int rob1(int[] nums) {
        // dp[i] represents the maximum amount of money you can rob tonight
        // dp[i] = min(dp[i-2] + nums[i], dp[i-1]).

        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        else if (nums.length == 2) return Math.max(nums[0], nums[1]);

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; ++i) {
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
        }

        return dp[nums.length - 1];
    }

    /*
        improve the code
     */
    private int rob11(int[] arr) {
        int[] dp = new int[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            dp[i] = Math.max((i>1? dp[i-2]:0) + arr[i],
                    (i>0?dp[i-1]:0));
        }
        return dp[arr.length - 1];
    }

    /*
        version 2: cannot rob the first and last house at the same night
     */
    public int rob2(int[] nums) {
        // two additonal array to solve the problem
        // [2, 3, 2]
        // [3, 2]    or   [2, 3]

        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        else if (nums.length == 2) return Math.max(nums[0], nums[1]);

        int[] dp = new int[nums.length - 1];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length - 1; ++i) {
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
        }

        int firstMax = dp[dp.length - 1];

        Arrays.fill(dp, 0);
        dp[0] = nums[1];
        dp[1] = Math.max(nums[1], nums[2]);
        for (int i = 2; i < nums.length - 1; ++i) {
            dp[i] = Math.max(dp[i-2] + nums[i+1], dp[i-1]);
        }

        int secondMax = dp[dp.length - 1];

        return Math.max(firstMax, secondMax);
    }
}
