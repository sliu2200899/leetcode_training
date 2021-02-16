package com.jetbrains.classic.searchByAlgorithm.bfs;

import java.util.*;

public class OpenLock {
    public int openLock(String[] deadends, String target) {
        // bfs initial is 0000
        // each time go from 0 to next 1 or 9
        // check if visited and locked combination, skip and continue
        // record levels
        Set<String> locked = new HashSet<>(Arrays.asList(deadends));
        Set<String> visited = new HashSet<>();

        Deque<String> queue = new LinkedList<>();
        queue.offer("0000");
        visited.add("0000");

        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; ++i) {
                String prev = queue.poll();

                if (locked.contains(prev)) {
                    continue;
                }

                if (target.equals(prev)) {
                    return level;
                }

                List<String> nt = getNext(prev);
                for (String next : nt) {
                    if (visited.contains(next) || locked.contains(next)) {
                        continue;
                    }

                    queue.offer(next);
                    visited.add(next);
                }
            }
            level++;
        }
        return -1;
    }

    private List<String> getNext(String prev) {
        List<String> nt = new ArrayList<>();
        for (int i = 0; i < prev.length(); ++i) {
            char c = prev.charAt(i);
            nt.add(prev.substring(0, i) + (c == '9' ? 0 : c - '0' + 1) + prev.substring(i + 1));
            nt.add(prev.substring(0, i) + (c == '0' ? 9 : c - '0' - 1) + prev.substring(i + 1));
        }
        return nt;
    }
}
