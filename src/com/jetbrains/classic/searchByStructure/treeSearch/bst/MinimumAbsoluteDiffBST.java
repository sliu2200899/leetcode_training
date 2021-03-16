package com.jetbrains.classic.searchByStructure.treeSearch.bst;

import com.jetbrains.innerStructure.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinimumAbsoluteDiffBST {
    public int getMinimumDifference(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;

        int min = Integer.MAX_VALUE, prev = -1;
        while(!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            TreeNode node = stack.pop();
            if (prev == -1) {
                prev = node.val;
            } else {
                min = Math.min(min, node.val - prev);
                prev = node.val;
            }

            cur = node.right;
        }

        return min;
    }
}
