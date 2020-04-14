package com.jetbrains.classic;

import com.jetbrains.innerStructure.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class IsCompleteBinaryTree {
    // iterative approach to solve the problem
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        }

        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        boolean flag = false;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node.left != null) {
                if (flag) return false;
                queue.offer(node.left);
            } else {
                flag = true; // if this is a complete binary tree, no subsequent node would be added into the queue.
            }

            if (node.right != null) {
                if (flag) return false;
                queue.offer(node.right);
            } else {
                flag = true;
            }
        }

        return true;
    }

    // use recursive approach to solve the problem
    public boolean isCompleteTree2(TreeNode root) {
        if (root == null) return true;

        return helper(root, 0, countNode(root));
    }

    private boolean helper(TreeNode node, int index, int num) {
        if (node == null) return true;
        if (index >= num) return false;

        return helper(node.left, index * 2 + 1, num) && helper(node.right, index *2 + 2, num);
    }

    private int countNode(TreeNode root) {
        if (root == null) return 0;

        return 1 + countNode(root.left) + countNode(root.right);
    }

}
