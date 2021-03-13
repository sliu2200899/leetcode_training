package com.jetbrains.classic.searchByStructure.treeSearch;

import com.jetbrains.innerStructure.TreeNode;

public class LongestUniValue {
    /*

        similar to teh maximum path sum
        Same idea but looked more intuitive after revisiting this problem.

        Basically, if every node can tell how far the path from it can extend on the left and on the right, we are done!
        As part of traversal of the tree, we touch every node so we can keep updating max with that answer at each node.
        Now, what do we mean by extending? Simply compare the values of the current node and its children. Then any path starting at the child can be extended to our node.

        If we cannot extend our path either to left or right, we simply return 0.
        If we can extend only one side, we add 1 to that side path and return to the caller.
        If we can extend to both sides, max will be the total path extended to the left side and right side combined. But to our caller, we just pick the max of the two sides and return.

        time: O(N), where N is hte number of nodes in the tree. we process every node once.
        space: O(H), where H ishte height of the tree. Our recursive call stack could be  up to H layers deep.
     */
    int max = 0;
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        dfs(root);
        return max;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;

        int left = dfs(root.left);
        int right = dfs(root.right);

        if (root.left != null && root.val == root.left.val) {
            left++;
        } else {
            left = 0;
        }

        if (root.right != null && root.val == root.right.val) {
            right++;
        } else {
            right = 0;
        }

        max = Math.max(max, left + right);

        return Math.max(left, right);
    }
}
