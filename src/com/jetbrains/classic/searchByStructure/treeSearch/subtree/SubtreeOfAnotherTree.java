package com.jetbrains.classic.searchByStructure.treeSearch.subtree;

import com.jetbrains.innerStructure.TreeNode;

public class SubtreeOfAnotherTree {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) return t == null;

        return match(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean match(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;

        if (s.val != t.val) {
            return false;
        }

        return match(s.left, t.left) && match(s.right, t.right);
    }
}
