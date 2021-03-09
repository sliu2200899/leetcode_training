package com.jetbrains.classic.advancedDataStructure.dsGraph;

import java.util.Arrays;
import java.util.LinkedList;

public class BasicGraphDFS {
    /*
        time: O(E)  E is the number of the edges because number of edge is large than number of vertices
        space: O(V)  V is the number of the vertices
     */
    boolean found =  false;
    LinkedList<Integer>[] adj;

    public void dfs(int s, int t, int num, LinkedList<Integer>[] adj) {
        boolean[] visited = new boolean[num];
        int[] prev = new int[num];
        Arrays.fill(prev, -1);

        this.adj = adj;

        recurDfs(s, t, visited, prev);
        print(prev, s, t);
    }

    private void recurDfs(int w, int t, boolean[] visited, int[] prev) {
        if (found == true) return;
        visited[w] = true;

        if (w == t) {
            found = true;
            return;
        }

        for (int i = 0; i < adj[w].size(); ++i) {
            int q = adj[w].get(i);
            if (!visited[q]) {
                prev[q] = w;
                recurDfs(q, t, visited, prev);
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
