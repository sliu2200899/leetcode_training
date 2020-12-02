package com.jetbrains.classic.tree;

import com.jetbrains.innerStructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class pathSum {
    /*
        pathSum 1:
        Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all
        the values along the path equals the given sum.

        algo:
        The most intuitive way is to use a recursion here. One is going through the tree by considering at each step
        the node itself and its children. If node is not a leaf, one calls recursively hasPathSum method for its children
        with a sum decreased by the current node value. If node is a leaf, one checks if the the current sum is zero,
        i.e if the initial sum was discovered.

        time: we visit each node exactly once, thus the time complexity is O(N), where NN is the number of nodes.
        Space complexity : in the worst case, the tree is completely unbalanced, e.g. each node has only
        one child node, the recursion call would occur NN times (the height of the tree), therefore the storage
        to keep the call stack would be O(N). But in the best case (the tree is completely balanced),
        the height of the tree would be log(N). Therefore, the space complexity in this case would be
        O(log(N)).
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;

        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                return true;
            }
        }

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    /*
        pathSUm 2:
        Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

        Note: A leaf is a node with no children.

        algo:

     */
    public List<List<Integer>> pathSum2(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        helper(root, res, new ArrayList<Integer>(), sum);

        return res;
    }

    private void helper(TreeNode root, List<List<Integer>> res, List<Integer> list, int sum) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                list.add(root.val);
                res.add(new ArrayList<>(list));
                list.remove(list.size() - 1);
                return;
            }
        }

        list.add(root.val);
        helper(root.left, res, list, sum - root.val);
        helper(root.right, res, list, sum - root.val);
        list.remove(list.size() - 1);
    }

}
