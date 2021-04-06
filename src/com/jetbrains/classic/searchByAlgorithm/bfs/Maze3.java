package com.jetbrains.classic.searchByAlgorithm.bfs;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Maze3 {
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        // queue.
        // distance
        // path 2D
        if (maze == null || maze.length == 0 || maze[0].length == 0) {
            return "impossible";
        }

        int m = maze.length, n = maze[0].length;

        Deque<int[]> queue = new LinkedList<>();
        queue.offer(ball);

        int[][] distance = new int[m][n];
        for (int[] row : distance) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        distance[ball[0]][ball[1]] = 0;

        String[][] paths = new String[m][n];
        for (String[] row : paths) {
            Arrays.fill(row, "");
        }
        paths[hole[0]][hole[1]] = "end";

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            // hole 1.
            if (cur[0] == hole[0] && cur[1] == hole[1]) {
                continue;
            }

            for (int i = 0; i < 4; ++i) {
                int x = cur[0];
                int y = cur[1];
//                 for(String[] p : paths) {
//                      System.out.println(Arrays.toString(p));
//                 }

                String path = paths[x][y] + pa[i];
                int count = distance[x][y];
                while (x + pair[i][0] >= 0 && x + pair[i][0] < m && y + pair[i][1] >= 0 && y + pair[i][1] < n && maze[x + pair[i][0]][y + pair[i][1]] == 0) {
                    x += pair[i][0];
                    y += pair[i][1];
                    count ++;

                    // hole 2.
                    if (x == hole[0] && y == hole[1]) {
                        break;
                    }
                }

                // hole 3. since next if check count == distance, we need to check (x, y) is (cur[0], cur[1]) itself
                // otherwise, it will result in dead loop
                if (x == cur[0] && y == cur[1]) {
                    continue;
                }

                if (count <= distance[x][y]) {
                    if (count < distance[x][y]) {
                        distance[x][y] = count;
                        paths[x][y] = path;
                    } else if (path.compareTo(paths[x][y]) < 0) {
                        paths[x][y] = path;
                    }
                    queue.offer(new int[]{x, y});
                }
            }
        }
        return paths[hole[0]][hole[1]].equals("end") ? "impossible" : paths[hole[0]][hole[1]];
    }
    private int[][] pair = new int[][]{{-1,0},{1,0},{0,1},{0,-1}};
    private static final String[] pa = new String[] {"u", "d", "r", "l"};
}
