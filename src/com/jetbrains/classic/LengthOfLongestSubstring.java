package com.jetbrains.classic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LengthOfLongestSubstring {

    /*
        first Sliding window method, 2 times visit
        In the naive approaches, we repeatedly check a substring to see if it has duplicate character.
        But it is unnecessary.

        By using HashSet as a sliding window, checking if a character in the current can be done in O(1)
        A sliding window is an abstract concept commonly used in array/string problems.
        A window is a range of elements in the array/string which usually defined by the start and end indices, i.e. [i, j) (left-closed, right-open).
        A sliding window is a window "slides" its two boundaries to the certain direction. For example, if we slide [i, j) to the right by 11 element, then it becomes [i+1, j+1)(left-closed, right-open).


        time complexity: O(2n)
        space complexity: O(min(m,n)
     */
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }


    /*
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
