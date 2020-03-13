package com.jetbrains.classic.search;

import com.jetbrains.innerStructure.TreeNode;

import java.util.*;

public class VerticalOrderTraversal {
    private class Cell {
        int offset;
        TreeNode node;

        public Cell(int offset, TreeNode node) {
            this.offset = offset;
            this.node = node;
        }
    }

    // 1. first, we need to think that we need a new data structure to store node and its location.
    // 2. second, we need to think that how to traverse the binary tree, dfs or bfs, apprently, bfs is preferred. We need a queue to traverse.
    // 3. to output the result more efficiently, we actually need a hashmap to store the location and list of values
    //    also, during the traversal, we need to record the range of the location(min and max)
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Deque<Cell> queue = new LinkedList<>();
        queue.offer(new Cell(0, root));
        Map<Integer, List<Integer>> map = new HashMap<>();

        int min = 0, max = 0;  // important, we need to know the range of the map key
        while (!queue.isEmpty()) {
            Cell c = queue.poll();
            map.putIfAbsent(c.offset, new ArrayList<>());
            map.get(c.offset).add(c.node.val);

            min = Math.min(min, c.offset);
            max = Math.max(max, c.offset);

            if (c.node.left != null) queue.offer(new Cell(c.offset - 1, c.node.left));
            if (c.node.right != null) queue.offer(new Cell(c.offset + 1, c.node.right));
        }

        for (int i = min; i <= max; ++i) {
            res.add(new ArrayList<>(map.get(i)));
        }

        return res;
    }
}
