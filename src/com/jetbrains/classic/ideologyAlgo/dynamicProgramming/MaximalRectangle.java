package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null  || matrix.length == 0 || matrix[0].length == 0) return 0;
        // row[i] = row[i-1] + 1 if row[i] == '1'

        // maxWidth = min(maxWidth, widthHere);
        // curArea = maxWidth * (currentRow - originalRow + 1)
        // maxArea = max(maxArea, curArea)

        int m = matrix.length;
        int n = matrix[0].length;

        int[][] dp = new int[m][n];    // dp[i][j] represents the number of consecutive ones ending with jth col in ith row.
        int maxArea = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {

                if (matrix[i][j] == '1') {

                    // compute the maximum width and update dp with it
                    dp[i][j] = j == 0 ? 1 : dp[i][j-1] + 1;

                    int width = dp[i][j];

                    // compute the maximum area rectangle with a lower right corner at [i,j]
                    int curArea = 0;
                    int cur = i;
                    while (cur >= 0 && dp[cur][j] != 0) {

                        width = Math.min(dp[cur][j], width);
                        maxArea = Math.max(maxArea, width * (i - cur + 1));

                        cur--;
                    }
                }
            }
        }

        return maxArea;
    }
}
