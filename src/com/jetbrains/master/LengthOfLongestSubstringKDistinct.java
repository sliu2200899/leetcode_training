package com.jetbrains.master;

import java.util.HashMap;
import java.util.LinkedHashMap;
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

    /*
        time complexity: O(n) and single pass
     */
    public int lengthOfLongestSubstringKDistinct2(String s, int k) {
        int n = s.length();
        if (n*k == 0) return 0;

        // sliding window left and right pointers
        int left = 0;
        int right = 0;
        // hashmap character -> its rightmost position
        // in the sliding window
        LinkedHashMap<Character, Integer> hashmap = new LinkedHashMap<Character, Integer>(k + 1);

        int max_len = 1;

        while (right < n) {
            Character character = s.charAt(right);
            // if character is already in the hashmap -
            // delete it, so that after insert it becomes
            // the rightmost element in the hashmap
            if (hashmap.containsKey(character))
                hashmap.remove(character);
            hashmap.put(character, right++);

            // slidewindow contains k + 1 characters
            if (hashmap.size() == k + 1) {
                // delete the leftmost character
                Map.Entry<Character, Integer> leftmost = hashmap.entrySet().iterator().next();
                System.out.println(leftmost.getKey());
                hashmap.remove(leftmost.getKey());
                // move left pointer of the slidewindow
                left = leftmost.getValue() + 1;
            }

            max_len = Math.max(max_len, right - left);
        }
        return max_len;
    }
}
