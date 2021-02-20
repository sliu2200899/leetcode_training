package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

public class CoinInALine {
    /*
        coins in a line 1
        There are n coins in a line. Two players take turns to take one or two coins from right side until there are no more coins left. The player who take the last coin wins.
        Could you please decide the first player will win or lose?
        If the first player wins, return true, otherwise return false.


     */
    public boolean firstWillWin(int n) {
        // write your code here
        if (n <= 0) {
            return false;
        }
        Map<Integer, Boolean> map = new HashMap<>();
        return dfs(n, map);
    }

    private boolean dfs(int n, Map<Integer, Boolean> map) {
        if (map.containsKey(n)) {
            return map.get(n);
        }

        if (n == 0) {
            return false;
        }

        if (n == 1 || n == 2) {
            return true;
        }

        boolean ans = false;
        if (!dfs(n-1, map) && !dfs(n-2, map)) {
            ans = true;
        }

        map.put(n, ans);
        return ans;
    }
}
