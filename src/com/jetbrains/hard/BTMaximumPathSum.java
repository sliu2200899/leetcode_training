package com.jetbrains.hard;

import com.jetbrains.innerStructure.TreeNode;

public class BTMaximumPathSum {
    private int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;

        dfs(root);
        return max;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;

        int left = Math.max(0, dfs(root.left));
        int right = Math.max(0, dfs(root.right));

        max = Math.max(max, root.val + left + right);

        return root.val + Math.max(left, right);
    }
}
