package com.jetbrains.classic.advancedDataStructure.dsGraph;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class BasicGraphBFS {

    /*
        time: O(E)  E is the number of the edges because number of edge is large than number of vertices
        space: O(V)  V is the number of the vertices
     */
    public void bfs(int s, int t, int num, LinkedList<Integer>[] adj) {
        if (s == t) return;
        boolean[] visited = new boolean[num];
        visited[s] = true;
        Deque<Integer> queue = new LinkedList<>();
        queue.offer(s);

        int[] prev = new int[num];
        Arrays.fill(prev, -1);

        while (queue.size() != 0) {
            int w = queue.poll();
            for (int i = 0; i < adj[w].size(); ++i) {
                int q = adj[w].get(i);
                if (!visited[q]) {
                    prev[q] = w;
                    if (q == t) {
                        print(prev, s, t);
                        return;
                    }
                }
                visited[q] = true;
                queue.offer(q);
            }
        }
    }

    private void print(int[] prev, int s, int t) {
        if (prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }

        System.out.println(t + " ");
    }
}
