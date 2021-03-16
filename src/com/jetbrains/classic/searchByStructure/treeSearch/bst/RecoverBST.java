package com.jetbrains.classic.searchByStructure.treeSearch.bst;

import com.jetbrains.innerStructure.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class RecoverBST {
    public void recoverTree(TreeNode root) {
        List<Integer> nums = new ArrayList<>();
        inorder(root, nums);
        int[] swapped = findTwoSwapped(nums);
        recover(root, 2, swapped[0], swapped[1]);
    }

    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    /*
        find two swapped elements in array -> classic problem....
     */
    private int[] findTwoSwapped(List<Integer> list) {
        int n = list.size();
        int x = -1, y = -1;
        for (int i = 0; i < n - 1; ++i) {
            if (list.get(i + 1) < list.get(i)) {
                y = list.get(i+1);

                if (x == -1) x = list.get(i);
                else break;
            }
        }

        return new int[]{x, y};
    }

    private void recover(TreeNode root, int num, int low, int high) {
        if (root != null) {
            if(root.val == low || root.val == high) {
                root.val = root.val == low ? high : low;
                if (--num == 0) return;
            }

            recover(root.left, num, low, high);
            recover(root.right, num, low, high);
        }
    }

    /*
        solution 2: iterative method
        combine the step 1 and 2.
     */
    private void swap(TreeNode a, TreeNode b) {
        int tmp = a.val;
        a.val = b.val;
        b.val = tmp;
    }
    public void recoverTree2(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root, pred = null, x = null, y = null;

        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            TreeNode node = stack.pop();
            if (pred != null && node.val < pred.val) {
                y = node;
                if (x == null) x = pred;
                else break;
            }

            pred = node;
            cur = node.right;
        }

        swap(x, y);
    }

    /*
        solution 3: morris algorithm
        The idea of Morris algorithm is to set the temporary link between the node and its predecessor: predecessor.right = root. So one starts from the node, computes its predecessor and verifies if the link is present.
        There is no link? Set it and go to the left subtree.  There is a link? Break it and go to the right subtree.
     */
}
