package com.jetbrains.classic.searchByAlgorithm.bfs;

import java.util.Deque;
import java.util.LinkedList;

public class MinimumKnightMoves {
    public int minKnightMoves(int x, int y) {
        Deque<int[]> queue = new LinkedList<>();

        // - Rather than using the inefficient HashSet, we use the bitmap
        //     otherwise we would run out of time for the test cases.
        // - We create a bitmap that is sufficient to cover all the possible
        //     inputs, according to the description of the problem.
        boolean[][] visited = new boolean[605][605];

        queue.offer(new int[]{0,0});
        visited[302][302] = true;

        int step = 0;
        while (!queue.isEmpty()) {
            int num = queue.size();
            for (int i = 0; i < num; ++i) {

                int[] cur = queue.poll();
                if (cur[0] == x && cur[1] == y) {
                    return step;
                }

                for (int[] dir : dirs) {
                    int newX = cur[0] + dir[0];
                    int newY = cur[1] + dir[1];

                    if (!visited[newX + 302][newY + 302]) {
                        queue.offer(new int[]{newX, newY});
                        visited[newX + 302][newY + 302] = true;
                    }
                }
            }
            step++;
        }

        return step;
    }

    private int[][] dirs = new int[][]{{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1,-2}, {-1,-2}, {-2, -1}};
}
