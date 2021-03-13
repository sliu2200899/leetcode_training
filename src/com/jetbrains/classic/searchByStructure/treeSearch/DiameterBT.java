package com.jetbrains.classic.searchByStructure.treeSearch;

import com.jetbrains.innerStructure.TreeNode;

public class DiameterBT {
    // use recursion to solve teh problme
    // what does the recursion function return?
    //      longest path from currunt node to the leaf node.
    // from the perspective of the parent node, what does it do?
    //      max(max, left + right)    // longest path may not pass through the root node
    //      return max(left, right) + 1;
    int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        dfs(root);
        return max;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = dfs(root.left);
        int right = dfs(root.right);

        max = Math.max(max, left + right);

        return 1 + Math.max(left, right);
    }
}
