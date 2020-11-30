package com.jetbrains.master;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    /*
    If the current combination is done - add it to output.

    Iterate over the integers from first to n.

    Add integer i into the current combination curr.

    Proceed to add more integers into the combination : backtrack(i + 1, curr).

    Backtrack by removing i from curr.
     */

    public List<List<Integer>> combine(int n, int k) {

        List<List<Integer>> res = new ArrayList<>();

        dfs(1, n, k, res, new ArrayList<Integer>());

        return res;
    }

    private void dfs(int start, int n, int k, List<List<Integer>> res, List<Integer> list) {
        if (list.size() == k) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = start; i <= n; ++i) {
            list.add(i);
            dfs(i + 1, n, k, res, list);
            list.remove(list.size() - 1);
        }
    }
}
