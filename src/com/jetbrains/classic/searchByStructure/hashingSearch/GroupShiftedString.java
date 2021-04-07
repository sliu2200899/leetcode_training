package com.jetbrains.classic.searchByStructure.hashingSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupShiftedString {
    /*
    clarify:
        1. input, output, example
        2. shift the string "abc" -> "zab"   allowed
    algo:
        how to shift the string ?

        strings = ["abc","bcd","acef","xyz","az","ba","a","z"]

        group them:  ["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]

                 ["abc","bcd","xyz"] y
                 "ba",  => az

                 acef  =>
                 bdfg  => cegh => ... => a***

                 map, pattern as key and List<String> as value


*/
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> list = new ArrayList<>();
        if (strings == null || strings.length == 0) {
            return list;
        }

        Map<String, List<String>> map = new HashMap<>();
        for (String s : strings) {
            String pattern = getPattern(s);
            map.putIfAbsent(pattern, new ArrayList<>());
            map.get(pattern).add(s);
        }

        for (String s : map.keySet()) {
            list.add(map.get(s));
        }
        return list;
    }

    private String getPattern(String s) {
        if (s.charAt(0) == 'a') {
            return s;
        }

        char[] arr = s.toCharArray();
        for (int i = 0; i <= 26; ++i) {
            for (int j = 0; j < arr.length; ++j) {
                arr[j] = (char)((arr[j] - 'a' + 1) % 26 + 'a');
            }
            if (arr[0] == 'a') {
                return String.valueOf(arr);
            }
        }

        return "";
    }
}
