package com.jetbrains.classic.searchByAlgorithm.bfs;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Matrix01 {
    /*
        using BFS  since this problem asks us to find the distance of the nearest 0 for each cell
        similar to bruteforce

        time: O((r*c)^2)
        space: O(r*c)
     */

    public int[][] updateMatrix(int[][] matrix) {

        int m = matrix.length, n = matrix[0].length;

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] = bfs(matrix, i, j);
                }
            }
        }

        return matrix;
    }

    private int bfs(int[][] matrix, int row, int col) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

        int m = matrix.length, n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        Deque<Cell> queue = new LinkedList<>();
        queue.offer(new Cell(row, col));
        visited[row][col] = true;

        int num = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            num++;
            for (int i = 0; i < size; ++i) {
                Cell c = queue.poll();
                for (int[] pair : pairs) {
                    int x = pair[0] + c.row;
                    int y = pair[1] + c.col;

                    if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]) {
                        if (matrix[x][y] == 0) {
                            return num;
                        } else {
                            visited[x][y] = true;
                            queue.offer(new Cell(x, y));
                        }
                    }
                }
            }
        }
        return num;
    }

    private class Cell{

        public int row;
        public int col;

        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private int[][] pairs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};


    /*
        solution 2: using bfs one time

        A better brute force: Looking over the entire matrix appears wasteful and hence, we can use Breadth First Search(BFS) to limit the search to the nearest 0 found for each 1.
        As soon as a 0 appears during the BFS, we know that the 0 is nearest, and hence, we move to the next 1.

        Think again: But, in this approach, we will only be able to update the distance of one 1 using one BFS, which could in fact, result in slightly higher complexity
        than the Approach #1 brute force. But hey,this could be optimised if we start the BFS from 0s and thereby, updating the distances of all the 1s in the path.

        time: O(rc)
        space: O(rc)
     */

    public int[][] updateMatrix2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        Deque<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] pair : dir) {
                int x = cur[0] + pair[0];
                int y = cur[1] + pair[1];
                if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y]) {
                    continue;
                }

                visited[x][y] = true;
                matrix[x][y] = matrix[cur[0]][cur[1]] + 1;
                queue.offer(new int[]{x, y});
            }
        }

        return matrix;
    }

    /*
        solution 3: using DP

     */

    public int[][] updateMatrix3(int[][] matrix) {
        /*
            dp[i][j] represents the distance of nearest 0. for (i,j)

        */

        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        for (int[] tmp : dp) Arrays.fill(tmp, m*n);  // canot set Integer.MAX_VALUE it will stack overflow...

        // first pass: check for left and top
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 0) {
                    dp[i][j] = 0;
                } else {
                    if (i > 0) {
                        dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + 1);
                    }
                    if (j > 0) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][j-1] + 1);
                    }
                }

            }
        }

        // second pass: check for right and bottom
        for (int i = m - 1; i >=0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (i < m - 1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i+1][j] + 1);
                }
                if (j < n - 1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j+1] + 1);
                }
            }
        }

        return dp;
    }
}
