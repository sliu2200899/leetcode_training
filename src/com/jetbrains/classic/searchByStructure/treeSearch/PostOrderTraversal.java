package com.jetbrains.classic.searchByStructure.treeSearch;

import com.jetbrains.innerStructure.TreeNode;

import java.util.*;

public class PostOrderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root, prev = null;
        stack.push(root);

        while (!stack.isEmpty()) {
            cur = stack.peek();

            if (prev == null || prev.left == cur || prev.right == cur) {
                // cur is under the prev pointer, need to explore all the nodes
                // below the curr
                if (cur.left != null) {
                    stack.push(cur.left);
                } else if (cur.right != null) {
                    stack.push(cur.right);
                }
            }
            else if (cur.left == prev) {
                // explore all the nodes in the right subtree
                if (cur.right != null) stack.push(cur.right);
            }
            else {
                res.add(cur.val);
                stack.pop();
            }
            prev = cur;
        }
        return res;
    }


    /*
        preferred way, just put the cur node val to teh
     */

    public List<Integer> postorderTraversal2(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        if (root == null) return list;

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.addFirst(node.val);    // most important, the difference between preorder and post order

            if (node.left != null) {
                stack.push(node.left);
            }

            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return list;
    }
}
