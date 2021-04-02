package com.jetbrains.classic.advancedDataStructure.dsGraph;

import javafx.util.Pair;

import java.util.Deque;
import java.util.LinkedList;

public class ShortestPathVisitingAllNodes {
    /*
          shortest path -> bfs
          but how to solve the problem of revisiting some nodes during the process..
          we need to specify the state
          since graph range is pretty small just 1...12
          we can just use int[][] integer array to represent if some nodes has been visited before.

          time: O(N*2^N)
          space: O(N*2^N)
     */
    public int shortestPathLength(int[][] graph) {
        if (graph == null || graph.length == 0 || graph[0].length == 0) return 0;

        int n = graph.length;
        int avs = (1 << n) - 1;
        Deque<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(0, 0));

        boolean[][] visited = new boolean[n][1<<n];    // node as key, state as value
        for (int i = 0; i < n; ++i) {
            queue.offer(new Pair<>(i, 1 << i));
        }

        int len = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                Pair<Integer, Integer> pair = queue.poll();
                int node = pair.getKey();
                int state = pair.getValue();

                if (state == avs) {
                    return len;
                }

                if (visited[node][state]) {
                    continue;
                }

                visited[node][state] = true;
                for (int neighbor : graph[node]) {
                    queue.offer(new Pair<>(neighbor, state | 1 << neighbor));
                }
            }
            len++;
        }

        return -1;
    }
}
