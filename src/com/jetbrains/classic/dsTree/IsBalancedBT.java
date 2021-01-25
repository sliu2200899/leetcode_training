package com.jetbrains.classic.dsTree;

import com.jetbrains.innerStructure.TreeNode;

public class IsBalancedBT {

    // recursive way to solve the problem.
    // in this problem we need to return to its parents {height, isBalanced}
    // we can use height = -1 to determine if this is balanced or not.
    public boolean isBalanced(TreeNode root) {
        return helper(root) != -1;
    }

    private int helper(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = helper(node.left);
        int right = helper(node.right);

        if (left == -1 || right == -1 || Math.abs(left - right) > 1) return -1;

        return Math.max(left, right) + 1;
    }
}
