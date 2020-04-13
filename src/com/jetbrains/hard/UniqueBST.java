package com.jetbrains.hard;

import com.jetbrains.innerStructure.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniqueBST {
    Map<Integer, Integer> map;
    public int numTrees(int n) {
        map = new HashMap<>();

        return helper(n);
    }

    private int helper(int m) {
        if (map.containsKey(m)) {
            return map.get(m);
        }

        // base case
        if (m == 0 || m == 1) {
            map.put(m, 1);
            return 1;
        }

        // recursive case
        int total = 0;
        for (int i = 1; i <= m; ++i) {
            total += (helper(i - 1) * helper(m - i));
        }

        // DP memorization
        map.put(m, total);

        return total;
    }

    
    // follow up, print all the tree node
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<TreeNode>();
        }
        return helper(1, n);
    }

    private List<TreeNode> helper(int low, int high) {
        List<TreeNode> list = new ArrayList<>();

        if (low > high) {
            list.add(null);
            return list;
        }

        for (int i = low; i <= high; ++i) {

            List<TreeNode> left = helper(low, i - 1);
            List<TreeNode> right = helper(i + 1, high);

            for (TreeNode j : left) {
                for (TreeNode k : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = j;
                    root.right = k;
                    list.add(root);
                }
            }
        }

        return list;
    }
}
