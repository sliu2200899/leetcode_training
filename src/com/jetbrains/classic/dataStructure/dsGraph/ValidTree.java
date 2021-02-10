package com.jetbrains.classic.dataStructure.dsGraph;

import java.util.*;

public class ValidTree {
    /*
        basic graph theory

        Recall that a graph G is a tree iff the following two conditions are met:
        1. G is fully connected. In other words, for every pair of nodes in G, there is a path between them.
        2. G contains no cycles. In other words, there is exactly one path between each pair of nodes in G.

        algo
        Depth-first search requires being able to look up the adjacent (immediate neighbours) of a given node. Like many graph
        interview problems though, the input format we're given doesn't allow us to quickly get the neighbours of a node.
        Therefore, our first step is to convert the input into an adjacency list. Recall that an adjacency list is where we have
        a list of sub-lists, where each sub-list is the list of the immediate neighbours for the i'th node.
     */


// note that if we address the directed graph cycle detection. just     if (seen.contains(neighbor)) return false;

    /*
        time: O(N + E)
            Creating the adjacency list requires initialising a list of length N, with a cost of O(N), and then iterating over and inserting E edges,
            for a cost of O(E)O(E). This gives us O(E)+O(N)=O(N+E).

            Each node is added to the data structure once. This means that the outer loop will run NN times. For each of the NN nodes,
            its adjacent edges is iterated over once. In total, this means that all EE edges are iterated over once by the inner loop.
            This, therefore, gives a total time complexity of O(N + E)O(N+E).

            Because both parts are the same, we get a final time complexity of O(N+E).

        space: O(N + E)
            The adjacency list is a list of length NN, with inner lists with lengths that add to a total of EE. This
            gives a total of O(N+E) space.

            In the worst case, the stack/ queue will have all NN nodes on it at the same time, giving a total of O(N)O(N) space.

            In total, this gives us O(E+N) space.
     */


/*
    iterative dfs with first strategy to address the undirected graph cycle detection.
 */
    public boolean validTree(int n, int[][] edges) {
        List<List<Integer>> adjacencyList = new ArrayList<>();
        // initialize an empty list for each node
        for (int i = 0; i < n; ++i) {
            adjacencyList.add(new ArrayList<>());
        }

        // go through the edge list, populating the adjacency list
        for (int[] edge: edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }

        // iterative dfs
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);

        Set<Integer> seen = new HashSet<>();
        seen.add(0);

        while (!stack.isEmpty()) {
            int node = stack.pop();  // take one off to visit
            // check for unseen neighbors of this node
            for (int neighbor : adjacencyList.get(node)) {
                if (seen.contains(neighbor)) {
                    return false;
                }
                // otherwise, put the neighbor onto stack
                // and record that it has been seen
                stack.push(neighbor);
                seen.add(neighbor);

                adjacencyList.get(neighbor).remove(Integer.valueOf(node));   // first strategy to address the undirected graph cycle detection.
            }
        }

        return seen.size() == n;
    }
/*
  iterative dfs with second strategy to address the undirected graph cycle detection.
 */
    public boolean validTree2(int n, int[][] edges) {
        List<List<Integer>> adjacencyList = new ArrayList<>();
        // initialize an empty list for each node
        for (int i = 0; i < n; ++i) {
            adjacencyList.add(new ArrayList<>());
        }

        // go through the edge list, populating the adjacency list
        for (int[] edge: edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }

        // iterative dfs
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);

        Map<Integer, Integer> parent = new HashMap<>();
        parent.put(0, -1);

        while (!stack.isEmpty()) {
            int node = stack.pop();  // take one off to visit
            // check for unseen neighbors of this node
            for (int neighbor : adjacencyList.get(node)) {
                // don't look at the trivial cycle
                if (parent.get(node) == neighbor) continue;

                // check if we've already seen this node
                if (parent.containsKey(neighbor)) return false;

                stack.push(neighbor);
                parent.put(neighbor, node);
            }
        }

        return parent.size() == n;
    }

