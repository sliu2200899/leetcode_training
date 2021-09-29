package com.jetbrains.zCompanyInterviewPrep.microsoft;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NumSplit {
    public int split(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int count = 1;
        int j = 0;
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (map.containsKey(ch)) {
                if (map.get(ch) >= j) {
                    count++;
                    j = i;
                }
            }
            map.put(ch, i);
        }
        return count;
    }

    public static void main(String[] args) {
        String s = "cycle";
        NumSplit solution = new NumSplit();
        System.out.println(solution.split(s));
    }
}
