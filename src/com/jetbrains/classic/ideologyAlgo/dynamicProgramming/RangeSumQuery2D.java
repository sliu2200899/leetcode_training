package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

public class RangeSumQuery2D {

    /*
        using a cumulative sum array to cache rows
        try to see the 2D matrix as mm rows of 1D arrays. To find the region sum, we just accumulate the sum in the region row by row.

        Time complexity : O(m) time per query, O(mn) time pre-computation.
        space complexity: O(mn)
     */
    int[][] prefixSum = null;
    public void  NumMatrix1(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;

        int m  = matrix.length;
        int n = matrix[0].length;

        prefixSum = new int[m][n+1];

        for (int i = 0; i < m; ++i) {
            for (int j = 1; j <= n; ++j) {
                prefixSum[i][j] = prefixSum[i][j-1] + matrix[i][j-1];
            }
        }
    }

    public int sumRegion1(int row1, int col1, int row2, int col2) {
        if (prefixSum == null) return 0;

        int rst = 0;
        for (int i = row1; i <= row2; ++i) {
            rst += (prefixSum[i][col2 + 1] - prefixSum[i][col1]);
        }
        return rst;
    }

    /*
        caching smarter

        We used a cumulative sum array in the 1D version. We notice that the cumulative sum is computed with respect to the origin at index 0.
        Extending this analogy to the 2D case, we could pre-compute a cumulative region sum with respect to the origin at (0, 0)(0,0).
     */
    private int[][] dp;

    public void NumMatrix2(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return;
        dp = new int[matrix.length + 1][matrix[0].length + 1];
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                dp[r + 1][c + 1] = dp[r + 1][c] + dp[r][c + 1] + matrix[r][c] - dp[r][c];
            }
        }
    }

    public int sumRegion2(int row1, int col1, int row2, int col2) {
        return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
    }
}
