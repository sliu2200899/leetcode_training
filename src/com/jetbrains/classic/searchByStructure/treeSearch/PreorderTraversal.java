package com.jetbrains.classic.searchByStructure.treeSearch;

import com.jetbrains.innerStructure.TreeNode;
import com.jetbrains.innerStructure.TreeNodeParent;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val);

            if (node.right != null) {
                stack.push(node.right);
            }

            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return res;
    }

    // preorder with parent pointer without stack
    public List<Integer> preorderTraversal2(TreeNodeParent root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        TreeNodeParent cur = root;
        while (cur != null) {
            res.add(cur.val);

            if (cur.left != null) {
                cur = cur.left;
            } else if (cur.right != null) {
                cur = cur.right;
            } else {
                // cur.left = cur.right = null
                while (cur.parent != null && (cur.parent.right == null || cur == cur.parent.right)) {
                    cur = cur.parent;
                }
                if (cur.parent == null) break;
                cur = cur.parent.right;
            }
        }
        return res;
    }
}
