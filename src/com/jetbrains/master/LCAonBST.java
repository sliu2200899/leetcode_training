package com.jetbrains.master;

import com.jetbrains.innerStructure.TreeNode;

public class LCAonBST {

    // the basic idea is that walking from the root node, and
    // choose left or right subtree based on relationship between node value and p, q's value.
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // sanity check
        if (root == null || p == null || q == null) return null;

        while (root != null) {
            if (root.val > p.val && root.val > q.val) root = root.left;
            else if (root.val < p.val && root.val < q.val) root = root.right;
            else break;
        }

        return root;
    }
}
