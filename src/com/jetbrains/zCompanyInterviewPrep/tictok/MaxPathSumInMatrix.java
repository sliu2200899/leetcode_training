package com.jetbrains.zCompanyInterviewPrep.tictok;

import java.util.Arrays;

public class MaxPathSumInMatrix {
    /*
        given a matrix of integers, could be both positive and negative, give the max path sum in the matrix

        Given an n*m matrix, the task is to find the maximum sum of elements of cells starting from the cell (0, 0) to cell (n-1, m-1).
        However, the allowed moves are right, downwards or diagonally right, i.e, from location (i, j) next move can be (i+1, j),
        or, (i, j+1), or (i+1, j+1). Find the maximum sum of elements satisfying the allowed moves.

        Input:
        mat[][] = {{100, -350, -200},
                   {-100, -300, 700}}
        Output: 500
        Explanation:
        Path followed is 100 -> -300 -> 700
     */
    private int process(int[][] arr) {
        if (arr == null || arr.length == 0 || arr[0].length == 0) {
            return 0;
        }

        int m = arr.length, n = arr[0].length;

        // dp[i][j] = max(dp[i-1][j], dp[i-1][j-1], dp[i][j-1])
        int[] dp = new int[n];
        dp[0] = arr[0][0];
        for (int i = 1; i < n; ++i) {
            dp[i] = dp[i-1] + arr[0][i];
        }

        for (int i = 1; i < m; ++i) {
            int prev = 0;
            for (int j = 0; j < n; ++j) {
                int ac = dp[j];
                if (j == 0) {
                    dp[j] = dp[j] + arr[i][j];
                } else {
                    int tmp = Math.max(prev, dp[j]);
                    dp[j] = arr[i][j] + Math.max(tmp, dp[j-1]);
                }
                prev = ac;
            }
        }

        return dp[n-1];
    }

    /*
        variants:

        Given a matrix of N * M. Find the maximum path sum in matrix. The maximum path is sum of all elements from first row to last row
        where you are allowed to move only down or diagonally to left or right. You can start from any element in first row.

     */
    private int process2(int[][] arr) {
        if (arr == null || arr.length == 0 || arr[0].length == 0) {
            return 0;
        }

        int m = arr.length, n = arr[0].length;

        int[][] dp = new int[m][n];
        for (int i = 0; i < n; ++i) {
            dp[0][i] = arr[0][i];
        }

        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (j == 0) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j+1]) + arr[i][j];
                } else if (j == n -1) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]) + arr[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j+1], Math.max(dp[i-1][j], dp[i-1][j-1])) + arr[i][j];
                }
            }
        }

        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            res = Math.max(res, dp[m-1][i]);
        }
        return res;
    }

    /*
        no extra constraints
     */
//    int maxSum = 0;
    private int process3(int[][] arr) {
        if (arr == null || arr.length == 0 || arr[0].length == 0) {
            return 0;
        }

        int m = arr.length, n = arr[0].length;

        int sum = Integer.MIN_VALUE;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                sum = Math.max(sum, dfs(i, j, arr, new boolean[m][n]));
            }
        }
        return sum;
    }

    private int dfs(int row, int col, int[][] arr, boolean[][] visited) {
        // base case
        if (row < 0 || row >= arr.length || col < 0 || col >= arr[0].length || visited[row][col]) {
            return 0;
        }

        // recursive
        visited[row][col] = true;
        int ans = Integer.MIN_VALUE;
        int[][] pairs = new int[][]{{0,-1}, {0,1}, {1,0}, {-1,0}};
        for (int[] pair : pairs) {
            int newx = pair[0] + row;
            int newy = pair[1] + col;

            ans = Math.max(ans, arr[row][col] + dfs(newx, newy, arr, visited));
        }

        visited[row][col] = false;

        return ans;
    }


    public static void main (String[] args) {
        System.out.println("GfG!");
        MaxPathSumInMatrix gfg = new MaxPathSumInMatrix();
        int arr[][] = {{-100, -350, -200}, {-100, -300, -700}};
        System.out.println(gfg.process3(arr));
    }
}
