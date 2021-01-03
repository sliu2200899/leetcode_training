package com.jetbrains.master;

import java.util.HashMap;
import java.util.Map;

public class PermutePalindrome {
    public boolean canPermutePalindrome(String s) {
        char[] arr = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : arr) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int odd = 0;
        for (Character key : map.keySet()) {
            if (map.get(key) % 2 == 1) {
                odd++;
            }
        }
        return odd <= 1;
    }
}
