package com.jetbrains.classic.searchByAlgorithm.dfs;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;

public class ConcatenatedWords {
    /*
        basic approach, just similar to work break
        but TLE...

        words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
        ["catsdogcats","dogcatsdog","ratcatdogcat"]

          i
        catsdogcats

        cat  sdogcats. ->
        cats + dfs(dogcats)
                dog + dfs(cats)
                        cats


        set to store all the words
        foreach all the words
            s.substring(0, i) exists in the set.
            dfs(s.substring(i))

    */
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();

        Set<String> set = new HashSet<>();
        for (String s : words) set.add(s);

        for (String s : words) {
            set.remove(s);
            if (dfs(set, s, new HashMap<String, Boolean>())) {    // you should add hashmap here because you need to make sure hashmap does not affect other words exploration.
                res.add(s);
            }
            set.add(s);
        }

        return res;
    }

    private boolean dfs(Set<String> set, String word, Map<String, Boolean> map) {
        if (map.containsKey(word)) {
            return map.get(word);
        }
        if (set.contains(word)) {
            map.put(word, true);
            return true;
        }

        // recursive case
        for (int i = 1; i <= word.length(); ++i) {
            if (set.contains(word.substring(0, i)) && dfs(set, word.substring(i), map)) {
                map.put(word, true);
                return true;
            }
        }

        map.put(word, false);
        return false;
    }

    /*
        DP solution to solve the problem
     */

    public static List<String> findAllConcatenatedWordsInADict2(String[] words) {
        List<String> result = new ArrayList<>();
        Set<String> preWords = new HashSet<>();
        Arrays.sort(words, new Comparator<String>() {
            public int compare (String s1, String s2) {
                return s1.length() - s2.length();
            }
        });

        for (int i = 0; i < words.length; i++) {
            if (canForm(words[i], preWords)) {
                result.add(words[i]);
            }
            preWords.add(words[i]);
        }

        return result;
    }

    private static boolean canForm(String word, Set<String> dict) {
        if (dict.isEmpty()) return false;
        boolean[] dp = new boolean[word.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= word.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (!dp[j]) continue;
                if (dict.contains(word.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[word.length()];
    }
}
