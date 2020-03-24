package com.jetbrains.master;

import com.jetbrains.innerStructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class PathSum2 {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        dfs(root, res, new ArrayList<Integer>(), sum);

        return res;
    }

    private void dfs(TreeNode root, List<List<Integer>> res, List<Integer> list, int sum) {
        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                list.add(root.val);
                res.add(new ArrayList<>(list));
                list.remove(list.size() - 1);
                return;
            }
        }

        if (root.left != null) {
            list.add(root.val);
            dfs(root.left, res, list, sum - root.val);
            list.remove(list.size() - 1);
        }
        if (root.right != null) {
            list.add(root.val);
            dfs(root.right, res, list, sum - root.val);
            list.remove(list.size() - 1);
        }
    }
}
