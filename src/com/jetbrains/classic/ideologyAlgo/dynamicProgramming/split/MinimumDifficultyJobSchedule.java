package com.jetbrains.classic.ideologyAlgo.dynamicProgramming.split;

import java.util.Arrays;

public class MinimumDifficultyJobSchedule {
    /*
         k-1.                 1            days
      1 ...   j            j+1. ... i      jobs
       dp[j][k-1]       max(jobs[j+1~i])   difficulty
     dp[i][k] := min difficulty to schedule the first i jobs(1-based) in k days
     dp[i][k] = min(dp[j][k-1] + max(jobs[j+1 ~ i])) where k-1 <= j < i


     time: O(nnd)
     space: O(nd)
 */
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if(n < d) return -1;

        int[][] dp = new int[n+1][d+1];

        // dp init
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE/2);
        }
        dp[0][0] = 0;

        // dp transition:
        for (int i = 1; i <= n; ++i) {
            for (int k = 1; k <= d; ++k) {
                int md = 0;
                for (int j = i - 1; j >= k - 1; --j) {
                    md = Math.max(md, jobDifficulty[j]);
                    dp[i][k] = Math.min(dp[i][k], dp[j][k - 1] + md);
                }
            }
        }

        return dp[n][d];
    }

    /*
        stack + dp
        time: O(nd)
        https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/discuss/963963/Java-Top-down-and-bottom-up-DP-monotonic-stack-time-O(nd)-space-O(nd)-with-detailed-explanation

     */
}
