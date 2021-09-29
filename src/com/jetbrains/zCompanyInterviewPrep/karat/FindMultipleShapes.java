package com.jetbrains.zCompanyInterviewPrep.karat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindMultipleShapes {
    /*
        the image has random shapes filled with 0s, separated by 1s. Find all the shapes.
        Each shape is represented by coordinates of all the elements inside.
     */
    public static List<List<int[]>> findMultipleShapes(int[][] board) {
        List<List<int[]>> res = new ArrayList<>();
        if (board == null || board.length == 0 || board[0].length == 0) {
            return res;
        }

        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (board[i][j] == 0) {
                    List<int[]> list = new ArrayList<>();
                    dfs(i, j, board, list);
                    res.add(list);
                }
            }
        }

        return res;
    }

    private static final int[][] dirs = new int[][]{{-1,0}, {1,0}, {0, -1}, {0,1}};

    private static void dfs(int i, int j, int[][] board, List<int[]> list) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == 1) {
            return;
        }

        board[i][j] = 1;

        list.add(new int[]{i, j});

        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];

            dfs(x, y, board, list);
        }
    }

    public static void main(String[] args) {
        int[][] board = new int[][]{{1,1,1,1}, {1,0,0,1}, {1,0,0,1}, {1,1,1,1}};
        List<List<int[]>> res = findMultipleShapes(board);
        for(List<int[]> l : res){
            System.out.print(Arrays.toString(l.get(0)));
            System.out.println(Arrays.toString(l.get(1)));
        }
    }

}
