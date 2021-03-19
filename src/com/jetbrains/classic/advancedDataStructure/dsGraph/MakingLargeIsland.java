package com.jetbrains.classic.advancedDataStructure.dsGraph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MakingLargeIsland {
    /*
        Explanation
        Only 2 steps:

        Explore every island using DFS, count its area, give it an island index and save the result to a {index: area} map.
        Loop every cell == 0, check its connected islands and calculate total islands area.

        Complexity
        Time: O(N^2)
        space: O(n^2)
     */
    public int largestIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        int color = 2, max = 0;;
        Map<Integer, Integer> map = new HashMap<>(); // color as key and area of connected component as value
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    int num = dfs(grid, i, j, color);
                    max = Math.max(max, num);
                    map.put(color, num);
                    color++;
                }
            }
        }

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    Set<Integer> set = new HashSet<>();

                    int area = 1;
                    int[][] pairs = new int[][]{{-1,0}, {1,0}, {0,1}, {0,-1}};
                    for (int[] pair : pairs) {
                        int x = pair[0] + i;
                        int y = pair[1] + j;
                        if (x >= 0 && x < m && y >= 0 && y < n && !set.contains(grid[x][y])) {
                            set.add(grid[x][y]);
                            area += map.getOrDefault(grid[x][y], 0);
                        }
                    }

                    max = Math.max(max, area);
                }
            }
        }

        return max;
    }


    private int dfs(int[][] grid, int row, int col, int color) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] != 1) {
            return 0;
        }

        grid[row][col] = color;

        int[][] pairs = new int[][]{{-1,0}, {1,0}, {0,1}, {0,-1}};
        int num = 1;
        for (int[] pair: pairs) {
            int x = pair[0] + row;
            int y = pair[1] + col;

            num += dfs(grid, x, y, color);
        }
        return num;
    }
}
