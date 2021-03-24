package com.jetbrains.classic.searchByAlgorithm.bfs;

import java.util.*;

public class WordBreak2 {
    /*
        Recursion + Memoization
        time: O(n^2)
            w/o memorization,
            T(n) = T(n-1) + T(n-2) + T(n-3) + .... + T(1)   => exponential
            Now if we add memorization, by the time we finish doing T(n-1), We already have the memorization result for n - 2, n - 3 ... 1.
            T(n) = T(n-1) + n = T(n-2) + (n-1) + n = ... = T(1) + 2 + 3 + ... + n = n(n+1)/2 => O(n^2)
        space: O(n)   the depth of recursion tree can go up to n.

     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        Map<String, List<String>> map = new HashMap<>();
        Set<String> set = new HashSet<>(wordDict);
        return helper(s, map, set);
    }

    private List<String> helper(String s, Map<String, List<String>>map, Set<String> set) {
        if (map.containsKey(s)) return map.get(s);

        List<String> res = new ArrayList<>();
        if (s.length() == 0) {
            res.add("");
            return res;
        }

        for (String word : set) {
            if (s.startsWith(word)) {
                List<String> sublist = helper(s.substring(word.length()), map, set);
                for (String sub : sublist) {
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);
                }
            }
        }
        map.put(s, res);
        return res;
    }

    /*
        backtracking + pruning
        Recursion + Memoization
        time: O(n^2)
            w/o memorization,
            T(n) = T(n-1) + T(n-2) + T(n-3) + .... + T(1)   => exponential

            proof:
              T(1) = T(2) = 1
              T(3) = T(1) + T(2) = 1+1 =2; // 2^1
              T(4) = T(1)+ T(2) + T(3) = 1+1+2 =4; //2^2
              T(5) = T(1) + T(2) +T(3) +T(4) = 1+1+2+4 =8; //2^3

            it will be clear that the Time complexity is 2^(n-2)
            but here we have pruning, so out time complexity is pretty low...
     */
    public List<String> wordBreak2(String s, List<String> wordDict) {
        // dfs + backtracking

        List<String> res = new ArrayList<>();
        if (s == null || wordDict == null || wordDict.size() == 0)
            return res;

        Set<String> set = new HashSet<>(wordDict);

        dfs(s, "", res, set);

        return res;
    }

    private void dfs(String s, String result, List<String> res, Set<String> set) {
        if (s.length() == 0) {
            res.add(result.trim());
            return;
        }

        for (int i = 1; i <= s.length(); ++i) {
            String prefixStr = s.substring(0, i);
            if (set.contains(prefixStr)) {   // pruning
                dfs(s.substring(i), result + " " + prefixStr, res, set);
            }
        }
    }
}
