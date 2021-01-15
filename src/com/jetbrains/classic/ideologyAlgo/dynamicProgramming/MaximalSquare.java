package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

public class MaximalSquare {

    // bruteforce approach
    /*
        The simplest approach consists of trying to find out every possible square of 1’s that can be formed from within the matrix. The question now is – how to go for it?

        We use a variable to contain the size of the largest square found so far and another variable to store the size of the current, both initialized to 0. Starting from
        the left uppermost point in the matrix, we search for a 1. No operation needs to be done for a 0. Whenever a 1 is found, we try to find out the largest square that
        can be formed including that 1. For this, we move diagonally (right and downwards), i.e. we increment the row index and column index temporarily and then check whether
        all the elements of that row and column are 1 or not. If all the elements happen to be 1, we move diagonally further as previously. If even one element turns out to be 0,
        we stop this diagonal movement and update the size of the largest square. Now we, continue the traversal of the matrix from the element next to the initial 1 found,
        till all the elements of the matrix have been traversed.

        time: O((nn)^2)
        space: O(1)
     */

    public int maximalSquare(char[][] matrix) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

        int rows = matrix.length;
        int cols = matrix[0].length;

        int maxsqlen = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (matrix[i][j] == '1') {
                    int sqlen = 1;
                    boolean flag = true;
                    while (sqlen + i < rows && sqlen + j < cols && flag) {
                        for (int k = j; k <= sqlen + j; k++) {
                            if (matrix[sqlen + i][k] == '0') {
                                flag = false;
                                break;
                            }
                        }

                        for (int k = i; k <= sqlen + i; ++k) {
                            if (matrix[k][sqlen + j] == '0') {
                                flag = false;
                                break;
                            }
                        }

                        if (flag) {
                            sqlen++;
                        }
                    }
                    if (maxsqlen < sqlen) {
                        maxsqlen = sqlen;
                    }
                }
            }
        }
        return maxsqlen * maxsqlen;
    }

    /*
        approach 2: dynamic programming
        dp(i,j) represents the side length of the maximum square whose bottom right corner is the cell with index (i,j) in the original matrix.
        dp(i,j) = min(dp(i−1,j),dp(i−1,j−1),dp(i,j−1))+1
        We also remember the size of the largest square found so far. In this way, we traverse the original matrix once and find out the required maximum size.
        This gives the side length of the square (say maxsqlenmaxsqlen). The required result is the area maxsqlen^2
 .
        time: O(mn)
        space: O(mn)
     */
    public int maximalSquare2(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[][] dp = new int[rows + 1][cols + 1];
        int maxsqlen = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i-1][j-1] == '1'){
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[i][j]);
                }
            }
        }
        return maxsqlen * maxsqlen;
    }

    /*
        approach 3: better dynamic programming
        improve the space complexity further more...

        time: O(mn)
        space: O(n)
     */

    public int maximalSquare3(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[] dp = new int[cols + 1];
        int maxsqlen = 0, prev = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                int temp = dp[j];
                if (matrix[i - 1][j - 1] == '1') {
                    dp[j] = Math.min(Math.min(dp[j - 1], prev), dp[j]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[j]);
                } else {
                    dp[j] = 0;
                }
                prev = temp;
            }
        }
        return maxsqlen * maxsqlen;
    }
}
