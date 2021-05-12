package com.jetbrains.classic.advancedDataStructure.unionFind;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class SmallestStringWithSwaps {
    /*
        graph + unionfind

        This implies, we can build connected components where each component is a list of indices that can be exchanged with any of them.
        In Union find terms, we simply iterate through each pair, and do a union on the indices in the pair.
        At the end of the union of all the pairs, we have built connected component of indices that can be exchanged with each other.

        Then we build a sorted list of characters for every connected component.

        The final step is, we iterate through all the indices, and for each index we locate its component id and find the sorted list corresponding
        to that component and grab the next lowest character from that list.

        time: O(nlogn)
     */
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        int[] root = new int[n];

        for (int i = 0; i < n; ++i) {
            root[i] = i;
        }
        int[] rank = new int[n];


        for (List<Integer> p : pairs) {
            union(root, rank, p.get(0), p.get(1));
        }

        // maintain a map to store head of connected component and pq
        Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            int head = findRoot(root, i);
            root[i] = head;
            if (!map.containsKey(head)) {
                map.put(head, new PriorityQueue<>());
            }

            map.get(head).add(s.charAt(i));
        }

        // construct the output string based on the map
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            sb.append(map.get(root[i]).poll());
        }

        return sb.toString();
    }

    private int findRoot(int[] root, int i) {
        if (root[i] == i) {
            return i;
        }

        return root[i] = findRoot(root, root[i]);
    }

    private void union(int[] root, int[] rank, int src, int dst) {
        int srcRoot = findRoot(root, src);
        int dstRoot = findRoot(root, dst);

        if (srcRoot != dstRoot) {
            if (rank[srcRoot] > rank[dstRoot]) {
                root[dstRoot] = srcRoot;
            } else if (rank[srcRoot] < rank[dstRoot]) {
                root[srcRoot] = dstRoot;
            }
            else {
                root[srcRoot] = dstRoot;
                rank[dstRoot]++;
            }
        }
    }
}
