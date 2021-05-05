package com.jetbrains.classic.searchByStructure.hashingSearch;

import java.util.*;

public class HandStraights {

    /*
        the same as 1296
        hashmap with array sort...
     */
    public boolean isNStraightHand(int[] hand, int W) {
        // corner case
        if ((hand.length % W) != 0) {
            return false;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : hand) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        Arrays.sort(hand);

        for (int i = 0; i < hand.length; ++i) {

            if (map.get(hand[i]) == 0) continue;

            for (int j = 0; j < W; ++j) {
                int curVal = hand[i] + j;
                if (!map.containsKey(curVal) || map.get(curVal) == 0) {
                    return false;
                }
                map.put(curVal, map.get(curVal) - 1);
            }
        }

        return true;
    }

    /*
        hashmap w/o sorting
     */
    public boolean isNStraightHand2(int[] hand, int W) {
        Map<Integer, Integer> c = new TreeMap<>();
        for (int i : hand) c.put(i, c.getOrDefault(i, 0)+1);
        for (int it : c.keySet())
            if (c.get(it) > 0)
                for (int i = W - 1; i >= 0; --i) {
                    if (c.getOrDefault(it + i, 0) < c.get(it)) return false;
                    c.put(it + i, c.get(it + i) - c.get(it));
                }
        return true;
    }

    /*
        Follow Up
        We just got lucky AC solution. Because W <= 10000.
        What if W is huge, should we cut off card on by one?
     */

    /*
        O(MlogM + N), where M is the number of different cards.
        Because I count and sort cards.
        In Cpp and Java it's O(NlogM), which can also be improved.
     */
    public boolean isNStraightHand3(int[] hand, int W) {
        Map<Integer, Integer> c = new TreeMap<>();
        for (int i : hand) c.put(i, c.getOrDefault(i, 0)+1);
        Queue<Integer> start = new LinkedList<>();
        int last_checked = -1, opened = 0;
        for (int i : c.keySet()) {
            if (opened > 0 && i > last_checked + 1 || opened > c.get(i)) return false;
            start.add(c.get(i) - opened);
            last_checked = i; opened = c.get(i);
            if (start.size() == W) opened -= start.remove();
        }
        return opened == 0;
    }
}
