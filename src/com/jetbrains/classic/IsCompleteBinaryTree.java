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
    private int countNodes(TreeNode root) {

        if (root == null) {
            return 0;
        }
        return (1 + countNodes(root.left) + countNodes(root.right));
    }

    private boolean isComplete(TreeNode root, int index, int num) {
        // an empty tree is complete
        if (root == null) {
            return true;
        }
        // tree is not complete if index assigned to current node is more than number of nodes in tree.
        if (index >= num) {
            return false;
        }

        // recur for left and right subtrees
        return (isComplete(root.left, 2 * index + 1, num)
                && (isComplete(root.right, 2 * index + 2, num));
    }

    private boolean isComplete2(TreeNode root) {
        return isComplete(root, 0, countNodes(root));
    }

}
