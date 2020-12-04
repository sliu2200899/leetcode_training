package com.jetbrains.innerStructure;

import java.util.LinkedList;

public class Graph {
    private int v;
    private LinkedList<Integer> adj[]; // adjacency list

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList<>();
        }
    }

    public LinkedList<Integer>[] getAdj() {
        return adj;
    }

    public int numVertices() {
        return v;
    }

    public void addEdge(int s, int t) {  // store two copies of them if it's an undirected graph
        adj[s].add(t);
        adj[t].add(s);
    }
}
