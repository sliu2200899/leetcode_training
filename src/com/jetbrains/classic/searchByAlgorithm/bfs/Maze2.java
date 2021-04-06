package com.jetbrains.classic.searchByAlgorithm.bfs;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Maze2 {
    /*
        bfs w/o visited

        // bfs
        // queue
        // set/array to check visited

        // first problem:
        //    while()
        // second problem: how to update visited array
        //    1.use step + 2D array  -> problem
        //    2.distance -> problem.
     */
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int[][] distance = new int[maze.length][maze[0].length];
        for (int[] row : distance) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        distance[start[0]][start[1]] = 0;

        int[][] dirs = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};

        Deque<int[]> queue = new LinkedList<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                int[] cur = queue.poll();
                for (int[] dir : dirs) {
                    int x = cur[0] + dir[0];
                    int y = cur[1] + dir[1];

                    int count = 0;
                    while (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0) {
                        x += dir[0];
                        y += dir[1];
                        count ++;
                    }

                    // check the distance, don't need visited array to check multiple visite since the distance in the latter visit is definitely larger than the distance in the first time visit,
                    if (distance[cur[0]][cur[1]] + count < distance[x - dir[0]][y - dir[1]]) {
                        distance[x - dir[0]][y - dir[1]] = distance[cur[0]][cur[1]] + count;
                        queue.offer(new int[]{x - dir[0], y - dir[1]});
                    }
                }
            }
        }
        return distance[destination[0]][destination[1]] != Integer.MAX_VALUE ? distance[destination[0]][destination[1]] : -1;
    }

    /*
        dijkstra
     */
    public int shortestDistance2(int[][] maze, int[] start, int[] dest) {
        int[][] distance = new int[maze.length][maze[0].length];
        for (int[] row: distance)
            Arrays.fill(row, Integer.MAX_VALUE);
        distance[start[0]][start[1]] = 0;
        dijkstra(maze, start, distance);
        return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
    }

    public void dijkstra(int[][] maze, int[] start, int[][] distance) {
        int[][] dirs={{0,1},{0,-1},{-1,0},{1,0}};
        PriorityQueue< int[] > queue = new PriorityQueue < > ((a, b) -> a[2] - b[2]);
        queue.offer(new int[]{start[0],start[1],0});
        while (!queue.isEmpty()) {
            int[] s = queue.poll();
            if(distance[s[0]][s[1]] < s[2])
                continue;
            for (int[] dir: dirs) {
                int x = s[0] + dir[0];
                int y = s[1] + dir[1];
                int count = 0;
                while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                    count++;
                }
                if (distance[s[0]][s[1]] + count < distance[x - dir[0]][y - dir[1]]) {
                    distance[x - dir[0]][y - dir[1]] = distance[s[0]][s[1]] + count;
                    queue.offer(new int[]{x - dir[0], y - dir[1], distance[x - dir[0]][y - dir[1]]});
                }
            }
        }
    }
}
