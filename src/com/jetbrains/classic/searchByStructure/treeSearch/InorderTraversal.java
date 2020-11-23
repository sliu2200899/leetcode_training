package com.jetbrains.classic.searchByStructure.treeSearch;

import com.jetbrains.innerStructure.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class InorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;

        while(!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            TreeNode node = stack.pop();
            res.add(node.val);

            cur = node.right;
        }

        return res;
    }
}
