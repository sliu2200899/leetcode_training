package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

public class minFallingPathSum {
    public int minFallingPathSum(int[][] A) {
        if (A == null || A.length == 0 || A[0].length == 0) return 0;

        int m = A.length;
        int n = A[0].length;

        int[][] dp = new int[m][n];
        for (int i = 0; i < n; ++i) {
            dp[0][i] = A[0][i];
        }

        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int minNum = getMiniPrevRow(dp, i, j);
                dp[i][j] = minNum + A[i][j];
            }
        }

        int rst = Integer.MAX_VALUE;
        for (int i : dp[m-1]) {
            if (rst > i) {
                rst = i;
            }
        }
        return rst;
    }

    private int getMiniPrevRow(int[][] arr, int i, int j) {

        // dp[i-1][m]   j-1 <= m <= j+1
        if (j==0) {
            return Math.min(arr[i-1][j], arr[i-1][j+1]);
        }
        else if (j==arr[0].length - 1) {
            return Math.min(arr[i-1][j], arr[i-1][j-1]);
        }
        else {
            return Math.min(Math.min(arr[i-1][j], arr[i-1][j+1]), arr[i-1][j-1]);
        }
    }
}
