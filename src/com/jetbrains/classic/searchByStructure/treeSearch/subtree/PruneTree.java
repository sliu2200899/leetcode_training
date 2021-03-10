package com.jetbrains.classic.searchByStructure.treeSearch.subtree;

import com.jetbrains.innerStructure.TreeNode;

public class PruneTree {
    /*
        approach 1:

        At every node, check the return value from left and right. If they say cut, cut them. If any of them say do not cut or curr node has 1, then return false to the caller to inform do not cut.

        time: O(N)  N is the number of nodes in the tree. We process each node once.
        space: O(h)   h is the height of the tree. This represents the size of the call stack in our recursion
     */
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) return null;

        return dfs(root) ? root: null;
    }

    private boolean dfs(TreeNode root) {

        // base case
        if (root == null) return false;

        // recursive case
        boolean left = dfs(root.left);  // whether the node satisfy the property...
        boolean right = dfs(root.right);

        if (!left) {
            root.left = null;
        }

        if (!right) {
            root.right = null;
        }

        return root.val == 1 || left || right;
    }


    /*
        approach 2:

        Each node asks each child that do you have 1 in your subtree? They respond by returning null if they do not have it or by themselves if they have it.
        After you ask your children, now it is your turn to tell your story to your parents - you will return null if your children are null and you are 0 otherwise return yourself.
     */
    public TreeNode pruneTree2(TreeNode root) {
        if (root == null) return null;

        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);

        return root.val == 0 && root.left == null && root.right == null ? null : root;
    }
}
