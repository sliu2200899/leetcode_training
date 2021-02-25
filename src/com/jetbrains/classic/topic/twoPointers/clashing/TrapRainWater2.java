package com.jetbrains.classic.topic.twoPointers.clashing;

import java.util.PriorityQueue;

public class TrapRainWater2 {
    /*
    analyze:
        2D height map
        pqmin store the cell infor at the boundary of 2D
        each time, pop up the smallest cell and assign it as the height_max
        pop up another cell and check

    algo:
        pqmin
        put all the boundary cell into the pqmin

        height_max and init by assigning the pqmin.pop()

        while !pqmin.isEmpty()
            pop up one cell
            if (height > Height_max) update height_max
            else sum += (height_max - height)

        return sum

*/
    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) return 0;

        int m = heightMap.length, n = heightMap[0].length;

        PriorityQueue<int[]> pqmin = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; ++i) {
            pqmin.offer(new int[]{i, 0, heightMap[i][0]});
            pqmin.offer(new int[]{i, n - 1, heightMap[i][n - 1]});

            visited[i][0] = true;
            visited[i][n-1] = true;
        }

        for (int i = 0; i < n; ++i) {
            pqmin.offer(new int[]{0, i, heightMap[0][i]});
            pqmin.offer(new int[]{m-1, i, heightMap[m-1][i]});

            visited[0][i] = true;
            visited[m-1][i] = true;
        }



        int height_max = 0;
        int sum = 0;
        while (!pqmin.isEmpty()) {
            int[] cell = pqmin.poll();
            if (cell[2] > height_max) {
                height_max = cell[2];
            } else {
                sum += (height_max - cell[2]);
            }

            for (int[] pair : pairs) {
                int x = pair[0] + cell[0];
                int y = pair[1] + cell[1];

                if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]) {
                    pqmin.offer(new int[]{x, y, heightMap[x][y]});
                    visited[x][y] = true;
                }
            }

        }

        return sum;
    }

    private int[][] pairs = new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}};
}
