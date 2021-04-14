package com.jetbrains.classic.topic.array.matrix;

public class RotateImage {
    /*
    clarify:
        1. input, output, example
        2. space: in-place

    algo:
        (i,j) -> (x,y)

        1: (0,0) -> (0,2)
        2: (0,1) -> (1,2)
        3: (0,1) -> (2,2)
        4: (1,0) -> (0,1)
        5: (1,1) -> (1,1)
        6: (1,2) -> (2,1)
        7: (2,0) -> (0,0)
        8: (2,1) -> (1,0)
        ...
        old.           new
        (row, col) -> (col, (n - 1) - row)

        [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]

        5: (0,0) -> (0, 3)
        4: (1,1) -> (1, 2)
        ...

        (n+1)/2 outer loop
        (n)/2 inner loop

        outer loop and inner loop

    refer to:  https://leetcode.com/problems/rotate-image/submissions/
    time: O(n^2)
    space: O(1)

*/
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        int n = matrix.length;

        for (int i = 0; i < (n+1) / 2; ++i) {
            for (int j = i; j < (n-1-i); j++) {
                int p1 = matrix[i][j];
                int p2 = matrix[j][n-1-i];
                int p3 = matrix[n-1-i][n-1-j];
                int p4 = matrix[n-1-j][i];

                matrix[n-1-j][i] = p3;
                matrix[n-1-i][n-1-j] = p2;
                matrix[j][n-1-i] = p1;
                matrix[i][j] = p4;
            }
        }
    }

    /*
        approach 2:
        rotate 90 degrees could be implemented by transpose and reflect

        swap based on the diagnal
        swap based on the middle col for each row
     */
    public void rotate2(int[][] matrix) {
        transpose(matrix);
        reflect(matrix);
    }

    private void transpose(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i; j < n; ++j) {
                int tmp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }
    }

    private void reflect(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n/2; ++j) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n-1-j];
                matrix[i][n-1-j] = tmp;
            }
        }
    }
}
