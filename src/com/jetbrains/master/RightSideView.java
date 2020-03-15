package com.jetbrains.master;

import com.jetbrains.innerStructure.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class RightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode node = queue.poll();
                if (i == size - 1) res.add(node.val);

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }

        return res;
    }

    // use dfs recursive manner to solve the problem.
    public List<Integer> rightSideView2(TreeNode root) {
        //dfs
        List<Integer> res = new ArrayList<>();
        helper(root, res, 0);  // root -> height 0
        return res;
    }

    private void helper(TreeNode node, List<Integer> list, int height) {
        // base case
        if (node == null) return;

        // recursive case
        if (list.size() <= height) {
            list.add(node.val);
        } else {
            list.set(height, node.val);
        }
        helper(node.left, list, height + 1);
        helper(node.right, list, height + 1);
    }


    // use dfs recursive manner (another angle) to solve the problem
    public List<Integer> rightSideView3(TreeNode root) {
        //dfs
        List<Integer> res = new ArrayList<>();
        helper2(root, res, 0);  // root -> height 0
        return res;
    }

    private void helper2(TreeNode node, List<Integer> list, int height) {
        // base case
        if (node == null) return;

        // recursive case
        if (list.size() <= height) list.add(node.val);

        helper2(node.right, list, height + 1);
        helper2(node.left, list, height + 1);
    }
}
