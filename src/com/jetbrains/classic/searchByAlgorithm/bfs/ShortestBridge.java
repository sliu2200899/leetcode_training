package com.jetbrains.classic.searchByAlgorithm.bfs;

import java.util.Deque;
import java.util.LinkedList;

public class ShortestBridge {

    /*
        analyze:
            step1: dfs to find an island, mark it in 'visited'
            step2: bfs to expand this island

        algo:
            for loop to call dfs()
            dfs(A, i, j, visited, queue)
                check if cur cell is inside the matrix boundary, not visited, value != 0

                for 4 dir:
                    dfs()

            bfs to explore this island till we touch 1's
                while (!queue.isEmpty())
                    expand one step each time
                    check if its value == 1
                        return step

            return -1

        test:


        time complexity:
        space complexity:

    */

    public int shortestBridge(int[][] A) {
        if (A == null || A.length == 0 || A[0].length == 0) return 0;

        int m = A.length, n = A[0].length;

        // 1. dfs to find an island, mark it in 'visited'
        boolean[][] visited = new boolean[m][n];
        int[][] dirs = new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}};
        Deque<int[]> queue = new LinkedList<>();

        boolean found = false;
        for (int i = 0; i < A.length; ++i) {
            if (found) {
                break;
            }
            for (int j = 0; j < A[0].length; ++j) {
                if (A[i][j] == 1) {
                    dfs(A, visited, queue, i, j, dirs);
                    found = true;
                    break;
                }
            }
        }

        // 2. bfs to expand this island
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                int[] cur = queue.poll();
                for (int[] dir : dirs) {
                    int x = dir[0] + cur[0];
                    int y = dir[1] + cur[1];

                    if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]) {
                        if (A[x][y] == 1) {
                            return step;
                        }
                        queue.offer(new int[]{x, y});
                        visited[x][y] = true;
                    }
                }
            }
            step++;
        }

        return -1;
    }

    private void dfs(int[][] A, boolean[][] visited, Deque<int[]> queue, int row, int col, int[][] dirs) {
        if (row < 0 || row >= A.length || col < 0 || col >= A[0].length || visited[row][col]) {
            return;
        }

        visited[row][col] = true;
        queue.offer(new int[]{row, col}); //important to understand why we put it in queue here
        for (int[] dir : dirs) {
            int x = row + dir[0];
            int y = col + dir[1];

            dfs(A, visited, queue, x, y, dirs);
        }
    }
}
