package com.jetbrains.master;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
    /*
        combination sum
Given an array of distinct integers candidates and a target integer target,
return a list of all unique combinations of candidates where the chosen numbers sum to target.
You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times.
Two combinations are unique if the frequency of at least one of the chosen numbers is different.

It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return res;

        Arrays.sort(candidates);
        dfs(candidates, target, 0, res, new ArrayList<Integer>());

        return res;
    }

    private void dfs(int[] candidates, int remaining, int start, List<List<Integer>> res, List<Integer> list) {
        if (remaining == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        if (remaining < 0) {
            return;
        }

        for (int i = start; i < candidates.length; ++i) {
            list.add(candidates[i]);
            dfs(candidates, remaining - candidates[i], i, res, list);
            list.remove(list.size() - 1);
        }
    }


    /*
        combination sum 2:
Given a collection of candidate numbers (candidates) and a target number (target),
find all unique combinations in candidates where the candidate numbers sum to target.

Each number in candidates may only be used once in the combination.

Note: The solution set must not contain duplicate combinations.
     */

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return res;

        Arrays.sort(candidates);
        dfs(candidates, target, 0, res, new ArrayList<>(), new boolean[candidates.length]);

        return res;
    }

    private void dfs(int[] candidates, int target, int start, List<List<Integer>> res, List<Integer> list, boolean[] visited) {

        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }

        if (start > candidates.length || target < 0) {
            return;
        }

        for (int i = start; i < candidates.length; ++i) {
            if (i != 0 && candidates[i] == candidates[i - 1] && !visited[i - 1]) continue;

            if (!visited[i]) {
                visited[i] = true;
                list.add(candidates[i]);
                dfs(candidates, target - candidates[i], i + 1, res, list, visited);
                list.remove(list.size() - 1);
                visited[i] = false;
            }
        }
    }

    /*
        combination sum 3:
just need to take care about the result condition
     */

    public List<List<Integer>> combinationSum3(int k, int n) {
        // start in the recursive method
        //
        List<List<Integer>> res = new ArrayList<>();

        dfs(k, n, 1, res, new ArrayList<>());

        return res;
    }

    private void dfs(int k, int n, int start, List<List<Integer>> res, List<Integer> list) {
        if (n == 0 && list.size() == k) {
            res.add(new ArrayList<>(list));
            return;
        }

        if (n < 0 || start > 9 || list.size() > k) {
            return;
        }

        for (int i = start; i <= 9; ++i) {
            list.add(i);
            dfs(k, n - i, i + 1, res, list);
            list.remove(list.size() - 1);
        }
    }
}
