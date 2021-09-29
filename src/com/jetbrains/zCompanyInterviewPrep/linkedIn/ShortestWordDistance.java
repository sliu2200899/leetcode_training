package com.jetbrains.zCompanyInterviewPrep.linkedIn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShortestWordDistance {
    /*
        version 1:
        keeping two indices i1 and i2 where we store the most recent locations of word1 and word2
        Each time we find a new occurrence of one of the words,

        time: O(NM)   where NN is the number of words in the input list, and MM is the total length of two input words.
        space: O(1)
     */
    public int shortestDistance(String[] words, String word1, String word2) {
        int i1 = -1, i2 = -1;
        int minDistance = words.length;

        for (int i = 0; i < words.length; ++i) {
            if (words[i].equals(word1)) {
                i1 = i;
            } else if (words[i].equals(word2)) {
                i2 = i;
            }

            if (i1 != -1 && i2 != -1) {
                minDistance = Math.min(minDistance, Math.abs(i1 - i2));
            }
        }

        return minDistance;
    }


    // follow up -> 如果有很多words 怎么办

    /*
        version 2:

        time: The time complexity of the constructor of our class is O(N) considering there were NN words in the original list.
        space:  O(N) for the dictionary that we prepare in the constructor
     */

    class WordDistance {

        Map<String, List<Integer>> map;
        public WordDistance(String[] wordsDict) {
            map = new HashMap<>();
            for (int i = 0; i < wordsDict.length; ++i) {
                map.putIfAbsent(wordsDict[i], new ArrayList<>());
                map.get(wordsDict[i]).add(i);
            }
        }

        public int shortest(String word1, String word2) {
            List<Integer> ind1 = map.get(word1);   // sorted ...
            List<Integer> ind2 = map.get(word2);

            int l1 = 0, l2 = 0, diff = Integer.MAX_VALUE;
            while (l1 < ind1.size() && l2 < ind2.size()) {
                diff = Math.min(diff, Math.abs(ind1.get(l1) - ind2.get(l2)));
                if (ind1.get(l1) < ind2.get(l2)) {
                    l1++;
                } else {
                    l2++;
                }
            }

            return diff;
        }
    }

    /*
        follow up:
            1.call很多次 怎么优化 -> 提前把每一个two words pair算一遍distance 放到hashmap里 可以做到O(1)
     */
}
