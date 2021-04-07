package com.jetbrains.classic.searchByStructure.hashingSearch;

import java.util.*;

public class GroupAnagrams {
    /*
        clarify:
            1. input, output, example
                 output: any order
            2. size of the input array

        algo:
            strs = ["eat","tea","tan","ate","nat","bat"]
            "eat" "tea" "ate" =>.   treat it as a group cuz they have same character and have the same number of characters   =>.  aet.
            "bat"  =>  ...
            "nat" "tan" =>  ...

            Map.  aet as key, List<String> as value

            iterate over the strs array, parse each string
            finally output the map entry

        time: O(NK log K)   where N is the length of strs, and K is the maximum length of a string in strs
        space: O(NK)
    */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> list = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return list;
        }

        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String pattern = String.valueOf(ca);
            map.putIfAbsent(pattern, new ArrayList<>());
            map.get(pattern).add(s);
        }

        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            List<String> value = entry.getValue();
            list.add(value);
        }

        return list;
    }
}
