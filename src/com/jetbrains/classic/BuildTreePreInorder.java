package com.jetbrains.classic;

import com.jetbrains.innerStructure.TreeNode;

public class BuildTreePreInorder {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null ||
                preorder.length == 0 || inorder.length == 0) {
            return null;
        }

        return build(preorder, 0, inorder, 0, preorder.length);
    }

    // s1: start index of preorder array
    // s2: start index of inorder array
    // len: the length of the current preorder subarray
    private TreeNode build(int[] pre, int s1, int[] in, int s2, int len) {
        // base case
        if (len <= 0) return null;
        if (len == 1) return new TreeNode(pre[s1]);

        // recursive case
        int rootVal = pre[s1]; // root value
        int pos = s2;   // find the position of the root value in the inorder array
        while (pos < in.length && in[pos] != rootVal) {
            pos++;
        }
        //we can split both inorder and preorder arrays into three parts.
        int leftLen = pos - s2;   // the length of partA subarray
        int rightLen = len - 1 - leftLen; // the length of partB subarray

        TreeNode root = new TreeNode(rootVal);
        root.left = build(pre, s1 + 1, in, s2, leftLen);
        root.right = build(pre, s1 + leftLen + 1, in, pos + 1, rightLen);

        return root;
    }
}
