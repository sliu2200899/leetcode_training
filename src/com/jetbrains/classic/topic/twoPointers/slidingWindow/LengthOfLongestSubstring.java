package com.jetbrains.classic.topic.twoPointers.slidingWindow;

import sun.jvm.hotspot.memory.Space;

import java.sql.Time;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LengthOfLongestSubstring {
    /*

        analyze:
            Input: s = "abcabcbb"
            output: 3

            "the basic idea is, keep a hashmap which stores the characters in string as keys and their positions as values,
            and keep two pointers which define the max substring. move the right pointer to scan through the string, and meanwhile
            update the hashmap. If the character is already in the hashmap, then move the left pointer to the right of the same
            character last found. Note that the two pointers can only move forward."

            sliding window,
            use two pointers i, j maintian a sliding window,
            i fast runner
            j slow runner

            iterate through the string s, move j to make sure the substring(i,j) is the substring without repeating characters
            and during the process, we need to check whether this substring is the longest or not.

        algo:
            how to use the sliding window
            i
            use hashtable to store the character and its index
            len
            for i in s:
                char c = s.charAt(i)
                if hashtable not contains c
                    map.put(c, i)
                    len = max(len, i - j + 1)
                else
                    j = max(map.get(c) + 1, j)
                    map.put(c, i)  //
                    len = max(len, i - j + 1)   // update the global longest len
            }

        test:
            input: pwwkew
                    i

            i
            map:
                p 0
                w 1
            len


        Time complexity : O(n). Index j will iterate n times.
        Space complexity : O(min(m, n)). Same as the previous approach.
            We need O(k) space for the sliding window, where k is the size of the Set.
            The size of the Set is upper bounded by the size of the string n and the size
            of the charset/alphabet mm.
     */
    public int lengthOfLongestSubstring2(String s) {
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
