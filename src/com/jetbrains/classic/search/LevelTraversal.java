package com.jetbrains.classic.search;

import com.jetbrains.innerStructure.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class LevelTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; ++i) {
                TreeNode node = queue.poll();
                list.add(node.val);

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            res.add(list);
        }

        return res;
    }

    // recursive level order
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, res, 0);
        return res;
    }

    private void dfs(TreeNode root, List<List<Integer>> list, int height) {
        if (root == null) {
            return;
        }
        if (list.size() <= height) {
            list.add(new ArrayList<Integer>());
        }
        list.get(height).add(root.val);
        dfs(root.left, list, height + 1);
        dfs(root.right, list, height + 1);

        return;
    }
}
