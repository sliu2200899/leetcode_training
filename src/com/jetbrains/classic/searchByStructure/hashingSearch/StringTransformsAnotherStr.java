package com.jetbrains.classic.searchByStructure.hashingSearch;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StringTransformsAnotherStr {
    /*
    clarify:
        1. input, output, example
            can be empty or not.  could be   return false;
            input contains only lowercase letters
        2. aabcc =>. aabbb => aaeee. allowed
    algo:

        str1 = "aabcc", str2 = "ccdee"
        a       a       b       c       c    => recode the char and its freq
        c       c       d       e       e
                i

        build the mapping relationship for str1
        at the same time we count the number of the unique characters within str2
        the key of this problem
            if there is a mapping conflict between characters in str1 and str2, then we are going to return false directly
            otherwise we depend on the unique numbering characters within str2, so if it is smaller than 26 there is always a third character we can make a use of to transform

    time: O(n);
    space: O(n)


*/
    public boolean canConvert(String str1, String str2) {
        if (str1.equals(str2)) {
            return true;
        }

        Map<Character, Character> map = new HashMap<>();
        Set<Character> set = new HashSet<>();

        for (int i = 0; i < str1.length(); ++i) {
            char c1 = str1.charAt(i);
            char c2 = str2.charAt(i);
            set.add(c2);

            if (!map.containsKey(c1)) {
                map.put(c1, c2);
            }
            if (map.get(c1) != c2) {
                return false;
            }
        }
        return set.size() < 26;
    }
}
