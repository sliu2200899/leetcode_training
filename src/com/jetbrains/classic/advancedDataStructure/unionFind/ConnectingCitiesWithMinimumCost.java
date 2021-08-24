package com.jetbrains.classic.advancedDataStructure.unionFind;

import java.util.Arrays;

public class ConnectingCitiesWithMinimumCost {
    /*
        If we model the cities and connections as a graph, each connection is an edge (undirected) and each city is a node of the graph.
        We need to find a subset of edges which connects all the nodes of the graph with the minimum possible total weight.
        This is by definition the Minimum Spanning Tree or MST of a graph.

        There are a variety of algorithms that we can use to obtain the MST of a graph. We will use Kruskal's algorithm here,
        which is implemented using the Disjoint set Union-Find data structure.

        In order to obtain the MST using Kruskal's algorithm, we first sort all the connections (edges) present in the graph based on their
        weights (in increasing order) and will iterate over them one by one. The objective here is to greedily pick edges that will help us
        to connect more nodes in the graph. Each time we find a new edge which does not result in a cycle with the edges selected so far,
        we add that edge in the final MST. We keep doing this till we have obtained the MST which connects all the nodes in the graph
        (i.e. connects all the cities using the connections).


        If we combine both Path compression and Weighted Union, it takes log N for the union and find operations in case of Disjoint-set union link

        time: O(MlogN)
        space: O(N)
     */
    public int minimumCost(int n, int[][] connections) {
        UnionFind uf = new UnionFind(n);

        Arrays.sort(connections, (a, b) -> a[2] - b[2]);

        // keep track of total edges add in the MST and total cost
        int total = 0, cost = 0;

        for (int i = 0; i < connections.length; ++i) {
            int a = connections[i][0] - 1;
            int b = connections[i][1] - 1;

            if (uf.isInSameGroup(a, b)) {
                continue;
            }

            uf.connect(a, b);

            cost += connections[i][2];

            total++;
        }

        if (total == n - 1) {
            return cost;
        } else {
            return -1;
        }
    }

    private class UnionFind {
        private int[] weights;
        private int[] father;
        private int count;

        public UnionFind(int n) {
            weights = new int[n];
            father = new int[n];
            for (int i = 0; i < n; ++i) {
                father[i] = i;
            }
        }

        public void set_count(int n) {
            this.count = n;
        }

        public int query() {
            return this.count;
        }

        public void connect(int a, int b) {
            int root_a = find(a);
            int root_b = find(b);
            if (root_a != root_b) {
                if(weights[root_a] > weights[root_b]) {
                    father[root_b] = root_a;
                    weights[root_a] += weights[root_b];
                } else {
                    father[root_a] = root_b;
                    weights[root_b] += weights[root_a];
                }
                count--;
            }
        }

        public int find(int a) {
            if (a == father[a]) {
                return a;
            }

            return father[a] = find(father[a]);
        }

        public boolean isInSameGroup(int a, int b) {
            return find(a) == find(b);
        }
    }
}
