package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

public class minPathSum {
    public int minPathSum(int[][] grid) {
        // dp[i][j] represetns the minimum sum of the path from. (0,0) to (i,j)
        // dp[i][j] = min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
        // initialize
        //.

        /*
            time: O(mn)
            space: O(mn)
         */

        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];
        for (int i = 0; i < n; ++i) {
            dp[0][i] = (i == 0) ? grid[0][i] : (dp[0][i - 1] + grid[0][i]);
        }

        for (int i = 0; i < m; ++i) {
            dp[i][0] = (i == 0) ? grid[i][0] : (dp[i - 1][0] + grid[i][0]);
        }

        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }
}
