package com.jetbrains.master;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFreqElements {
    // time complexity:  O(Nlogk)
    // space complexity:  O(N + k)
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        // 1. init heap 'the less frequent element first'
        PriorityQueue<Integer> minPQ = new PriorityQueue<>((n1, n2) -> map.get(n1) - map.get(n2));

        // 2. keep k top frequent elements in the heap
        // O(N log k) < O(N log N) time
        for (int n : map.keySet()) {
            minPQ.offer(n);
            if (minPQ.size() > k) {
                minPQ.poll();
            }
        }

        // 3. build an output array
        // O(k log k) time
        int[] res = new int[k];
        for (int i = k - 1; i >= 0; --i) {
            res[i] = minPQ.poll();
        }

        return res;
    }

    /*
        method 2: quick select

     */
}
