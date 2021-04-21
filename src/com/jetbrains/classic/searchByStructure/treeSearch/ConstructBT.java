package com.jetbrains.classic.searchByStructure.treeSearch;

import com.jetbrains.innerStructure.TreeNode;

public class ConstructBT {
    /*
        construct binary tree from preorder and inorder
     */
    public TreeNode buildTreePreorderInorder(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null) return null;

        return helper1(preorder, 0, inorder, 0, preorder.length);
    }

    private TreeNode helper1(int[] pre, int preStart, int[] in, int inStart, int len) {
        // base case
        if (len <= 0) return null;    // don't forget
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
        root.left = helper1(pre, preStart + 1, in, inStart, leftLen);
        root.right = helper1(pre, preStart + 1 + leftLen, in, inStart + 1 + leftLen, rightLen);

        return root;
    }

    /*
        construct binary tree from postorder and inorder
     */
    /*
        given binary tree like

        inorder traversal:   [9,3,15,20,7],
        postorder traversal: [9,15,7,20,3]
                                   i

    */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return build(inorder, 0, postorder, postorder.length - 1, inorder.length);
    }

    private TreeNode build(int[] inorder, int inStart, int[] postorder, int postStart, int len) {
        if (len <= 0) return null;
        if (len == 1) return new TreeNode(inorder[inStart]);

        int rootVal = postorder[postStart];
        int index = 0;
        while (index < len && inorder[inStart + index] != rootVal) {
            index++;
        }

        int lenLeft = index;
        int lenRight = len - 1 - lenLeft;

        TreeNode root = new TreeNode(rootVal);
        root.left = build(inorder, inStart, postorder, postStart - 1 - lenRight, lenLeft);
        root.right = build(inorder, inStart + 1 + lenLeft, postorder, postStart - 1, lenRight);

        return root;
    }
}
