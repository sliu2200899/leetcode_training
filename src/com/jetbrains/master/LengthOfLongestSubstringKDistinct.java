package com.jetbrains.master;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestSubstringKDistinct {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0) return 0;

        Map<Character, Integer> map = new HashMap<>();
        int index = 0, len = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);

            while (map.size() > k) {
                char old = s.charAt(index);
                map.put(old, map.get(old) - 1);
                if (map.get(old) == 0) {
                    map.remove(old);
                }
                index++;
            }
            len = Math.max(len, i - index + 1);
        }

        return len;
    }
}
