package com.jetbrains.zCompanyInterviewPrep.karat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindRectangles {
    /*
        1. find single rectangles

        there is an image filled with 0s and 1s. There is at most one rectangle in this image filled with 0s,
        find the rectangle. Output could be the coordinates of top-left and bottom-right elements of the rectangle,
        or top-left element, width and height.

     */
    public static void main(String[] args){
        int[][] board = new int[][]{{1,1,1,1}, {1,0,0,1}, {1,0,0,1}, {1,1,1,1}};
        List<List<int[]>> res = findSingleRectangle(board);
        for(List<int[]> l : res){
            System.out.print(Arrays.toString(l.get(0)));
            System.out.println(Arrays.toString(l.get(1)));
        }
    }
    public static List<List<int[]>> findSingleRectangle(int[][] board) {
        List<List<int[]>> res = new ArrayList<>();
        if (board == null || board.length == 0 || board[0].length == 0) {
            return res;
        }
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (board[i][j] == 0) {
                    List<int[]> rectangle = new ArrayList<>();
                    rectangle.add(new int[]{i, j});
                    board[i][j] = 1;
                    int height = 1, width = 1;
                    while (i + height < board.length && board[i + height][j] == 0) {
                        height++;
                    }
                    while (j + width < board[0].length && board[i][j + width] == 0) {
                        width++;
                    }

                    for (int h = 0; h < height; ++h) {
                        for (int w = 0; w < width; ++w) {
                            board[i + h][j + w] = 1;
                        }
                    }

                    rectangle.add(new int[]{i + height - 1, j + width - 1});
                    res.add(rectangle);
                }
            }
        }

        return res;
    }
}
