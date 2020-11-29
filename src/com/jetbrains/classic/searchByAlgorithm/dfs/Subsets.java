package com.jetbrains.classic.searchByAlgorithm.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets {
    // backtracking
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null) return res;

        helper(nums, res, new ArrayList<>(), 0);

        return res;
    }

    private void helper(int[] nums, List<List<Integer>> res, List<Integer> list, int k) {
        // base case
        res.add(new ArrayList<>(list));

        // recursive case
        for (int i = k; i < nums.length; ++i) {
            list.add(nums[i]);
            helper(nums, res, list, i + 1);
            list.remove(list.size() - 1);
        }
    }


    // binary sorted subsets
    /*
     The idea is that we map each subset to a bitmask of length n, where 1 on the ith position in bitmask
     means the presence of nums[i] in the subset, and 0 means its absence.

     algo:
     Generate all possible binary bitmasks of length n.
     Map a subset to each bitmask: 1 on the ith position in bitmask means the presence of nums[i] in the subset, and 0 means its absence.
     Return output list.
    */
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        int n = nums.length;

        for (int i = (int)Math.pow(2, n); i < (int)Math.pow(2, n + 1); ++i) {
            String bitmask = Integer.toBinaryString(i).substring(1);

            // append subset corresponding to that bitmask
            List<Integer> curr = new ArrayList<>();
            for (int j = 0; j < n; ++j) {
                if (bitmask.charAt(j) == '1') curr.add(nums[j]);
            }

            output.add(curr);
        }

        return output;
    }

    // follow up: what if the nums array contains duplicates?
    // the key is that
    /*
        if we have not visited the first number, we should take the duplicates into consideration.
        However, if we have visited this number, we should just ignore.

        the difference between 3 sum with dup is that in this problem we need to consider the situation like
         [1, 2, 2]
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null) return res;

        Arrays.sort(nums);
        helper2(nums, res, new ArrayList<>(), 0, new boolean[nums.length]);

        return res;
    }

    private void helper2(int[] nums, List<List<Integer>> res, List<Integer> list, int start, boolean[] visited) {
        // base case
        res.add(new ArrayList<>(list));

        // recursive case
        for (int i = start; i < nums.length; ++i) {
            if (i != 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue;

            visited[i] = true;
            list.add(nums[i]);
            helper2(nums, res, list, i + 1, visited);
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }
}
