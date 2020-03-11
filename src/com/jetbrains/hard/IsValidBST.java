package com.jetbrains.hard;

import com.jetbrains.innerStructure.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class IsValidBST {
    public class Tuple{
        boolean isValid;
        TreeNode min;
        TreeNode max;
        public Tuple(boolean isValid, TreeNode min, TreeNode max) {
            this.isValid = isValid;
            this.min = min;
            this.max = max;
        }
    }
    // first recursive approach to record BST range, using a private class
    public boolean isValidBST(TreeNode root) {
        return helper(root).isValid;
    }

    private Tuple helper(TreeNode root) {
        if (root == null) {
            Tuple t = new Tuple(true, null, null);
            return t;
        }

        Tuple left = helper(root.left);
        Tuple right = helper(root.right);

        // how to return a false
        if (!left.isValid || !right.isValid ||
                (left.max != null && root.val <= left.max.val) ||
                (right.min != null && root.val >= right.min.val)) {
            return new Tuple(false, null, null);
        }

        TreeNode min = left.min == null ? root : left.min;
        TreeNode max = right.max == null ? root : right.max;

        return new Tuple(true, min, max);
    }


    // second recursive approach to record BST range, just putting the range in the parameter.
    public boolean isValidBST2(TreeNode root) {
        return helper(root, null, null);
    }

    private boolean helper(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;

        if ((min != null && root.val <= min) ||
                (max != null && root.val >= max)) {
            return false;
        }

        return helper(root.left, min, root.val) &&
                helper(root.right, root.val, max);
    }

    // Or we can use iterative method to sovle the problem
    public boolean isValidBST3(TreeNode root) {
        if (root == null) return true;

        // inorder traversal
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root, prev = null;

        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            TreeNode node = stack.pop();
            if (prev != null && prev.val >= node.val) {
                return false;
            }
            prev = node;
            cur = node.right;
        }

        return true;
    }
}
