package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

import java.util.*;

public class WordBreak2 {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Map<String, List<String>> map = new HashMap<>();
        Set<String> set = new HashSet<>(wordDict);
        return helper(s, map, set);
    }

    private List<String> helper(String s, Map<String, List<String>>map,  Set<String> set) {
        // in memory, directly return
        if (map.containsKey(s)) return map.get(s);

        List<String> res = new ArrayList<>();
        if (s.length() == 0) {
            res.add("");
            return res;
        }

        for (String word: set) {
            if (s.endsWith(word)) {
                List<String> sublist = helper(s.substring(0, (s.length() - word.length())), map, set);
                for (String sub : sublist) {
                    res.add(sub + (sub.isEmpty() ? "" : " ") + word);
                }
            }
        }

        map.put(s, res);
        return res;
    }
}
