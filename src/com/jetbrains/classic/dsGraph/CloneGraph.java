package com.jetbrains.classic.dsGraph;

import java.util.*;

public class CloneGraph {
    private class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    /*
        Depth first search

        algo
        1. Start traversing the graph from the given node.
        2. We would take a hash map to store the reference of the copy of all the nodes that have already been visited and cloned.
        The key for the hash map would be the node of the original graph and corresponding value would be the corresponding cloned
        node of the cloned graph. If the node already exists in the visited we return corresponding stored reference of the cloned node.

        3. If we don't find the node in the visited hash map, we create a copy of it and put it in the hash map. Note, how it's
        important to create a copy of the node and add to the hash map before entering recursion.

        4. Now make the recursive call for the neighbors of the node. Pay attention to how many recursion calls we will be making
        for any given node. For a given node the number of recursive calls would be equal to the number of its neighbors. Each recursive
        call made would return the clone of a neighbor.
     */

    /*
        time complexity: O(N + M) where N is a number of nodes (vertices) and M is a number of edges.
        space complexity: O(N)  This space is occupied by the visited hash map and in addition to that, space would also be occupied by
        the recursion stack since we are adopting a recursive approach here.

     */
    private HashMap<Node, Node> visited = new HashMap<>();
    public Node cloneGraph(Node node) {
        if(node == null) return node;

        // if the node was already visited before
        // return the clone from the visited dictionary
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        Node cloneNode = new Node(node.val, new ArrayList());
        // the key is original node and value being the clone node
        visited.put(node, cloneNode);

        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }

        return cloneNode;
    }

    /*
        bfs
        time complexity: O(N + M)
        space complexity: O(N)
     */

    public Node cloneGraph2(Node node) {
        if (node == null) {
            return node;
        }

        Map<Node, Node> visited = new HashMap<>();

        Deque<Node> queue = new LinkedList<>();
        queue.offer(node);

        visited.put(node, new Node(node.val, new ArrayList<>()));

        //bfs
        while (!queue.isEmpty()) {
            Node n = queue.poll();
            for (Node neighbor : n.neighbors) {
                if (!visited.containsKey(neighbor)) {
                    // clone the neighbor and put in the hashmap
                    visited.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
                    queue.offer(neighbor);
                }

                // add the clone of the neighbor to the neighbors of the clone node "n"
                visited.get(n).neighbors.add(visited.get(neighbor));
            }
        }

        return visited.get(node);
    }
}
