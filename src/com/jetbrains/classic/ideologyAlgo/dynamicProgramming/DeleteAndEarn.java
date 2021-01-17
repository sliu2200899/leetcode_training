package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

public class DeleteAndEarn {
    public int deleteAndEarn(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;

        int[] points = new int[10001];
        for (int i = 0; i < n; ++i) {
            points[nums[i]] += nums[i];
        }

        return rob(points);
    }

    private int rob(int[] arr) {
        int[] dp = new int[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            dp[i] = Math.max((i>1? dp[i-2]:0) + arr[i],
                    (i>0?dp[i-1]:0));
        }
        return dp[arr.length - 1];
    }
}
