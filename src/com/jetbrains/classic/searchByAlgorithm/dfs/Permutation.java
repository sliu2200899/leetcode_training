package com.jetbrains.classic.searchByAlgorithm.dfs;

import java.util.*;

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
    /*
        First of all, let us review the general idea of permutation with an example.

        Given the input array [1, 1, 2], to generate a permutation of the array, we could follow the
        Depth-First Search (DFS) approach, or more precisely the backtracking technique as one will see later.

        let's walk through the example with paper and pencil as follows:
        1. Given the input of [1, 1, 2], at the first stage, we have 2 choices to pick a number as the first number in the final permutation,
        i.e. 1 and 2. Suppose that we pick the number 1, now the remaining numbers would become [1, 2].
        Note: The reason that we have only 2 choices instead of 3, is that there is a duplicate in the given input.
        Picking any of the duplicate numbers as the first number of the permutation would lead us to the same permutation at the end.
        Should the numbers in the array be all unique, we would then have the same number of choices as the length of the array.

        2. At the second stage, we now then have again 2 choices, i.e. [1, 2]. Let us pick again the number 1,
        which leaves us the only remaining number 2.

        3. Now at the third stage, we have only one candidate number left, i.e. [2]. We then pick the last remaining number,
        which leads to a final permutation sequence of [1, 1, 2].

        4. Moreover, we need to revisit each of the above stages, and make a different choice in order to try out all possibilities.
        The reversion of the choices is what we call backtracking.
     */
    // We illustrate all potential exploration in the following graph where each node represents a choice at a specific stage:
    // preferred way to solve the problem
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null) return res;

        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        dfs2(res, list, nums, new boolean[nums.length]);
        return res;
    }

    private void dfs2(List<List<Integer>> res, List<Integer> list, int[] nums, boolean[] visited) {
        if (nums.length == list.size()) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; ++i) {
            if (i != 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }

            if (!visited[i]) {
                visited[i] = true;
                list.add(nums[i]);
                dfs2(res, list, nums, visited);
                list.remove(list.size() - 1);
                visited[i] = false;
            }
        }
    }








    public List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null) return res;

        Set<String> set = new HashSet<>();
        dfs3(nums, res, new ArrayList<>(), new boolean[nums.length], set);

        return res;
    }

    private void dfs3(int[] nums, List<List<Integer>> res, List<Integer> list, boolean[] visited, Set<String> set) {
        // base case
        if (list.size() == nums.length) {
            if (!set.contains(list.toString())) {
                List<Integer> tmp = new ArrayList<>(list);
                set.add(tmp.toString());
                res.add(tmp);
            }
            return;
        }

        // recursive case
        for (int i = 0; i < nums.length; ++i) {
            if (visited[i]) continue;

            visited[i] = true;
            list.add(nums[i]);
            dfs3(nums, res, list, visited, set);
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }
}
