package com.jetbrains.classic.searchByStructure.treeSearch.bst;

import com.jetbrains.innerStructure.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class KthSmallestBST {
    /*
        Kth Smallest Element in a BST
        Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
     */

    public int kthSmallest(TreeNode root, int k) {
        // convert it to a sorted array
        if (root == null || k < 0) return -1;

        int num = 1;

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;

        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            TreeNode node = stack.pop();
            if (num == k) {
                return node.val;
            }
            num++;

            cur = node.right;
        }

        return -1;
    }

    // follow up:  What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently?
    // How would you optimize the kthSmallest routine?

    // Insert and delete in a BST were discussed last week, the time complexity of these operations is O(H),
    // where H is a height of binary tree, and H=logN for the balanced tree.

    // Hence without any optimisation insert/delete + search of kth element has O(2H+k) complexity. How to optimise that?
    /*
        Hi, in the followup question, H is the time for insert/delete a node in BST,
        H + k is the time for search the k-th smallest node (approach 2).
        So, without any optimization, insert/delete + search of k-th element has O(2H + k) complexity.
     */
    // we can use a double linked list to record the order
    /*
        O(H) time for the insert and delete.
        O(k) for the search of kth smallest.

        so the time complexity is O(H + k)
        space complexity is O(N)
     */


}
