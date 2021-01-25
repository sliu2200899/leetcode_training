package com.jetbrains.classic.sorting;

import com.jetbrains.innerStructure.TreeNode;

import java.util.*;

public class SortInCollection {

    /*
        lc:  987 vertical order traversal of a binary tree
        The vertical order traversal of a binary tree is a list of non-empty reports for each unique x-coordinate from left to right.
        Each report is a list of all nodes at a given x-coordinate. The report should be primarily sorted by y-coordinate from highest y-coordinate to lowest.
        If any two nodes have the same y-coordinate in the report, the node with the smaller value should appear earlier.

        time: O(nlogn),
              in the first step, we traverse the input tree with either BFS or DFS, which would take O(N) time.
              in the second step, we sort the obtained list of coordinates which contains N elements, which would take O(nlogn) tim.
              in the third step, we extract the results from the sorted list, which would take O(n) time.
              To summarize, the overall time complexity of the algorithm would be O(nlogn) time.
        space: O(n)
              in the first step, we build a list that contains the coordinates of all the nodes. Hence, we need O(N) space for this list.
              in the second step, we used a queue data structure to maintain the order of visits. At any given moment, the queue contains no more than two levels of nodes in the tree.
                The maximal number of nodes at one level is O(n/2) which is the number of the leaf nodes in a balanced binary tree.
                As a result, the space needed for the queue would be O(n).
              in the third step, Although we don't need the queue data structure for the DFS approach, the recursion in the DFS approach incurs some additional memory consumption
                on the function call stack. In the worst case, the input tree might be completely imbalanced, e.g. each node has only the left child node. In this case, the recursion would occur up to NN times,
              the overall space complexity of the algorithm would be O(n).
     */

    private class Triplet<F, S, T> {
        public final F first;
        public final S second;
        public final T third;

        public Triplet(F first, S second, T third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }
    }

    List<Triplet<Integer, Integer, Integer>> nodeList = new ArrayList<>();

    private void BFS(TreeNode root) {
        Queue<Triplet<TreeNode, Integer, Integer>> queue = new ArrayDeque<>();
        int row = 0, column = 0;
        queue.offer(new Triplet(root, row, column));

        while (!queue.isEmpty()) {
            Triplet<TreeNode, Integer, Integer> triplet = queue.poll();
            root = triplet.first;
            row = triplet.second;
            column = triplet.third;

            if (root != null) {
                this.nodeList.add(new Triplet(column, row, root.val));
                queue.offer(new Triplet(root.left, row+1, column-1));
                queue.offer(new Triplet(root.right, row+1, column+1));
            }
        }
    }


    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        // step 1: bfs traversal
        BFS(root);

        // step 2: sort the global list by <column, row, value>
        Collections.sort(this.nodeList, new Comparator<Triplet<Integer, Integer, Integer>>(){
            @Override
            public int compare(Triplet<Integer, Integer, Integer> t1, Triplet<Integer, Integer, Integer> t2) {
                if (t1.first.equals(t2.first)) {
                    if (t1.second.equals(t2.second)) {
                        return t1.third - t2.third;
                    } else {
                        return t1.second - t2.second;
                    }
                } else {
                    return t1.first - t2.first;
                }
            }
        });

        // step 3: extract the values, partitioned by the column index
        List<Integer> currColumn = new ArrayList<>();
        Integer currColumnIndex = this.nodeList.get(0).first;

        for (Triplet<Integer, Integer, Integer> triplet: this.nodeList) {
            int column = triplet.first, value = triplet.third;

            if (column == currColumnIndex) {
                currColumn.add(value);
            } else {
                res.add(currColumn);
                currColumnIndex = column;
                currColumn = new ArrayList<>();
                currColumn.add(value);
            }
        }

        res.add(currColumn);

        return res;
    }
}
