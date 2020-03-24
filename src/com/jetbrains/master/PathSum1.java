package com.jetbrains.master;

import com.jetbrains.innerStructure.TreeNode;

public class PathSum1 {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        return dfs(root, 0, sum);
    }

    private boolean dfs(TreeNode root, int total, int target) {
        if (root.left == null && root.right == null) {
            if (total + root.val == target) {
                return true;
            }
        }

        boolean flag = false;
        if (root.left != null) {
            flag = flag || dfs(root.left, total + root.val, target);
        }
        if (root.right != null) {
            flag = flag || dfs(root.right, total + root.val, target);
        }

        return flag;
    }

    // Or we can use another method to solve the problem
    public boolean hasPathSum1a(TreeNode root, int sum) {
        if (root == null) return false;

        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                return true;
            }
        }

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}
