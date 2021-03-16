package com.jetbrains.classic.searchByStructure.treeSearch.bst;

import com.jetbrains.innerStructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class SearchRangeBST {
    /*
        Given two values k1 and k2 (where k1 < k2) and a root pointer to a Binary Search Tree.
        Find all the keys of tree in range k1 to k2. i.e. print all x such that k1<=x<=k2 and x is a key of given BST.
        Return all the keys in ascending order.

        example:
            If k1 = 10 and k2 = 22, then your function should return [12, 20, 22].
                20
              /    \
             8     22
            / \
           4  12
     */
    /*
        D & C
        if root.val < k1 , then we only search for right subtree
        if root.val > k2, then we only search for left subtree
        else we need to search for both left and right subtree

        after that, we need to add teh result from left subtree first, then root.val, then add the result from right subtree.
     */

    public ArrayList<Integer> searchRange(TreeNode root, int k1, int k2) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        if(root.val < k1) {
            return searchRange(root.right, k1, k2);
        }

        if (root.val > k2) {
            return searchRange(root.left, k1, k2);
        }

        if (root.val >= k1 && root.val <= k2) {
            List<Integer> left = searchRange(root.left, k1, k2);
            List<Integer> right = searchRange(root.right, k1, k2);

            result.addAll(left);
            result.add(root.val);
            result.addAll(right);
        }

        return result;
    }
}
