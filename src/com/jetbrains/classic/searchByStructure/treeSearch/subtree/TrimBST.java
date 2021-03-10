package com.jetbrains.classic.searchByStructure.treeSearch.subtree;

import com.jetbrains.innerStructure.TreeNode;

public class TrimBST {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        /*
            if node.val > high.    safely remove the root node and right child
            if node.val < low.     safely remove the root node and left child
            otherwise node.val > low and node.val < high,
                                   in this case, we need to trim the left and right subtree respectively, which is like divide process,
                                   and update root's left and right child pointer afterward, which is like the conqure process.
        */

        // base case
        if (root == null) {
            return null;
        }
        if (root.val > high) {
            return trimBST(root.left, low, high);
        }
        if (root.val < low) {
            return trimBST(root.right, low, high);
        }

        // recursive part
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);

        return root;
    }
}
