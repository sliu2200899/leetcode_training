package com.jetbrains.zCompanyInterviewPrep.microsoft;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MinDeletionCharacterFreqUnique {
    public int minDeletions(String s) {
        // map: [character, integer]
        // sort values of the map
        //
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        Arrays.sort(freq);
        Set<Integer> set = new HashSet();
        int deletions = 0;
        for(int i : freq){
            while(set.contains(i) && i > 0){
                i--;
                deletions++;
            }
            set.add(i);
        }
        return deletions;
    }
}
