package com.jetbrains.classic.searchByStructure.treeSearch.bst;

import com.jetbrains.innerStructure.TreeNode;

public class InsertBST {
    /*
        The problem solution is very simple - one could always insert new node as a child of the leaf. To define which leaf to use, one could follow the standard BST logic :

            If val > node.val - go to insert into the right subtree.

            If val < node.val - go to insert into the left subtree.

        time: O(H), where H is the tree height.   O(logN) in the average case, and O(N) in the worst case
        space: O(H), to keep the recursion stack.   O(logN) in the average case, and O(N) in the worst case
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);

        // insert into the right subtree
        if (val > root.val) root.right = insertIntoBST(root.right, val);
            // insert into the left subtree
        else root.left = insertIntoBST(root.left, val);

        return root;
    }
}
