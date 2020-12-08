package com.jetbrains.master;

import java.util.*;

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
    Map<Integer, Integer> map = new HashMap<>();
    public int[] topKFrequent2(int[] nums, int k) {

        if (nums == null || nums.length == 0) return new int[0];

        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        // array of unique elements
        int n = map.size();
        int[] unique = new int[n];
        int i = 0;
        for (int num : map.keySet()) {
            unique[i] = num;
            i++;
        }

        // kth top frequent element is (n - k)th less freqent.
        // Do a partial sort, from less frequent to the most frequent, till (n - k)th less frequent element takes its place in a sorted array
        // all elemnet on the left are less frequent
        // all element on hte right are more frequent
        quickselect(unique, 0, n - 1, n - k);

        return Arrays.copyOfRange(unique, n - k, n);
    }

    private int quickselect(int[] nums, int start, int end, int k) {
        if (start == end) return nums[start];

        Random r = new Random();
        int pivotIndex = r.nextInt(end - start) + start;

        int partitionIndex = partition(nums, start, end, pivotIndex);

        if (k == partitionIndex) return nums[k];
        else if (k > partitionIndex) return quickselect(nums, partitionIndex + 1, end, k);
        else return quickselect(nums, start, partitionIndex - 1, k);
    }

    private int partition(int[] nums, int start, int end, int pivotIndex) {
        int pivot = map.get(nums[pivotIndex]);
        swap(nums, pivotIndex, end);

        int endIndex = end;

        while (start <= end) {
            while (start <= end && map.get(nums[start]) < pivot) {
                start++;
            }

            while (start <= end && map.get(nums[end]) >= pivot) {
                end--;
            }

            if (start <= end) {
                swap(nums, start, end);
                start++;
                end--;
            }
        }

        swap(nums, start, endIndex);
        return start;
    }

    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}
