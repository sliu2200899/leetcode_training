package com.jetbrains.classic.searchByStructure.treeSearch.bst;

import com.jetbrains.innerStructure.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class ClosestBST {
    // 272. Closest Binary Search Tree Value II
    private class Cell{
        double diff;
        int val;
        public Cell(double diff, int val) {
            this.diff = diff;
            this.val = val;
        }
    }
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;

        PriorityQueue<Cell> pqmax = new PriorityQueue<Cell>((a, b) -> {
            return Double.compare(b.diff, a.diff);
        });

        preorderTraversal(pqmax, root, target, k);

        while (!pqmax.isEmpty()) {
            list.add(pqmax.poll().val);
        }

        return list;
    }

    private void preorderTraversal(PriorityQueue<Cell> pqmax, TreeNode root, double target, int k) {
        if (root == null) {
            return;
        }

        pqmax.offer(new Cell(Math.abs(root.val - target), root.val));
        if (pqmax.size() > k) {
            pqmax.poll();
        }

        preorderTraversal(pqmax, root.left, target, k);
        preorderTraversal(pqmax, root.right, target, k);

    }
}
