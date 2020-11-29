package com.jetbrains.classic.searchByAlgorithm.dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    // follow up: what if the nums array contains duplicates
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null) return res;

        Set<List<Integer>> set = new HashSet<>();
        dfs2(nums, res, new ArrayList<>(), new boolean[nums.length], set);

        return res;
    }

    private void dfs2(int[] nums, List<List<Integer>> res, List<Integer> list, boolean[] visited, Set<List<Integer>> set) {
        // base case
        if (list.size() == nums.length) {
            if (!set.contains(list)) {
                List<Integer> tmp = new ArrayList<>(list);
                set.add(tmp);
                res.add(tmp);
            }
            return;
        }

        // recursive case
        for (int i = 0; i < nums.length; ++i) {
            if (visited[i]) continue;

            visited[i] = true;
            list.add(nums[i]);
            dfs2(nums, res, list, visited, set);
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }
}
