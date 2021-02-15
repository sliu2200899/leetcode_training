package com.jetbrains.classic.searchByAlgorithm.bfs;

import java.util.*;

public class cutOffTree {
        /*
        analyze:
            mini height tree   using for loop
            put it in the queue
            poll one cell from the queue and

        algo:
            bfs
            queue: []
            num:
            while !queue.isEmpty()
                poll one cell from the queue
                num++
                explore all 4 directions around it
                    if cell value  == 0 || 1
                        return

                    mark the cur cell as 1
                    put cur cell in queue

            double for loop to check if there exist the cell with value (!0 && !1)


    */

    public int cutOffTree(List<List<Integer>> forest) {
        if (forest == null || forest.size() == 0) return 0;

        List<int[]> trees = new ArrayList<>();
        for (int r = 0; r < forest.size(); ++r) {
            for (int c = 0; c < forest.get(0).size(); ++c) {
                int v = forest.get(r).get(c);
                if (v > 1) trees.add(new int[]{v, r, c});
            }
        }

        trees.sort((a, b) -> Integer.compare(a[0], b[0]));

        int ans = 0, sr = 0, sc = 0;
        for (int[] tree : trees) {
            int d = bfs(forest, sr, sc, tree[1], tree[2]);
            if (d < 0) return -1;
            ans += d;
            sr = tree[1];
            sc = tree[2];
        }
        return ans;
    }

    public int bfs(List<List<Integer>> forest, int sr, int sc, int tr, int tc) {
        int R = forest.size(), C = forest.get(0).size();
        Queue<int[]> queue = new LinkedList();
        queue.add(new int[]{sr, sc, 0});
        boolean[][] seen = new boolean[R][C];
        seen[sr][sc] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == tr && cur[1] == tc) return cur[2];
            for (int[] pair: pairs) {
                int r = cur[0] + pair[0];
                int c = cur[1] + pair[1];
                if (0 <= r && r < R && 0 <= c && c < C &&
                        !seen[r][c] && forest.get(r).get(c) > 0) {
                    seen[r][c] = true;
                    queue.add(new int[]{r, c, cur[2]+1});
                }
            }
        }
        return -1;
    }

    private int[][] pairs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
}
