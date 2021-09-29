package com.jetbrains.zCompanyInterviewPrep.linkedIn;

import com.jetbrains.innerStructure.TreeNode;

public class SecondMinNodeInBinaryTree {
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) {
            return -1;
        }

        if (root.left == null && root.right == null) {
            return -1;
        }

        int left = root.left.val;
        int right = root.right.val;

        // if value same as root value, need to find the next candidate...
        if (left == root.val) {
            left = findSecondMinimumValue(root.left);
        }

        if (right == root.val) {
            right = findSecondMinimumValue(root.right);
        }

        if (left != -1 && right != -1) {
            return Math.min(left, right);
        } else if (left != -1) {
            return left;
        } else {
            return right;
        }
    }
}
