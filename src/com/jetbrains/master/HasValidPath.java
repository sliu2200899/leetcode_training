package com.jetbrains.master;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class HasValidPath {
    public boolean hasValidPath(int[][] grid) {
        // for the basic module, we need to consider the
        // BFS algorithem to explore the valid path from the upper-left to bottom right cell.
        // cell. put the potential cell to the queue.
        // (what is potential cell?) the cell that is connected and invisited...

        if (grid == null || grid.length == 0 || grid[0].length == 0) return false;

        Deque<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});

        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                int[] cell = queue.poll();
                if (cell[0] == m - 1 && cell[1] == n - 1) return true;
                for (int[] pair : pairs) {
                    int x = pair[0] + cell[0];
                    int y = pair[1] + cell[1];

                    if ( x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]) {
                        if ((pair[0] == -1 && isInArray(conn[0], grid[cell[0]][cell[1]]) && isInArray(conn[3],grid[x][y])) ||
                                (pair[0] == 1 && isInArray(conn[3], grid[cell[0]][cell[1]]) && isInArray(conn[0],grid[x][y])) ||
                                (pair[1] == -1 && isInArray(conn[1], grid[cell[0]][cell[1]]) && isInArray(conn[2],grid[x][y])) ||
                                (pair[1] == 1 && isInArray(conn[2], grid[cell[0]][cell[1]]) && isInArray(conn[1],grid[x][y]))) {

                            visited[x][y] = true;
                            queue.offer(new int[]{x, y});
                        }
                    }

                }
            }
        }
        return false;
    }

    private boolean isInArray(int[] arr, int num) {
        boolean test = false;
        for (int element : arr) {
            if (element == num) {
                test = true;
                break;
            }
        }
        return test;
    }

    int[][] pairs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    int[][] conn = new int[][]{{2,5,6}, {1,3,5}, {1,4,6}, {2,3,4}};
}
