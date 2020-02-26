package com.jetbrains.classic;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;

        Map<Character, Integer> map = new HashMap<>();
        int start = 0, len = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                // why we need this Math.max()..
                // example is abcdba...   在指针i指向第二个‘b’时，left pointer变为指向'c'，然后当i指向第二个'a'时，如果此时left pointer再指向第一个‘b’，范围反而扩大就错了
                start = Math.max(map.get(c) + 1, start);
            }
            map.put(c, i);
            len = Math.max(len, i - start + 1);
        }

        return len;
    }
}
