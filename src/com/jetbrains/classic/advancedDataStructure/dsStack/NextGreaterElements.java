package com.jetbrains.classic.advancedDataStructure.dsStack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class NextGreaterElements {
    /*
        next greater elements 1
        approach:  hashmap

        time: O(m*n)   The whole numsnums array, of length nn needs to be scanned for all the mm elements of finalNumsfinalNums in the worst case
        space: O(m)  res array of size mm is used. A hashmap hashhash of size mm is used, where mm refers to the length of the findNumsfindNums array.
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; ++i) {
            map.put(nums2[i], i);
        }

        for (int j = 0; j < nums1.length; ++j) {
            int i;
            for (i = map.get(nums1[j]); i < nums2.length; ++i) {
                if (nums2[i] > nums1[j]) {
                    res[j] = nums2[i];
                    break;
                }
            }
            if (i == nums2.length) {
                res[j] = -1;
            }
        }

        return res;
    }

    /*
        monotonic stack:
        template basic

        time: O(n + m)
        space: O(n + m)
     */
    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();   // num as the key, next greater element as the value
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = nums2.length - 1; i >= 0; --i) {
            while (!stack.isEmpty() && stack.peek() <= nums2[i]) {
                stack.pop();
            }
            map.put(nums2[i], stack.isEmpty() ? -1: stack.peek());
            stack.push(nums2[i]);
        }

        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; ++i) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }
}
