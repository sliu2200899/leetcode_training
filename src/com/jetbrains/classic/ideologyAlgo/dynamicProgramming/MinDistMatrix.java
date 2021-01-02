package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

public class MinDistMatrix {
    /*
    假设我们有一个 n 乘以 n 的矩阵 w[n][n]。矩阵存储的都是正整数。棋子起始位置在左上角，终止位置在右下角。我们将棋子从左上角移动到右下角。每次只能向右或者向下移动一位。
    从左上角到右下角，会有很多不同的路径可以走。我们把每条路径经过的数字加起来看作路径的长度。那从左上角移动到右下角的最短路径长度是多少呢？
     */


    /*
        states[][] to represent the minimum dist from (0,0) to (n-1, n-1)
     */
    public int minDistDP(int[][] matrix, int n) {
        int[][] states = new int[n][n];

        int sum = 0;

        for (int j = 0; j < n; ++j) {
            sum += matrix[0][j];
            states[0][j] = sum;
        }

        sum = 0;
        for (int i = 0; i < n; ++i) {
            sum += matrix[i][0];
            states[i][0] = sum;
        }

        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < n; ++j) {
                states[i][j] =
                        matrix[i][j] + Math.min(states[i][j - 1], states[i - 1][j]);
            }
        }

        return states[n - 1][n - 1];
    }

    /*
        backtracking + memo
     */

    private int[][] mem;
    public int minDistMemo(int[][] matrix, int n) {
        this.mem = new int[n][n];
        return helper(matrix, n - 1, n - 1);
    }

    private int helper(int[][] matrix, int i, int j) {
        if (i == 0 && j == 0) return matrix[0][0];
        if (mem[i][j] > 0) return mem[i][j];

        int minLeft = Integer.MAX_VALUE;
        if (j - 1 >= 0) minLeft = helper(matrix, i, j - 1);

        int minUp = Integer.MAX_VALUE;
        if (i - 1 >= 0) minUp = helper(matrix, i - 1, j);

        int currMinDist = matrix[i][j] + Math.min(minLeft, minUp);
        mem[i][j] = currMinDist;
        return currMinDist;
    }
}
