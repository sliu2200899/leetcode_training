package com.jetbrains.classic.searchByAlgorithm.bfs;

import java.util.Deque;
import java.util.LinkedList;

public class SurroundedRegion {

    /*
        clarify:
            1.input, output, example
        algo:
            board

            for loop
                for loop
                    if O
    */
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }

        int m = board.length, n = board[0].length;

        Deque<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; ++i) {
            if (board[i][0] == 'O') {
                queue.offer(new int[]{i, 0});
                visited[i][0] = true;
            }

            if (board[i][n-1] == 'O') {
                queue.offer(new int[]{i, n-1});
                visited[i][n-1] = true;
            }
        }

        for (int i = 0; i < n; ++i) {
            if (board[0][i] == 'O') {
                queue.offer(new int[]{0, i});
                visited[0][i] = true;
            }

            if (board[m-1][i] == 'O') {
                queue.offer(new int[]{m-1, i});
                visited[m-1][i] = true;
            }
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int[] pair : pairs) {
                int x = cur[0] + pair[0];
                int y = cur[1] + pair[1];

                if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y] && board[x][y] == 'O') {
                    visited[x][y] = true;
                    queue.offer(new int[]{x, y});
                }
            }
        }

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == 'O' && !visited[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private int[][] pairs = {{-1,0}, {1,0}, {0,1}, {0,-1}};
}
