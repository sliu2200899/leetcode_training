package com.jetbrains.classic.ideologyAlgo.greedy;

import java.util.*;

public class RearrangeStringKDistanceApart {
    private class Cell{
        char c;
        int freq;
        public Cell(char c, int freq) {
            this.c = c;
            this.freq = freq;
        }
    }
    public String rearrangeString(String s, int k) {
        if (k == 0) return s;
        // map to store teh element-freq pairs
        // priorityqueue to save the cell<element, freq> which sorted by the freq.  keep the max freq number at the top of the pqmax.
        // process k characters each round
        //
        Map<Character, Integer> map = new HashMap<>();
        PriorityQueue<Cell> pqmax = new PriorityQueue<>((a, b) -> {
            if (a.freq == b.freq) {
                return a.c - b.c;
            }
            return Integer.compare(b.freq, a.freq);
        });

        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (char c : map.keySet()) {
            pqmax.offer(new Cell(c, map.get(c)));
        }

        StringBuilder sb = new StringBuilder();
        while (!pqmax.isEmpty()) {
            int i = 0;
            List<Cell> list = new ArrayList<>();
            while (!pqmax.isEmpty() && i < k) {
                Cell cur = pqmax.poll();
                sb.append(cur.c);

                cur.freq--;
                if (cur.freq != 0) {
                    list.add(cur);
                }
                ++i;
            }

            if (list.size() != 0 && i < k) {
                return "";
            }

            for (Cell c : list) {
                pqmax.offer(c);
            }
        }
        return sb.toString();
    }
}
