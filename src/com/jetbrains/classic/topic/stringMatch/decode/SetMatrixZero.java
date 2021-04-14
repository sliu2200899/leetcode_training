package com.jetbrains.classic.topic.stringMatch.decode;

public class SetMatrixZero {
    /*
      approach 1: addition memory approach

      time: O(mn)
      space: O(m + n)
   */
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        int m = matrix.length, n = matrix[0].length;

        boolean[] rows = new boolean[200];
        boolean[] cols = new boolean[200];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 0) {
                    rows[i] = true;
                    cols[j] = true;
                }
            }
        }

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] != 0 && (rows[i] || cols[j])) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /*
        approach 2: O(1) approach
        The idea is that we can use the first cell of every row and column as a flag.
        This flag would determine whether a row or column has been set to zero.
        This means for every cell instead of going to M+N cells and setting it to zero we just set the flag in two cells.
     */

    public void setZeroes2(int[][] matrix) {

        int m = matrix.length, n = matrix[0].length;

        boolean rowZero = matrix[0][0] == 0;
        for (int i = 1; i < n; ++i) {
            if (matrix[0][i] == 0) {
                rowZero = true;

            }
        }

        boolean colZero = matrix[0][0] == 0;
        for (int i = 1; i < m; ++i) {
            if (matrix[i][0] == 0) {
                colZero = true;

            }
        }


        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }


        for (int i = 0; i < m; ++i) {
            if (colZero) {
                matrix[i][0] = 0;
            }
        }
        for (int i = 0; i < n; ++i) {
            if (rowZero) {
                matrix[0][i] = 0;
            }
        }
    }
}
