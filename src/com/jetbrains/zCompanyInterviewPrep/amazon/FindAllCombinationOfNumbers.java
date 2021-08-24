package com.jetbrains.zCompanyInterviewPrep.amazon;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class FindAllCombinationOfNumbers {
    /*
        given a positive integer, target, print all possible combiination of positive integers that sum
        up to the target number.

        for example, if we are given input '5', there are the possible sum combiination
         1,4
         2,3
         1,1,3
         1,2,2
         1,1,1,2
         1,1,1,1,1
     */
    public List<List<Integer>> print_all_sum(int target) {
        List<List<Integer>> res = new ArrayList<>();

        dfs(res, target, target, 1, new ArrayList<Integer>());

        return res;
    }

    private void dfs(List<List<Integer>> res, int n, int target, int start, ArrayList<Integer> list) {
        // base case
        if (target == 0 && list.size() != 1) {
            res.add(new ArrayList<>(list));
            return;
        }

        if (target < 0) {
            return;
        }

        for (int i = start; i <= n; ++i) {
            list.add(i);
            dfs(res, n, target - i, i, list);
            list.remove(list.size() - 1);
        }
    }
}
