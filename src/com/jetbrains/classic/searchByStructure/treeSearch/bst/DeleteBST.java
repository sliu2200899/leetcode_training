package com.jetbrains.classic.searchByStructure.treeSearch.bst;

import com.jetbrains.innerStructure.TreeNode;

public class DeleteBST {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (key > root.val) {
            // delete from the right subtree
            root.right = deleteNode(root.right, key);
        } else if (key < root.val) {
            // delete from the left subtree
            root.left = deleteNode(root.left, key);
        } else {
            // delete hte current node
            if (root.left == null && root.right == null) {
                root = null;
            } else if (root.right != null) {
                root.val = successor(root);
                root.right = deleteNode(root.right, root.val);
            } else {
                root.val = predecessor(root);
                root.left = deleteNode(root.left, root.val);
            }
        }
        return root;
    }
    /*
    Successor = "after node", i.e. the next node, or the smallest node after the current one.
    It's also the next node in the inorder traversal. To find a successor, go to the right once and then as many times to the left as you could.
     */
    private int successor(TreeNode root) {
        root = root.right;
        while (root.left != null) root = root.left;
        return root.val;
    }
    /*
    Predecessor = "before node", i.e. the previous node, or the largest node before the current one.
    It's also the previous node in the inorder traversal. To find a predecessor, go to the left once and then as many times to the right as you could.
     */
    private int predecessor(TreeNode root) {
        root = root.left;
        while (root.right != null) root = root.right;
        return root.val;
    }
}