/*
    iterative bfs, almost the same just use different data structure
 */
    public boolean validTree3(int n, int[][] edges) {
        List<List<Integer>> adjacencyList = new ArrayList<>();
        // initialize an empty list for each node
        for (int i = 0; i < n; ++i) {
            adjacencyList.add(new ArrayList<>());
        }

        // go through the edge list, populating the adjacency list
        for (int[] edge: edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }

        // iterative dfs
        Deque<Integer> queue = new LinkedList<>();
        queue.offer(0);

        Map<Integer, Integer> parent = new HashMap<>();
        parent.put(0, -1);

        while (!queue.isEmpty()) {
            int node = queue.poll();  // take one off to visit
            // check for unseen neighbors of this node
            for (int neighbor : adjacencyList.get(node)) {
                // don't look at the trivial cycle
                if (parent.get(node) == neighbor) continue;

                // check if we've already seen this node
                if (parent.containsKey(neighbor)) return false;

                queue.offer(neighbor);
                parent.put(neighbor, node);
            }
        }

        return parent.size() == n;
    }


    /*******
     * advanced graph theory
     * here's a better definition for determining whether or not a given graph is a tree.
     */

/*
For the graph to be a valid tree, it must have exactly n - 1 edges. Any less, and it can't possibly be fully connected.
Any more, and it has to contain cycles. Additionally, if the graph is fully connected and contains exactly n - 1 edges,
it can't possibly contain a cycle, and therefore must be a tree!

These facts are fairly straightforward to prove. We won't go into why they are true here, but if you're not familiar
with these facts, then we recommend reading up on graph theory. It is very important to be confident with graph theory
in-order to pass the interviews at a top tech company.

Going by this definition, our algorithm needs to do the following:
    1. Check whether or not there are n - 1 edges. If there's not, then return false.
    2. Check whether or not the graph is fully connected. Return true if it is, false if otherwise.

 */

    /*
        time complexity:  When E ≠ N - 1, we simply return false. Therefore, the worst case is when E=N−1.
                          Because E is proportional to NN, we'll say E=N to simplify the analysis.

                          As said above, creating an adjacency list has a time complexity of O(N+E). Because E is now
                          bounded by NN, we can reduce this slightly to O(N+N)=O(N).
        space complexity: O(N)
                          Previously, we determined that the adjacency list took O(E+N) space. We now know this is simply O(N).
                          In the worst case, the search algorithms will require an additional O(N) space; this is if all nodes
                          were on the stack/queue at the same time.

     */
    public boolean validTree4(int n, int[][] edges) {

        if (edges.length != n - 1) return false;

        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] edge: edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }

        // iterative dfs
        Deque<Integer> stack = new ArrayDeque<>();
        Set<Integer> seen = new HashSet();

        stack.push(0);
        seen.add(0);

        while (!stack.isEmpty()) {
            int node = stack.pop();
            for (int neighbor : adjacencyList.get(node)) {
                if (seen.contains(neighbor)) continue;

                seen.add(neighbor);
                stack.push(neighbor);
            }
        }

        return seen.size() == n;
    }

    /*
    Another way we could approach the problem is by considering each connected component to be a set of nodes.
    When an edge is put between two separate connected components, they are merged into a single connected component.


     */
    private class UnionFind{
        int[] father;
        int[] size;  // use rank to keep track of the size of each set.

        public UnionFind(int n) {
            father = new int[n];
            size = new int[n];
            for (int i = 0; i < n; ++i) {
                father[i] = i;
                size[i] = 1;
            }
        }

        public boolean connect(int a, int b) {
            int root_a = find(a);
            int root_b = find(b);

            if (root_a == root_b) return false;

            if (root_a != root_b) {
                if (size[root_a] > size[root_b]) {
                    father[root_b] = root_a;
                    size[root_a] += size[root_b];
                } else {
                    father[root_a] = root_b;
                    size[root_b] += size[root_a];
                }
            }

            return true;
        }

        public int find(int a) {
            if (father[a] == a) {
                return a;
            }

            return father[a] = find(father[a]); // path compression
        }
    }
    public boolean validTree5(int n, int[][] edges) {

        // condition 1: the graph must contain n - 1 edges
        if (edges.length != n - 1) return false;

        UnionFind uf = new UnionFind(n);

        for (int[] edge : edges) {
            int A = edge[0];
            int B = edge[1];
            if (!uf.connect(A, B)) {
                return false;
            }
        }

        return true;
    }
}
