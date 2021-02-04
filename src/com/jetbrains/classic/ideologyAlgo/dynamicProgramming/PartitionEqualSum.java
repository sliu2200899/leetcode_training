package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class PartitionEqualSum {
    /*
        approach 1: brute force recursion
        recursively check the problem, and can draw the recursion tree starting from the (0, 5) as the root node, in the next level we need to choose pick current element or not.

        time: O(2^n)   n is the length of nums array
        space: O(n)   recursion stack is n
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 != 0) return false;

        return recursion(nums, 0, sum/2);
    }

    // recursively check whether there exists a subset with target sum
    private boolean recursion(int[] nums, int index, int target) {
        // base cases
        if (target == 0) return true;
        if (target < 0 || index == nums.length) return false;

        // either choose nums[index] or not
        return recursion(nums, index+1, target - nums[index]) || recursion(nums, index+1, target);
    }

    /*
        time complexity optimization:  memo
        time: O(mn) the worse case, O(mn)  number of unique pairs
        space: O(mn) space that need to fill the memo table
     */

    private Boolean[][] memo;
    public boolean canPartition2(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 != 0) return false;

        sum /= 2;
        memo = new Boolean[nums.length+1][sum+1];

        return recursion2(nums, 0, sum);
    }

    // recursively check whether there exists a subset with target sum
    private boolean recursion2(int[] nums, int index, int target) {
        // base cases
        if (target == 0) return true;
        if (target < 0 || index == nums.length) return false;

        if (memo[index][target] != null) return memo[index][target];   //dp[i][j]

        // either choose nums[index] or not
        boolean ans = recursion2(nums, index+1, target - nums[index]) || recursion2(nums, index+1, target);   //dp[i+1][j-nums[i]] || dp[i+1][j]
        memo[index][target] = ans;
        return ans;
    }

    /*
        dynamic programming from bottom to top
        fill the dp table

        time: O(mn)
        space: O(mn)
     */

    public boolean canPartition3(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;

        if (sum % 2 != 0) return false;

        int n = nums.length;
        sum /= 2;
        boolean[][] dp = new boolean[n+1][sum+1];

        // for (int j = 0; j <= sum; ++j) dp[n][j] = false;
        for (int i = 0; i <= n; ++i) dp[i][0]  = true;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= sum; j++) {
                if (j < nums[i]) dp[i][j] = dp[i+1][j];
                else dp[i][j] = dp[i+1][j-nums[i]] || dp[i+1][j];
            }
        }

        return dp[0][sum];
    }
}
