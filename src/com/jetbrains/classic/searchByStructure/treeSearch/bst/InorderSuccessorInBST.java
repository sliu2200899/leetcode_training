package com.jetbrains.classic.searchByStructure.treeSearch.bst;

import com.jetbrains.innerStructure.Node;
import com.jetbrains.innerStructure.TreeNode;

public class InorderSuccessorInBST {

    /*
        my solution... good solution preferrable solution
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) return null;

        // find the target node based on the characteristics of BST
        TreeNode successor = null;
        while (root != null && root.val != p.val) {
            if (root.val > p.val) {
                successor = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }

        // if p does not exist in the tree
        if (root == null) return null;

        // if p does not have right subtree
        if (root.right == null) return successor;

        // if p has right subtree, then explore the left most node in the right subtree
        TreeNode node = root.right;
        while (node.left != null) {
            node = node.left;
        }

        return node;
    }
    /*
        solution 1: utilize the propery of BST   preferred way...
        We start our traversal with the root node and continue the traversal until our current node reaches a null value i.e. there are no more nodes left to process.

        At each step we compare the value of node p with that of node
            1. If p.val >= node.val that implies we can safely discard the left subtree since all the nodes there including the current node have values less than p
            2. However, if p.val < node.val, that implies that the successor must lie in the left subtree and that the current node is a potential candidate for inorder successor.
                Thus, we update our local variable for keeping track of the successor, successor, to node.
            3. return successor
     */
    public TreeNode inorderSuccessor1(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        while (root != null) {
            if (p.val >= root.val) {
                root = root.right;
            } else {
                successor = root;
                root = root.left;
            }
        }
        return successor;
    }
    /*
        solution 2: didn't use property of BST
     */
    private TreeNode previous;
    private TreeNode inorderSuccessorNode;

    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        // case 1: we simply need to find the leftmost node in the subtree rooted at p.right
        if (p.right != null) {
            TreeNode leftmost = p.right;
            while (leftmost.left != null) {
                leftmost = leftmost.left;
            }
            this.inorderSuccessorNode = leftmost;
        } else {
            // case 2: we need to perform the standard inorder traversal and keep track of the previous node

            this.inorderCase2(root, p);
        }

        return this.inorderSuccessorNode;
    }

    private void inorderCase2(TreeNode node, TreeNode p) {
        if (node == null) {
            return;
        }

        // recurse on the left side
        this.inorderCase2(node.left, p);

        // check if previous is the inorder predecessor of node
        if (this.previous == p && this.inorderSuccessorNode == null) {
            this.inorderSuccessorNode = node;
            return;
        }

        this.previous = node;

        // recurse on the right side
        this.inorderCase2(node.right, p);
    }

    /*
        follow up:
        Given a node in a binary search tree, return the in-order successor of that node in the BST. If that node has no in-order successor, return null.

        The successor of a node is the node with the smallest key greater than node.val.

        You will have direct access to the node but not to the root of the tree. Each node will have a reference to its parent node. Below is the definition for Node:


        Follow up: Could you solve it without looking up any of the node's values?

        time: O(H) where H is hte height of the bianry tree
        space: O(1)
     */

    private class Node{
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }
    public Node inorderSuccessor(Node node) {
        if (node == null) {
            return null;
        }

        if (node.right != null) {
            Node n = node.right;
            while (n.left != null) {
                n = n.left;
            }
            return n;

        }

        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }

        return node.parent;
    }
}
