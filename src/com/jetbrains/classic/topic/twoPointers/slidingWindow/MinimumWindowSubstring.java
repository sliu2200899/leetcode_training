package com.jetbrains.classic.topic.twoPointers.slidingWindow;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    /*
        analyze:
            keep a hashmap which stores the character in the string t as keys and its number of occurances as values
            and keep two variables which defines the minimum window in string s. move the right pointer to scan through the string s,
            and meanwhile update teh hashmap. After update of the hashmap, we need to move the left pointer to shrink the window size so that
            it can meet the condition this would be the minimum window in s which contain all the characters in t.

            s = "ADOBECODEBANC", t = "ABC"
                 j        i
                 ADOBEC
            map:
                A 0 (-2)
                B -1
                C 0
            size: 0
            i 0

        algo:
            hashmap to store teh character and its occurance
            j
            len
            num
            start
            for i in s
                char c = s.charAt(i)
                if c exists in map
                    update the map
                    if (map.get(c) == 0) {
                        size--;
                    }
                otherwise
                    continue

                // shrink the windown by moving j
                while (size == 0)
                    if (i - j + 1 < len)
                        len = i - j + 1
                        start = j
                    char left = s.charAt(j)
                    if left not contains in map
                        j++
                    otherwise
                        map.put(left, ocurr + 1)
                        if (map.get(left) == 0) {
                            size++;
                        }

            return s.substring(start, start + len);

    */
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
