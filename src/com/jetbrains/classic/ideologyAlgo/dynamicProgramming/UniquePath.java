package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

public class UniquePath {
    /*
        unique path 1: 2 dimensional array, robot moves from (0,0) to (m-1,n-1)
        How many possible unique paths are there?

        time: O(mn)
        space: O(mn)
     */
    public int uniquePaths(int m, int n) {
        // f[i][j]  represents the ways of robot moving from grid[0][0] to grid[i][j]
        // f[i][j] = f[i - 1][j] + f[i][j - 1]
        // how to initialize the f[i][j]

        int[][] f = new int[m][n];
        for (int i = 0; i < n; ++i) {
            f[0][i] = 1;
        }

        for (int j = 0; j < m; ++j) {
            f[j][0] = 1;
        }

        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }

        return f[m - 1][n - 1];
    }
    /*
        unique path 2: with obstacles

        time: O(mn)
        space: O(mn)
     */

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // f[i][j] = 0,  if obstacleGrid[i][j] == 1
        //.          f[i - 1][j] + f[i][j - 1], otherwise

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] f = new int[m][n];

        if (obstacleGrid[0][0] == 1) return 0;
        f[0][0] = 1;

        for (int i = 1; i < n; ++i) {
            f[0][i] = obstacleGrid[0][i] == 1 ? 0 : f[0][i - 1];
        }

        for (int j = 1; j < m ; ++j) {
            f[j][0] = obstacleGrid[j][0] == 1 ? 0 : f[j - 1][0];
        }

        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                f[i][j] = obstacleGrid[i][j] == 1 ? 0 : (f[i - 1][j] + f[i][j - 1]);
            }
        }

        return f[m - 1][n - 1];
    }
}
