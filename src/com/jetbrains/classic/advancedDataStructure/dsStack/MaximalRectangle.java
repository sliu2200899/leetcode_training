package com.jetbrains.classic.advancedDataStructure.dsStack;

import java.util.ArrayDeque;
import java.util.Deque;

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

    /*
        Using Histogram - Stack
        In the previous approach we discussed breaking the input into a set of histograms - one histogram
        representing the substructure at each column. To compute the maximum area in our rectangle, we merely have to
        compute the maximum area of each histogram and find the global maximum (note that the below approach builds a
        histogram for each row instead of each column, but the idea is still the same).

        Time complexity : O(NM). Running leetcode84 on each row takes M (length of each row) time. This is done N times for O(NM).
        Space complexity : O(M). We allocate an array the size of the the number of columns to store our widths at each row.
     */
    public int maximalRectangle2(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int maxarea = 0;

        int[] dp = new int[matrix[0].length];

        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {

                // update teh state of this row's histogram using the last row's histogram
                // by keeping track of the number of consecutive ones

                dp[j] = matrix[i][j] == '1' ? dp[j] + 1 : 0;
            }

            maxarea = Math.max(maxarea, computeAreaHistogram(dp));
        }

        return maxarea;
    }

    private int computeAreaHistogram(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);

        int maxarea = 0;
        for (int i = 0; i < heights.length; ++i) {
            while (stack.peek() != -1 &&
                    (heights[stack.peek()] >= heights[i])) {
                int curHeight = heights[stack.pop()];
                maxarea = Math.max(maxarea, curHeight * (i - stack.peek() - 1));
            }

            stack.push(i);
        }

        while (stack.peek() != -1) {
            int curHeight = heights[stack.pop()];
            maxarea = Math.max(maxarea, curHeight * (heights.length - stack.peek() - 1));
        }

        return maxarea;
    }
}
