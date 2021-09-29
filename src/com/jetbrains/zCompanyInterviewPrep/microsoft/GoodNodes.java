package com.jetbrains.zCompanyInterviewPrep.microsoft;

import com.jetbrains.innerStructure.TreeNode;

public class GoodNodes {
    public int goodNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + helper(root.left, root.val) + helper(root.right, root.val);
    }

    private int helper(TreeNode node, int value) {
        if (node == null) {
            return 0;
        }

        int left = helper(node.left, Math.max(value, node.val));
        int right = helper(node.right, Math.max(value, node.val));

        return (node.val >= value ? 1 : 0) + left + right;
    }
}
