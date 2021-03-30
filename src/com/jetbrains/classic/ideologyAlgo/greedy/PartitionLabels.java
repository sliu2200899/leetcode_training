package com.jetbrains.classic.ideologyAlgo.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartitionLabels {
    /*
        preferred simple one
        time:  O(n) where N is the length of S
        space: O(1)
     */
    public List<Integer> partitionLabels(String S) {
        if (S == null || S.length() == 0) {
            return null;
        }

        List<Integer> list = new ArrayList<>();
        int[] map = new int[26];   // record the last index of each char

        for (int i = 0; i < S.length(); ++i) {
            map[S.charAt(i) - 'a'] = i;
        }

        // record the end index of the current sub string
        int last = 0;
        int start = 0;
        for (int i = 0; i < S.length(); ++i) {
            last = Math.max(last, map[S.charAt(i) - 'a']);  // last refers to the largest last index of the chars I've visited so far.
            if (last == i) {   // last == i  represents we have found the substring ...
                list.add(last - start + 1);
                start = last + 1;
            }

        }
        return list;
    }

    /*
        my approach:
     */
    public List<Integer> partitionLabels2(String S) {
        // map
        // sliding window, use two pointers
        // s = ababcbacadefegdehijhklij
        // i,  j    s(i,j) contains all the characters like a, b, c  map.entry
        // List<Character> list...
        //  step 1: j
        // iterate each character inside the list, and check the corresponding freq is 0;
        // for each round, we try to get a substring
        // inner loop here j
        //    add the char to the list
        //.   iterate each character inside the list, and check the corresponding freq is 0;
        //.   if freq of all the characters is 0, return length of this substring.

        List<Integer> res = new ArrayList<>();
        if (S == null || S.length() == 0) {
            return res;
        }

        Map<Character, Integer> map = new HashMap<>();
        char[] chArr = S.toCharArray();
        for (char c : chArr) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        // "ababcbacadefegdehijhklij"
        int i = 0;
        while (i < chArr.length) {
            int j = i;
            List<Character> list = new ArrayList<>();

            while (j <= chArr.length && !isValidPartition(list, map)) {
                list.add(chArr[j]);
                map.put(chArr[j], map.get(chArr[j]) - 1);
                j++;
            }

            if (j <= chArr.length) {
                res.add(j - i);
            }
            i = j;
        }

        return res;
    }

    private boolean isValidPartition(List<Character> list, Map<Character, Integer> map) {
        for (char i : list) {
            if (map.get(i) != 0) {
                return false;
            }
        }

        return list.size() != 0;
    }
}
