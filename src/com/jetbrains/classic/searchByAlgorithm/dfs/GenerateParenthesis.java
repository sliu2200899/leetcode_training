package com.jetbrains.classic.searchByAlgorithm.dfs;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
    /*
        n = 3
        left = 3, right = 3
        recursion method to enumarate all of the combinations of well-formed parentheses.

    */
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();

        dfs(0,0,n, "", list);

        return list;
    }

    private void dfs(int left, int right, int n, String s, List<String> list) {
        if (left == n && right == n) {
            list.add(s);
            return;
        }

        if (left < right) {
            return;
        }

        if (left > n || right > n) {
            return;
        }

        if (left <= n) {
            dfs(left+1, right, n, s + '(', list);
        }

        if (right <= n) {
            dfs(left, right+1, n, s + ')', list);
        }
    }
}
