package com.jetbrains.master;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    //matrix 1: return all elements of the matrix in spiral order.   from matrix to list
    /*
        time complexity: O(N),
        space complexity: O(1),  without considering the output array, since we don't use any additional data structures
                for our computations
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        // four variables   top, bottom, left, right... 4 boundaries of the routes...
        // top > bottom, exit the while loop
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return res;

        int m = matrix.length, n = matrix[0].length;

        int top = 0, bottom = m  - 1, left = 0, right = n - 1, total = 0; //. the purpose of total is that we need to check every element
        while (top <= bottom && left <= right && total < n * m) {
            if (total < n * m && left <= right) {
                for (int i = left; i <= right; ++i) {
                    res.add(matrix[top][i]);
                    total++;
                }
                top++;
            }

            if (total < n * m && top <= bottom) {
                for (int i = top; i <= bottom; ++i) {
                    res.add(matrix[i][right]);
                    total++;
                }
                right--;
            }

            if (total < n * m && left <= right) {
                for (int i = right; i >= left; --i) {
                    res.add(matrix[bottom][i]);
                    total++;
                }
                bottom--;
            }

            if (total < n * m && top <= bottom) {
                for (int i = bottom; i >= top; --i) {
                    res.add(matrix[i][left]);
                    total++;
                }
                left++;
            }
        }

        return res;
    }

    // matrix 2:  generate matrix...
    public int[][] generateMatrix(int n) {
        if (n <= 0) return new int[0][0];

        int[][] arr = new int[n][n];
        int top = 0, bottom = n - 1, left = 0, right = n - 1, total = 1;

        while (top <= bottom && left <= right && total <= n * n) {
            if (total <= n * n && left <= right) {
                for (int i = left; i <= right; ++i) {
                    arr[top][i] = total++;
                }
                top++;
            }

            if (total <= n * n && top <= bottom) {
                for (int i = top; i <= bottom; ++i) {
                    arr[i][right] = total++;
                }
                right--;
            }

            if (total <= n * n && left <= right) {
                for (int i = right; i >= left; --i) {
                    arr[bottom][i] = total++;
                }

                bottom--;
            }

            if (total <= n * n && top <= bottom) {
                for (int i = bottom; i >= top; --i) {
                    arr[i][left] = total++;
                }

                left++;
            }
        }
        return arr;
    }
}
