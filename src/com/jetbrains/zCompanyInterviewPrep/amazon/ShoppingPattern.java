package com.jetbrains.zCompanyInterviewPrep.amazon;

import java.util.HashMap;
import java.util.Map;

public class ShoppingPattern {
    /*
        undirected graph, given an integer n which is the number of nodes in the graph and an array edges, where each
        edges[i] = [ui, vi] indicates that there is an undirected edge between ui and vi.

        a connected trio is a set of three nodes where there is an edge between every pair of them
        the degree of a connected trio is the number of edges where one endpoint is in the trio, and the other is not.

        retunr the minimum degree of a connected trio in the graph, or -1 if the graph has no connected trios.
     */

    public int minTrioDegree(int n, int[][] edges) {
        int min = Integer.MAX_VALUE;
        Map<Integer, Integer> degrees = new HashMap<>();
        boolean[][] isEdge = new boolean[n+1][n+1];

        for (int[] edge : edges) {
            degrees.put(edge[0], degrees.getOrDefault(edge[0], 0) + 1);
            degrees.put(edge[1], degrees.getOrDefault(edge[1], 0) + 1);

            isEdge[edge[0]][edge[1]] = true;
            isEdge[edge[1]][edge[0]] = true;
        }

        for (int[] edge : edges) {
            for (int i = 1; i <= n; ++i) {
                // iterate through all nodes k, if (i, k) and (k, j) are also edges, then this is a trio.
                if (isEdge[i][edge[0]] && isEdge[i][edge[1]]) {
                    // subtract 6 because we do not count inner edges of a trio
                    int degree = degrees.get(i) + degrees.get(edge[0]) + degrees.get(edge[1]) - 6;
                    min = Math.min(min, degree);
                }
            }
        }

        if (min == Integer.MAX_VALUE) {
            return -1;
        }
        return min;
    }
}
