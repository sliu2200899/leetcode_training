package com.jetbrains.classic.advancedDataStructure.dsGraph;

import java.util.Deque;
import java.util.LinkedList;

public class ShortestPathAllKeys {
    /*
        basic bfs + some special case + bit operation

        time: O(m*n*2^keys)
        space: O(m*n*2^keys)
     */
    public int shortestPathAllKeys(String[] grid) {
        if (grid == null || grid.length == 0 || grid[0].length() == 0) {
            return 0;
        }

        int m = grid.length, n = grid[0].length();

        Deque<Integer> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[m][n][64];

        int avk = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                char c = grid[i].charAt(j);
                if (c == '@') {
                    queue.offer((i<<16 | j<<8 | 0));
                    visited[i][j][0] = true;
                } else if (c >= 'a' && c <= 'f') {
                    avk |= (1 << (c - 'a'));
                }
            }
        }

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                int cur = queue.poll();

                int curX = (cur>>16);
                int curY = ((cur>>8) & 255);
                int curK = (cur & 255);

                if (curK == avk) {
                    return step;
                }

                for (int[] pair: pairs) {
                    int newX = curX + pair[0];
                    int newY = curY + pair[1];
                    int newKey = curK;

                    if (newX < 0 || newX >= m || newY < 0 || newY >= n) continue;

                    char c = grid[newX].charAt(newY);

                    if (c == '#') continue; // wall
                    // do not have the key
                    if (c >= 'A' && c <= 'F' && (newKey & (1 << (c - 'A'))) == 0) continue;
                    // update teh keys we have
                    if (c >= 'a' && c <= 'f') newKey |= (1 << (c - 'a'));

                    if (visited[newX][newY][newKey]) continue;

                    queue.offer((newX << 16) | (newY << 8) | (newKey));
                    visited[newX][newY][newKey] = true;
                }

            }

            step++;
        }

        return -1;

    }

    private int[][] pairs = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
}
