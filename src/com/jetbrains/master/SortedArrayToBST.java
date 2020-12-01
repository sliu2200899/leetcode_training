package com.jetbrains.master;

import com.jetbrains.innerStructure.TreeNode;

public class SortedArrayToBST {
    /*

    Here is the funny thing about BST. Inorder traversal is not a unique identifier of BST.
    At the same time both preorder and postorder traversals are unique identifiers of BST.
    From these traversals one could restore the inorder one: inorder = sorted(postorder) = sorted(preorder),
    and inorder + postorder or inorder + preorder are both unique identifiers of whatever binary tree.

    So, the problem "sorted array -> BST" has multiple solutions.

     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null) return null;

        return helper(nums, 0, nums.length);
    }

    private TreeNode helper(int[] nums, int start, int len) {
        // base case
        if (len <= 0) return null;
        if (len == 1) return new TreeNode(nums[start]);

        // recursive case
        int mid = start + len / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, start, mid - start);
        root.right = helper(nums, mid + 1, len - len / 2 - 1);

        return root;
    }
}
