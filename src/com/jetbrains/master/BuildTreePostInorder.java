package com.jetbrains.master;

import com.jetbrains.innerStructure.TreeNode;

public class BuildTreePostInorder {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null ||
                inorder.length == 0 || postorder.length == 0) return null;

        return build(inorder, 0, postorder, postorder.length - 1, postorder.length);
    }

    private TreeNode build(int[] in, int s1, int[] post, int s2, int len) {
        if (len <= 0) return null;
        if (len == 1) return new TreeNode(post[s2]);

        int rootVal = post[s2];

        int pos = s1;
        while (pos < in.length && in[pos] != rootVal) {
            pos++;
        }

        int leftLen = pos - s1;
        int rightLen = len - 1 - leftLen;

        TreeNode root = new TreeNode(rootVal);
        root.left = build(in, s1, post, s2 - 1 - rightLen, leftLen);
        root.right = build(in, pos + 1, post, s2 - 1, rightLen);

        return root;
    }
}
