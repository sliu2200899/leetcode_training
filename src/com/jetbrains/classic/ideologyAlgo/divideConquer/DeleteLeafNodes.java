package com.jetbrains.classic.ideologyAlgo.divideConquer;

import com.jetbrains.innerStructure.TreeNode;

public class DeleteLeafNodes {
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        // base case
        if (root == null) return null;

        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);

        if (root.left == null && root.right == null && root.val == target) {
            return null;
        }

        return root;
    }
}
