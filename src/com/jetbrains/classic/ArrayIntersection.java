package com.jetbrains.classic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ArrayIntersection {
    /*
        approach 1: two sets
        time : O(n + m)
        space : O(n + m)
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        for (Integer n : nums1) set1.add(n);
        HashSet<Integer> set2 = new HashSet<>();
        for (Integer n : nums2) set2.add(n);

        if (set1.size() < set2.size()) return set_intersection(set1, set2);
        else return set_intersection(set2, set1);
    }

    private int[] set_intersection(HashSet<Integer> set1, HashSet<Integer> set2) {
        int[] output = new int[set1.size()];
        int idx = 0;

        // set1 is the smaller set, while set2 is the larger set
        for (Integer s : set1) {
            if (set2.contains(s)) output[idx++] = s;
        }

        return Arrays.copyOf(output, idx);
    }

    /*
        approach 2: Built-in Set Intersection
     */
    public int[] intersection2(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        for (Integer n : nums1) set1.add(n);
        HashSet<Integer> set2 = new HashSet<>();
        for (Integer n : nums2) set2.add(n);

        set1.retainAll(set2);

        int[] output = new int[set1.size()];
        int idx = 0;
        for (int s : set1) output[idx++] = s;
        return output;
    }

    /*
        follow up: how to solve this in O(n) time and O(1) space complexity? we assume nums1 and nums2 are sorted
        n is the max(nums1.length, nums2.length)
     */
    public int[] intersection3(int[] nums1, int[] nums2) {
        // inputs are sorted as assumed
        Arrays.sort(nums1);  // we can skip ...
        Arrays.sort(nums2);  // we can skip ...

        // output is not considered in space
        List<Integer> list = new ArrayList<>();
        int j = 0;
        int i = 0;
        while (i < nums2.length && j < nums1.length) {
            // skip duplicates
            while (i > 0 && i < nums2.length && nums2[i] == nums2[i - 1]) i++;
            while (j > 0 && j < nums1.length && nums1[j] == nums1[j - 1]) j++;

            if (i < nums2.length && j < nums1.length) {
                int n = nums2[i];
                int m = nums1[j];

                if (m == n) {
                    list.add(n);
                    i++;
                    j++;
                }
                else if (m > n) {
                    i++;
                }
                else {
                    j++;
                }
            }
        }
        return list.stream().mapToInt(x -> x).toArray();
    }


}
