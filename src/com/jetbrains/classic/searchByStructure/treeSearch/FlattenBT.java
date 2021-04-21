package com.jetbrains.classic.searchByStructure.treeSearch;

import com.jetbrains.innerStructure.TreeNode;

public class FlattenBT {
    /*
        recursion

        The main idea behind a recursive solution is that we use the solutions for subproblems to solve an uber level problem.
        In the case of a tree, the subtrees are essentially our subproblems. So, a recursive solution for this problem is essentially
        based on the idea that assuming we have already transformed the left and the right halves of a given root node, how do we
        establish or modify the necessary connections so that we get a right skewed tree overall. Let's look

        algo:
        1.We'll have a separate function for flattening out the tree since the main function provided in the problem isn't supposed to return anything and our algorithm will return the tail node of the flattened out tree.

        2.For a given node, we will recursively flatten out the left and the right subtrees and store their corresponding tail nodes in leftTail and rightTail respectively.

        3.Next, we will make the following connections (only if there is a left child for the current node, else the leftTail would be null)
            leftTail.right = node.right
            node.right = node.left
            node.left = None

        Next we have to return the tail of the final, flattened out tree rooted at node. So, if the node has a right child, then we will return the rightTail, else, we'll return the leftTail.
     */
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        helper(root);
    }

    private TreeNode helper(TreeNode root) {
        if (root == null) {
            return null;
        }
        // for a leaf node, we simply return teh node as is
        if (root.left == null && root.right == null) {
            return root;
        }

        // recursively flatten the left and right subtree
        TreeNode leftRoot = helper(root.left);
        TreeNode rightRoot = helper(root.right);

        // if there is no left subtree, we only need to return the root node, since we have shuffle the connection in the right subtree
        if (leftRoot == null) return root;

        if (rightRoot == null) {
            root.left = null;
            root.right = leftRoot;
        }

        // if there was a left subtree, we shuffle the connections around
        // so that there is nothing on hte left side anymore
        // the first step is to find the right most node in the left subtree
        TreeNode rightMost = leftRoot;
        while (rightMost.right != null) {
            rightMost = rightMost.right;
        }

        rightMost.right = rightRoot;
        root.left = null;
        root.right = leftRoot;

        return root;
    }
}