package com.jetbrains.zCompanyInterviewPrep.karat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ValidMatrix {
    /*
        给一个N*N的矩阵，判定是否是有效的矩阵。有效矩阵的定义是每一行或者每一列的数字都必须正好是1到N的数。输出一个bool。

     */
    public static boolean isValidMatrix(int[][] matrix) {
        Map<Integer, Set<Integer>> cols = new HashMap<>();
        for (int i = 0; i < matrix.length; ++i) {
            cols.put(i, new HashSet<>());
        }

        for (int i = 0; i < matrix.length; ++i) {
            Set<Integer> rows = new HashSet<>();
            for (int j = 0; j < matrix[0].length; ++j) {
                int cur = matrix[i][j];
                if (cur < 1 || cur > matrix.length || rows.contains(cur) || cols.get(j).contains(cur)) {
                    return false;
                }

                rows.add(cur);
                cols.get(j).add(cur);
            }
        }

        return true;
    }

    public static void main(String[] args){
        int[][] matrix = new int[][]{{1, 2, 3}, {3, 1, 2}, {2, 3, 1}};
        System.out.println(isValidMatrix(matrix));
    }
}
