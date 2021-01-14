package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

public class DungeonGame {
    public int calculateMinimumHP(int[][] dungeon) {
        // process the dp array from bottom right corner to top left
        // dp[i][j] minimum health point that knight should have at this cell.
        // int tmp = Math.min(dp[i+1][j],dp[i][j+1]) - dungeon[i][j]
        // dp[i][j] = tmp    if tmp > 0
        //            1,     otherwise

        /*
            time: O(mn)
            space: O(mn)
         */
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0 ) return 0;

        int m = dungeon.length;
        int n = dungeon[0].length;

        int[][] dp = new int[m][n];
        dp[m-1][n-1] = dungeon[m-1][n-1] > 0 ? 1 : 1 - dungeon[m-1][n-1];

        for (int i = m - 2; i >= 0; --i) {
            int tmp = dp[i+1][n-1] - dungeon[i][n-1];
            dp[i][n-1] = tmp > 0 ? tmp : 1;
        }


        for (int i = n - 2; i >= 0; --i) {
            int tmp = dp[m-1][i+1] - dungeon[m-1][i];
            dp[m-1][i] = tmp > 0 ? tmp : 1;
        }


        for (int i = m - 2; i >= 0; --i) {
            for (int j = n - 2; j >= 0; --j) {
                int tmp = Math.min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j];
                dp[i][j] = tmp > 0 ? tmp : 1;
            }
        }

        return dp[0][0];
    }
}
