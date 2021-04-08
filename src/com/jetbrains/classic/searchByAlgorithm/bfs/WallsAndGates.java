package com.jetbrains.classic.searchByAlgorithm.bfs;

import java.util.Deque;
import java.util.LinkedList;

public class WallsAndGates {
    /*
    clarify:
        1. input, output, example
        2. modify the input directly
        3. size of the input

    algo:
        rooms = [[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]

        [2147483647,           -1,                      0,                 2147483647]
        [2147483647,           2147483647,              2147483647,         -1]
        [2147483647,           -1,                      2147483647,         -1]
        [0,                    -1,                      2147483647,         2147483647]


        bfs
        take gate as starting

        queue
        visited

    time:  O(mn)
    space: O(mn)

*/
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) {
            return;
        }

        int m = rooms.length, n = rooms[0].length;

        Deque<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (rooms[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                int[] cur = queue.poll();

                for (int[] pair : pairs) {
                    int x = cur[0] + pair[0];
                    int y = cur[1] + pair[1];

                    if (x >= 0 && y >= 0 && x < m && y < n && rooms[x][y] == 2147483647 && !visited[x][y]) {
                        queue.offer(new int[]{x,y});
                        visited[x][y] = true;
                        rooms[x][y] = step;
                    }
                }
            }
            step++;
        }
    }

    private int[][] pairs = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
}
