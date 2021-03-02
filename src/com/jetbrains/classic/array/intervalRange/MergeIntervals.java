package com.jetbrains.classic.array.intervalRange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        /*
            analyze:
                intervals = [[1,3],[2,6],[8,10],[15,18]]
                [[1,6],[8,10],[15,18]]

                [1,3],[2,6],[8,10],[15,18]

                sort teh intervals based on the starting time of each interval
                for each interval, we need to comapre the starting time of cur interval with the end time of the previous one.
                if exist some overlapping, then we need to merge then
                how to merge?
                    start time is the start time of previous interval
                    end time is max(end_previous, end_cur)

                    end_previous = end_time
                    delete and set the cur interval
        */
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) return new int[0][0];

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < intervals.length; ++i) {
            if (res.isEmpty() || intervals[i][0] > res.get(res.size() - 1)[1]) {
                res.add(intervals[i]);
            } else {
                res.get(res.size() - 1)[1] = Math.max(intervals[i][1], res.get(res.size() - 1)[1]);
            }
        }

        return res.toArray(new int[res.size()][2]);
    }

    /*
        Question: How do you add intervals and merge them for a large stream of intervals? (Facebook Follow-up Question)

     */

    // it seems like we need to do it using interval tree... but
    private class TreeNode {
        public int start;
        public int end;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public int[][] merge2(int[][] intervals) {
        /*
            algo:

            TreeNode - On top of the left child, right child, start boundary, and end boundary, we have a middle field that determines whether a
            new interval goes to the left child, right right or merged with the current node.

            add - If the new interval touches or crosses the middle of the current node, we update the current node. Otherwise, we put the new
            interval into the left subtree or right subtree.

            Why do we use middle for comparison and not start or end boundaries?
            The reason is that we can use merge-sort technique to query the merged intervals result when the left subtree does not overlap with the right subtree.

            query - Use merge-sort technique by retrieving the merged intervals of the left subtree (i.e. left_intervals) and those of the
            right subtree (i.e. right_intervals). Because of the implementation of add, we can guarantee that

            if there's an interval in the left_intervals that overlaps with the current node, then we know that all the intervals after that
            interval overlaps with the current node. The first few intervals or zero intervals in the right_intervals overlap with the current
            node.

            Here's the visualization:

                left_res = [ (intervals that do not overlap), (intervals that overlap with current node) ]
                right_res = [ (intervals that overlap with current node), (intervals that do not overlap) ]

        */
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) return new int[0][0];

        TreeNode root = null;
        for (int[] interval : intervals) {
            if (root == null) {
                root = new TreeNode(interval[0], interval[1]);
            } else {
                add(root, interval[0], interval[1]);
            }
        }
        return query(root);
    }

    private void add(TreeNode node, int start, int end) {
        if (end < node.start) {
            if (node.left != null) {
                add(node.left, start, end);
            } else {
                node.left = new TreeNode(start, end);
            }
        } else if (start > node.end) {
            if (node.right != null) {
                add(node.right, start, end);
            } else {
                node.right = new TreeNode(start, end);
            }
        } else {
            node.start = Math.min(node.start, start);
            node.end = Math.max(node.end, end);
        }
    }

    private int[][] query(TreeNode node) {
        if (node == null) {
            return new int[0][0];
        }
        // divide and conquer
        int[][] left_intervals = query(node.left);
        int[][] right_intervals = query(node.right);

        boolean inserted = false;
        List<int[]> res = new ArrayList<>();
        for (int[] lres : left_intervals) {
            if (lres[1] < node.start) {
                res.add(lres);
            } else {
                res.add(new int[]{Math.min(lres[0], node.start), node.end});
                inserted = true;
                break;
            }
        }

        if (!inserted) {
            res.add(new int[]{node.start, node.end});
        }

        for (int[] rres : right_intervals) {
            if (rres[0] <= node.end) {
                res.get(res.size() - 1)[1] = Math.max(node.end, rres[1]);
            } else {
                res.add(rres);
            }
        }
        return res.toArray(new int[res.size() - 1][2]);

    }


    // use heap
    public int[][] merge3(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) return new int[0][0];

        PriorityQueue<int[]> pqmin = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        for (int[] interval : intervals) {
            pqmin.offer(interval);
        }

        List<int[]> res = new ArrayList<>();
        while(!pqmin.isEmpty()) {
            int[] cur = pqmin.poll();
            if (res.isEmpty() || cur[0] > res.get(res.size() - 1)[1]) {
                res.add(cur);
            } else {
                res.get(res.size() - 1)[1] = Math.max(cur[1], res.get(res.size() - 1)[1]);
            }
        }
        return res.toArray(new int[res.size()][2]);
    }
}
