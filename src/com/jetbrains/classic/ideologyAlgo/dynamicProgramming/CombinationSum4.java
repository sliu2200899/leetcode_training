package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

public class CombinationSum4 {
    /*
        similar to coin change,
        time: O(n*target)
        space: O(target)
     */
    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;

        int[] dp = new int[target+1];
        dp[0] = 1;
        for (int i = 1; i <= target; ++i) {
            for (int j = 0; j < nums.length; ++j) {
                if (i - nums[j] >= 0) {
                    dp[i] += dp[i-nums[j]];
                }
            }
        }
        return dp[target];
    }
}
