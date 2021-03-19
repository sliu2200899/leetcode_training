package com.jetbrains.classic.advancedDataStructure.dsGraph;

public class MaxAreaIslands {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length, n = grid[0].length;

        boolean[][] visited = new boolean[m][n];
        int max = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    max = Math.max(max, dfs(grid, i, j, visited));
                }
            }
        }
        return max;
    }

    private int dfs(int[][] grid, int row, int col, boolean[][] visited) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || visited[row][col] || grid[row][col] == 0) {
            return 0;
        }

        visited[row][col] = true;

        int num = 1;
        int[][] pairs = new int[][]{{-1,0}, {1,0}, {0,1}, {0,-1}};
        for (int[] pair : pairs) {
            int x = pair[0] + row;
            int y = pair[1] + col;

            num += dfs(grid, x, y, visited);
        }

        return num;
    }
}
