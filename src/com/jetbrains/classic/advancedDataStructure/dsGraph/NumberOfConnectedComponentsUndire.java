package com.jetbrains.classic.advancedDataStructure.dsGraph;

import java.util.ArrayList;
import java.util.List;

public class NumberOfConnectedComponentsUndire {
    /*
        DFS to traverse the undirected graph with representation of adjacency list
     */
    public int countComponents(int n, int[][] edges) {

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            adj.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < edges.length; ++i) {
            adj.get(edges[i][0]).add(edges[i][1]);
            adj.get(edges[i][1]).add(edges[i][0]);
        }

        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; ++i) {
            if (!visited[i]) {
                dfs(adj, visited, i);
                count++;
            };
        }
        return count;
    }

    private void dfs(List<List<Integer>> list, boolean[] visited, int index) {
        visited[index] = true;
        for (int other : list.get(index)) {
            if (!visited[other]) {
                dfs(list, visited, other);
            }
        }
    }

    /*
        union find
     */
    private class UnionFind {
        public int[] father;
        public int count;
        public int[] rank;

        public UnionFind(int n) {
            rank = new int[n];
            father = new int[n];
            for (int i = 0; i < n; ++i) {
                father[i] = i;
            }
        }

        public void connect (int a, int b) {
            int root_a = find(a);
            int root_b = find(b);
            if (root_a != root_b) {
                if (rank[root_a] > rank[root_b]) {
                    father[root_b] = root_a;
                } else if (rank[root_a] < rank[root_b]) {
                    father[root_a] = root_b;
                } else {
                    father[root_b] = root_a;
                    rank[root_a] += 1;
                }
                count--;
            }
        }

        public int find(int x) {
            if (x == father[x]) {
                return x;
            }

            return father[x] = find(father[x]);     // path compression
        }

        public int query() {
            return count;
        }

        public void set_count(int n) {
            count = n;
        }
    }
    public int countComponents2(int n, int[][] edges) {

        UnionFind uf = new UnionFind(n);
        uf.set_count(n);

        for (int[] edge : edges) {
            uf.connect(edge[0], edge[1]);
        }

        return uf.query();
    }
}
