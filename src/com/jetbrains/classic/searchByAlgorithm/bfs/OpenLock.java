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

    /*
        bi-directional BFS

        key point is that use dead set to serve as visited set. So each time we process one element, we jsut put it in
                the dead set

        refer to:  https://www.geeksforgeeks.org/bidirectional-search/

        time: O(m^2 * n)
        space: O(m^2 * n)
     */
    public int openLock2(String[] deadends, String target) {
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();

        Set<String> deads = new HashSet<>(Arrays.asList(deadends));
        beginSet.add("0000");
        endSet.add(target);

        int level = 0;
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                Set<String> temp = endSet;
                endSet = beginSet;
                beginSet = temp;
            }

            Set<String> tmp = new HashSet<>();
            for (String cur : beginSet) {

                if (endSet.contains(cur)) return level;
                if (deads.contains(cur)) continue;
                deads.add(cur);

                char[] arr = cur.toCharArray();
                for (int i = 0; i < arr.length; ++i) {
                    char c = arr[i];
                    String s1 = cur.substring(0, i) + (c == '9' ? 0 : c - '0' + 1) + cur.substring(i + 1);
                    String s2 = cur.substring(0, i) + (c == '0' ? 9 : c - '0' - 1) + cur.substring(i + 1);

                    if (!deads.contains(s1)) {
                        tmp.add(s1);
                    }

                    if (!deads.contains(s2)) {
                        tmp.add(s2);
                    }
                }
            }
            level++;
            beginSet = tmp;
        }

        return -1;
    }
}
