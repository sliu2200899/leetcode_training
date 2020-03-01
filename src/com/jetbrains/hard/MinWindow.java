package com.jetbrains.hard;

import java.util.HashMap;
import java.util.Map;

public class MinWindow {
    public String minWindow(String s, String t) {
        if (s == null) return "";

        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int left = 0, count = map.size();  // the point is using count to record the status of the map
        int index = 0, len = Integer.MAX_VALUE;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0) {
                    count--;
                }

                while (count == 0) {
                    if (len > (i + 1) - left) {
                        len = (i + 1) - left;
                        index = left;
                    }

                    char tmp = s.charAt(left);
                    if (map.containsKey(tmp)) {
                        map.put(tmp, map.get(tmp) + 1);
                        if (map.get(tmp) > 0) {
                            count++;
                        }
                    }
                    left++;
                }
            }
        }

        return len == Integer.MAX_VALUE ? "" : s.substring(index, index + len);
    }
}
