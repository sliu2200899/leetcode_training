package com.jetbrains.classic.searchByStructure.hashingSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringConcatinationAllWords {
    /*
        using two maps
        I like your idea of using isConcat. Instead of using equals at the end, I think it's faster to just return false when you know the maps won't match:
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> list = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return list;
        }

        Map<String, Integer> counts = new HashMap<>();
        for (String word : words) {
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }

        int sLen = s.length(), num = words.length, wordLen = words[0].length();
        for (int i = 0; i < sLen - num * wordLen + 1; ++i) {
            String sub = s.substring(i, i + num * wordLen);
            if (isConcat(sub, counts, wordLen)) {
                list.add(i);
            }
        }

        return list;
    }

    private boolean isConcat(String candidate, Map<String, Integer> counts, int wordLen) {
        Map<String, Integer> seen = new HashMap<>();
        for (int i = 0; i < candidate.length(); i += wordLen) {
            String word = candidate.substring(i, i + wordLen);
            seen.put(word, seen.getOrDefault(word, 0) + 1);
            if (!counts.containsKey(word) || seen.get(word) > counts.get(word)) {
                return false;
            }
        }
        return true;
    }
}
