package com.jetbrains.hard;

import java.util.Comparator;
import java.util.PriorityQueue;

public class TrapRainWater2 {

    class Cell {
        int val;
        int row;
        int col;

        public Cell(int num, int row, int col) {
            this.val = num;
            this.row = row;
            this.col = col;
        }
    }

    public int trapRainWater2(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) return 0;

        int m = heightMap.length;
        int n = heightMap[0].length;

        PriorityQueue<Cell> pqmin = new PriorityQueue<>((a, b) -> {
            return Integer.compare(a.val, b.val);
        });

        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; ++i) {
            visited[i][0] = true;
            visited[i][n - 1] = true;
            pqmin.offer(new Cell(heightMap[i][0], i, 0));
            pqmin.offer(new Cell(heightMap[i][n - 1], i, n - 1));
        }

        for (int i = 0; i < n; ++i) {
            visited[0][i] = true;
            visited[m - 1][i] = true;
            pqmin.offer(new Cell(heightMap[0][i], 0, i));
            pqmin.offer(new Cell(heightMap[m - 1][i], m - 1, i));
        }

        int area = 0;

        while (!pqmin.isEmpty()) {
            Cell c = pqmin.poll();

            for (int[] pair: pairs) {
                int x = pair[0] + c.row;
                int y = pair[1] + c.col;

                if (x < 0 || x > m - 1 || y < 0 || y > n - 1 || visited[x][y]) continue;

                visited[x][y] = true;

                if (heightMap[x][y] <= c.val) {
                    area += (c.val - heightMap[x][y]);
                }

                pqmin.offer(new Cell(Math.max(heightMap[x][y], c.val), x, y));  //
            }
        }

        return area;
    }

    private int[][] pairs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

}
