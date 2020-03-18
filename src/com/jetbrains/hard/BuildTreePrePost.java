package com.jetbrains.hard;

import com.jetbrains.innerStructure.TreeNode;

public class BuildTreePrePost {
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        if (pre == null || post == null ||
                pre.length == 0 || post.length == 0)
            return null;

        return build(pre, 0, post, 0, pre.length);
    }

    private TreeNode build (int[] in, int s1, int[] post, int s2, int len) {
        if (len <= 0) return null;
        if (len == 1) return new TreeNode(in[s1]);

        int rootVal = in[s1];
        int loc = s2;
        while (loc < s2 + len && post[loc] != in[s1 + 1]) {
            loc++;
        }

        int leftLen = loc - s2 + 1;
        int rightLen = len - leftLen - 1;

        TreeNode root = new TreeNode(rootVal);
        root.left = build(in, s1 + 1, post, s2, leftLen);
        root.right = build(in, s1 + leftLen + 1, post, s2 + leftLen, rightLen);

        return root;
    }
}
