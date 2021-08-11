package com.jetbrains.classic.topic.stringMatch;

import java.util.HashMap;
import java.util.Map;

public class ScrambleString {
    Map<String, Boolean> map = new HashMap<>();
    public boolean isScramble(String s1, String s2) {
        StringBuilder sb = new StringBuilder();
        sb.append(s1).append(s2);

        String key = sb.toString();
        if (map.containsKey(key)) {
            return map.get(key);
        }

        if (s1.equals(s2)) {
            map.put(key, true);
            return true;
        }

        int[] letters = new int[26];
        for (int i = 0; i < s1.length(); ++i) {
            letters[s1.charAt(i) - 'a']++;
            letters[s2.charAt(i) - 'a']--;
        }

        for (int i = 0; i < letters.length; ++i) {
            if (letters[i] != 0) {
                map.put(key, false);
                return false;
            }
        }

        for (int i = 1; i < s1.length(); ++i) {
            if(isScramble(s1.substring(0, i), s2.substring(0, i)) &&
                    isScramble(s1.substring(i), s2.substring(i))) {
                map.put(key, true);
                return true;
            }

            if (isScramble(s1.substring(0, i), s2.substring(s1.length() - i)) &&
                    isScramble(s1.substring(i), s2.substring(0, s1.length() - i))) {
                map.put(key, true);
                return true;
            }
        }

        map.put(key, false);
        return false;
    }
}
