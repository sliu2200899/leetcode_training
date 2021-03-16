package com.jetbrains.classic.searchByStructure.treeSearch.bst;

import com.jetbrains.innerStructure.TreeNode;

public class ConvertSortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null) return null;

        return helper(nums, 0, nums.length);
    }

    private TreeNode helper(int[] nums, int start, int len) {
        if (len == 0) return null;
        if (len == 1) return new TreeNode(nums[start]);

        //1,2,3
        // start:0, len = 3 mid = 1.
        int mid = start + len / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, start, mid - start);
        root.right = helper(nums, mid + 1, len - len/2 - 1);

        return root;
    }
}
