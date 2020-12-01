package com.jetbrains.classic;

import com.jetbrains.innerStructure.TreeNode;

public class BuildTreePreInorder {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null) return null;

        return helper(preorder, 0, inorder, 0, preorder.length);
    }

    private TreeNode helper(int[] pre, int preStart, int[] in, int inStart, int len) {
        // base case
        if (len <= 0) return null;
        if (len == 1) return new TreeNode(pre[preStart]);

        // recursive case
        int rootVal = pre[preStart];

        // find the index in the array in
        int i = 0;
        while (i < len && in[i + inStart] != rootVal) {
            i++;
        }

        int leftLen = i;
        int rightLen = len - i - 1;

        TreeNode root = new TreeNode(rootVal);
        root.left = helper(pre, preStart + 1, in, inStart, leftLen);
        root.right = helper(pre, preStart + 1 + leftLen, in, inStart + 1 + leftLen, rightLen);

        return root;
    }
}
