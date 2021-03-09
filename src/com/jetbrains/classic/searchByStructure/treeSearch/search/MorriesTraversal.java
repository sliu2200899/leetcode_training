package com.jetbrains.classic.searchByStructure.treeSearch.search;

import com.jetbrains.innerStructure.TreeNode;

import java.util.ArrayList;
import java.util.List;


/*
   before we decide to use morries, we need to clarify that if the binary tree is mutable
   three isssues we need to solve
   1. how to build the connection
   2. how to process current node
   3. how to release the connection
 */

/*
    this is new data structure: Threaded binary tree
 */
public class MorriesTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                // process the current node
                res.add(cur.val);
                cur = cur.right;
            } else {
                // navigate to the right most node in left subtrree
                TreeNode pre = cur.left;
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }

                if (pre.right == null) {
                    // build the connection
                    // res.add(cur.val); // preorder traversal
                    pre.right = cur;
                    cur = cur.left;
                } else {
                    // release the connection
                    pre.right = null;
                    res.add(cur.val); // inorder traversal
                    cur = cur.right;
                }
            }
        }
        return res;
    }
}
