package com.jetbrains.classic.searchByAlgorithm.bfs;

import java.util.*;

public class WordBreak {
    /*
        Recursion + Memoization
        time: O(n^2)
            w/o memorization,
            T(n) = T(n-1) + T(n-2) + T(n-3) + .... + T(1)   => exponential
            Now if we add memorization, by the time we finish doing T(n-1), We already have the memorization result for n - 2, n - 3 ... 1.
            T(n) = T(n-1) + n = T(n-2) + (n-1) + n = ... = T(1) + 2 + 3 + ... + n = n(n+1)/2 => O(n^2)
        space: O(n)   the depth of recursion tree can go up to n.
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Map<String, Boolean> map = new HashMap<>();
        Set<String> set = new HashSet<>(wordDict);
        return dfs(s, map, set);
    }

    private boolean dfs(String s, Map<String, Boolean>map,  Set<String> set) {
        // in memory, directly return
        if (map.containsKey(s)) return map.get(s);
        // set contains s
        if (set.contains(s)) {
            map.put(s, true);
            return true;
        }

        for (int i = 1; i <= s.length(); ++i) {

            if (set.contains(s.substring(0, i)) && dfs(s.substring(i), map, set)) {
                map.put(s, true);
                return true;
            }
        }

        map.put(s, false);
        return false;
    }

    /*
        BFS to solve the problem
     */
    public boolean wordBreak11(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[s.length()];
        queue.offer(0);

        while (!queue.isEmpty()) {
            int start = queue.poll();
            if (!visited[start]) {
                for (int end = start + 1; end <= s.length(); ++end) {
                    if (set.contains(s.substring(start, end))) {
                        queue.offer(end);
                        if (end == s.length()) {
                            return true;
                        }
                    }
                }
                visited[start] = true;
            }
        }
        return false;
    }
}
