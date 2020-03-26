package com.jetbrains.hard;

import com.jetbrains.innerStructure.TreeNode;

public class PathSum3 {

    // the problem doesn't specify that the path should go from root to leaf. only specify the path should go downward.
    // so we can find the number of the path starting at the root node. In addition to that, we should find those path
    // that start at the left subtree and right subtree.
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;

        return findPath(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    private int findPath(TreeNode root, int sum) {
        // base case
        if (root == null) return 0;

        // recursive case
        int res = 0;
        if(sum == root.val) {
            res++;
        }

        res += findPath(root.left, sum - root.val);
        res += findPath(root.right, sum - root.val);

        return res;
    }
}
