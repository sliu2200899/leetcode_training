package com.jetbrains.classic.ideologyAlgo.backtracking;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class TargetSum {
    Map<Pair<Integer, Integer>, Integer> map;
    public int findTargetSumWays(int[] nums, int S) {
        /*
               index | sum
                     0|3
           1|2.                1|4
     2|1.         2|3          2|3
  3|0    3|2    3|2  3|4
4|-1. 4|1  4|1    4|1.          ...
5|0   5|0   5|0    5|0          5|0    => output: 5
        */

        if (nums == null || nums.length == 0) {
            return 0;
        }
        map = new HashMap<>();
        return recursion(nums, 0, S);
    }

    private int recursion(int[] nums, int index, int target) {
        if (index == nums.length && target == 0) {     // dp[n][0] = 1;
            return 1;
        } else if (index >= nums.length) {
            return 0;
        }

        Pair<Integer, Integer> pair = new Pair(index, target);
        if (map.containsKey(pair)) {
            return map.get(pair);
        }

        int ans = 0;
        ans += recursion(nums, index+1, target - nums[index]);  // dp[i][j] = dp[i+1][j- nums[i]] +  dp[i+1][j+ nums[i]]
        ans += recursion(nums, index+1, target + nums[index]);

        map.put(pair, ans);
        return ans;
    }

    /*
        dp: just variant of knapsack problem
     */
    public int findTargetSumWays2(int[] nums, int S) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (S < -sum || S > sum) {return 0;}

        int[][] dp = new int[n+1][2*sum + 1];
        dp[0][0+sum] = 1;   // 0 + sum means 0,

        for (int i = 1; i <= nums.length; ++i) {
            for (int j = 0; j < 2*sum +1; ++j) {
                if (j + nums[i-1] < 2*sum + 1) dp[i][j] += dp[i-1][j + nums[i - 1]];
                if (j - nums[i-1] >= 0) dp[i][j] += dp[i - 1][j - nums[i - 1]];
            }
        }
        return dp[nums.length][sum + S];
    }

    /*
        has some problems...
     */
    public int findTargetSumWays3(int[] nums, int S) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (S < -sum || S > sum) {return 0;}

        int[][] dp = new int[n+1][2*sum + 1];
        dp[n][sum] = 1;

        for (int i = n-1; i >= 0; --i) {
            for (int j = 0; j < 2*sum +1; ++j) {
                if (j + nums[i] < 2*sum + 1) dp[i][j] += dp[i+1][j + nums[i]];
                if (j - nums[i] >= 0) dp[i][j] += dp[i+1][j - nums[i]];
            }
        }
        return dp[0][S];
    }
}
