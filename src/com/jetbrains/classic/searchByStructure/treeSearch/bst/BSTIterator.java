package com.jetbrains.classic.searchByStructure.treeSearch.bst;

import com.jetbrains.innerStructure.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class BSTIterator {
    /*

        time:
            hasNext: O(1)
            next: amortized O(1)

        space:
            O(h)  h is the height of the tree... the size of the stack.
     */
    Deque<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        // stack for the recursion simulation
        this.stack = new ArrayDeque<>();
        // algo starts with a call to the helper function witht the root node as the input
        this._leftmostInorder(root);
    }

    private void _leftmostInorder(TreeNode root) {
        // for a given node, add all the elements in the leftmost branch of the tree under it to the stack
        while (root != null) {
            this.stack.push(root);
            root = root.left;
        }
    }

    public int next() {
        TreeNode topmostNode = this.stack.pop();
        if (topmostNode.right != null) {
            this._leftmostInorder(topmostNode.right);
        }

        return topmostNode.val;
    }

    public boolean hasNext() {
        return this.stack.size() > 0;
    }
}
