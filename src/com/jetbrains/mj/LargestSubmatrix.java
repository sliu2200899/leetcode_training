package com.jetbrains.mj;

import java.util.Arrays;

public class LargestSubmatrix {
    /*
        something like the maximal square
        calculate all the 1 from (i,j) to (i,0)
        then, think about how to compute the maximal area ...
     */
    public int largestSubmatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

        for (int i = 1; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                if (matrix[i][j] != 0) {
                    matrix[i][j] += matrix[i-1][j];
                }
            }
        }

        int res = 0;
        for (int i = 0; i < matrix.length; ++i) {
            Arrays.sort(matrix[i]);
            for (int j = 0; j < matrix[i].length; ++j) {
                res = Math.max(res, matrix[i][j] * (matrix[i].length - 1 - j + 1));
            }
        }
        return res;
    }
}
