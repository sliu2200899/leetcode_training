package com.jetbrains.master;

import com.jetbrains.innerStructure.TreeNode;

public class IncreasingOrderBST {
    // method 1:  you can traverse the bst using in-order traversal method, after that construct a new tree based on the list
    // time: O(N), where N is the number of nodes in the given tree
    // space: O(N), the sizer of the answer

    // method 2:
    // We can perform the same in-order traversal as in Approach 1. During the traversal, we'll construct the answer on the fly,
    // reusing the nodes of the given tree by cutting their left child and adjoining them to the answer

    TreeNode cur;
    public TreeNode increasingBST(TreeNode root) {
        TreeNode ans = new TreeNode(0);
        cur = ans;
        inorder(root);
        return ans.right;
    }

    public void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        node.left = null;
        cur.right = node;
        cur = node;
        inorder(node.right);
    }
}
