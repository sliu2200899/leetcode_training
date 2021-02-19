package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

public class FlipGame2 {
    /*
        game theory DP:
        can be solved in recursion + memoization

        The idea is try to replace every "++" in the current string s to "--" and see if the opponent can win or not, if the opponent cannot win, great, we win!

        For the time complexity, here is what I thought, let's say the length of the input string s is n, there are at most n - 1 ways to replace "++" to "--"
        (imagine s is all "+++..."), once we replace one "++", there are at most (n - 2) - 1 ways to do the replacement, it's a little bit like solving the N-Queens problem,
        the time complexity is (n - 1) x (n - 3) x (n - 5) x ..., so it's O(n!!), double factorial.
     */
    public boolean canWin(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }

        for (int i = 0; i < s.length() - 1; ++i) {
            if (s.startsWith("++", i)) {
                String t = s.substring(0, i) + "--" + s.substring(i+2);

                if (!canWin(t)) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
        follow up: improve by add memoization

        /**
         * The idea is try to replace every "++" in the current string s to "--" and see if the opponent can win or not,
         * if the opponent cannot win, great, we win! So we use DFS + Memo to solve the problem.
         *
         * Actually, we do not care which player can win for a specific string at each recursive round.
         * we only care if current player (does not matter if current player is you or your opponent) can win or not.
         * After recursively building the dfs tree, we will know the winning condition for every leaf node (still does not
         * care which player plays the last round on the leaf node), and then trace back by the bottom-up order.
         * If we can find a path that return true at the very top (this is the player condition that we only care, since we
         * assume we play first), then we can guarantee that there exist a way that first player can win
         *


     */
    public boolean canWin2(String s) {
        if (s == null || s.length() < 2) return false;

        Map<String, Boolean> map = new HashMap<>();
        return dfs(s, map);
    }

    private boolean dfs(String s, Map<String, Boolean> map) {
        //// if we already traversed this path before, retrieve information from map directly
        if (map.containsKey(s)) {
            return map.get(s);
        }

        if (s == null || s.length() < 2) {
            map.put(s, false);
            return false;
        }
        //// try every possible string until find the leaf level for every possibility
        for (int i = 0; i < s.length() - 1; ++i) {
            if (s.startsWith("++", i)) {
                String newStr = s.substring(0, i) + "--" + s.substring(i+2);
                if (!dfs(newStr, map)) {
                    map.put(s, true);
                    return true;
                }
            }
        }
        // after traversing all characters of current string, if there is no two consecutive "+" in current string,
        // return false since current player cannot win in current string condition
        map.put(s, false);
        return false;
    }
}
