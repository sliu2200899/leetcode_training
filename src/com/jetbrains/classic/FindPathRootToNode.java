package com.jetbrains.classic;

import com.jetbrains.innerStructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class FindPathRootToNode {

    /*
       print the path from the root to the required node in binary tree
     */

    public List<Integer> findPath(TreeNode root, int x) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        if (hasPath(root, res, x)) {
            return res;
        }
        return null;
    }

    private boolean hasPath(TreeNode node, List<Integer> list, int x) {
        // base case
        if (node == null) return false;

        // recursive case
        list.add(node.val);
        if (node.val == x) return true;
        if (hasPath(node.left, list, x) || hasPath(node.right, list, x)) {
            return true;
        }

        list.remove(list.size() - 1);
        return false;
    }

    public static void main(String[] args) {
        FindPathRootToNode aa = new FindPathRootToNode();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(7);


        List<Integer> list = aa.findPath(root, 7);
        for (int i = 0; i < list.size(); ++i) {
            System.out.println(list.get(i));
        }
    }
}
