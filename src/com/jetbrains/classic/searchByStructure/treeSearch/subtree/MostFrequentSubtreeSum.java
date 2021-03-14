package com.jetbrains.classic.searchByStructure.treeSearch.subtree;

import com.jetbrains.innerStructure.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MostFrequentSubtreeSum {
    int maxCount = 0;
    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) return new int[0];
        // get all the possible path sum and use hashmap to store pathsum as key and num of occurances as value
        Map<Integer, Integer> map = new HashMap<>();
        getPathSum(root, map);

        // output the most frequent subtree sum
        List<Integer> list = new ArrayList<>();
        for (int k : map.keySet()) {
            if (map.get(k) == maxCount) {
                list.add(k);
            }
        }

        return list.stream().mapToInt(x -> x).toArray();
    }

    private int getPathSum(TreeNode root, Map<Integer, Integer> map) {
        if (root == null) {
            return 0;
        }

        int left = getPathSum(root.left, map);
        int right = getPathSum(root.right, map);

        int subtreeSum = left + right + root.val;

        map.put(subtreeSum, map.getOrDefault(subtreeSum, 0) + 1);
        maxCount = Math.max(maxCount, map.get(subtreeSum));

        return subtreeSum;
    }
}
