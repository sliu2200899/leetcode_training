package com.jetbrains.classic.searchByAlgorithm;

import java.util.Deque;
import java.util.LinkedList;

public class Maze {
    /*
        maze 1:  true or false there exist some path from start to destination
     */
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0 || maze[0].length == 0) {
            return false;
        }
        int m = maze.length, n = maze[0].length;
        boolean[][] visited = new boolean[m][n];

        return dfs(maze, start[0], start[1], destination, visited);
    }

    private boolean dfs(int[][] maze, int row, int col, int[] destination, boolean[][] visited) {
        if (visited[row][col]) {
            return false;
        }

        visited[row][col] = true;

        if (row == destination[0] && col == destination[1]) return true;

        // explore 4 directions around the cur cell

        int L = col - 1;
        int R = col + 1;
        int U = row - 1;
        int D = row + 1;

        // left
        while (L >= 0 && maze[row][L] != 1) L--;
        if (dfs(maze, row, L+1, destination, visited)) return true;

        // right
        while (R < maze[0].length && maze[row][R] != 1) R++;
        if (dfs(maze, row, R-1, destination, visited)) return true;

        // upper
        while (U >= 0 && maze[U][col] != 1) U--;
        if (dfs(maze, U+1, col, destination, visited)) return true;

        // down
        while (D < maze.length && maze[D][col] != 1) D++;
        if (dfs(maze, D-1, col, destination, visited)) return true;


        return false;
    }

    /*
        use bfs to solve the problem
        the key is how to let ball move multiple step instead of one
     */
    public boolean hasPath2(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        int[][] pairs = new int[][]{{-1,0},{1,0},{0,-1}, {0,1}};

        Deque<int[]> queue = new LinkedList<>();
        queue.offer(start);
        visited[start[0]][start[1]] = true;

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                int[] cur = queue.poll();
                if (cur[0] == destination[0] && cur[1] == destination[1]) {
                    return true;
                }
                for (int[] pair : pairs) {
                    int newx = cur[0] + pair[0];
                    int newy = cur[1] + pair[1];

                    while (newx >= 0 && newy >= 0 && newx < maze.length && newy < maze[0].length && maze[newx][newy] == 0) {
                        newx += pair[0];
                        newy += pair[1];
                    }

                    if (!visited[newx - pair[0]][newy - pair[1]]) {
                        queue.offer(new int[]{newx - pair[0], newy - pair[1]});
                        visited[newx - pair[0]][newy - pair[1]] = true;
                    }
                }
            }
        }
        return false;
    }

    /*
        maze 2:  return the shortest path from start to destination
     */


}
