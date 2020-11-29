package com.jetbrains.classic.searchByAlgorithm.dfs;

import java.util.ArrayList;
import java.util.List;

public class Permutation {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null) return res;

        dfs(nums, res, new ArrayList<>(), new boolean[nums.length]);

        return res;
    }

    private void dfs(int[] nums, List<List<Integer>> res, List<Integer> list, boolean[] visited) {
        // base case
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }

        // recursive case
        for (int i = 0; i < nums.length; ++i) {
            if (visited[i]) continue;

            visited[i] = true;
            list.add(nums[i]);
            dfs(nums, res, list, visited);
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }
}
