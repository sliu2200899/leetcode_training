package com.jetbrains.classic;

import java.util.PriorityQueue;

public class FindKLargest {
    /*
        heap:
        init a heap "the smallest elements first"
        the time complexity of adding an element in a heap of size k is O(logk),
        we do it N times means O(N logk) time complexity for the algo.

        time: O(N logk)
        space: O(k)
     */

    public static int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;

        PriorityQueue<Integer> pqmin = new PriorityQueue<>();

        for (int num : nums) {
            pqmin.offer(num);
            if (pqmin.size() > k) {
                pqmin.poll();
            }
        }
        return pqmin.poll();
    }


    /*
        quick select

     */
    public int findKthLargest2(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;

        return quickSelect(nums, 0, nums.length - 1, k);
    }

    private int quickSelect(int[] nums, int start, int end, int k) {
        // base case
        if (start == end) {
            return nums[start];
        }

        int i = start, j = end;
        int mid = i + (j - i) / 2;
        int pivot = nums[mid];
        while (i <= j) {
            while (i <= j && nums[i] > pivot) i++;
            while (i <= j && nums[j] < pivot) j--;

            if (i <= j) {
                swap(nums, i, j);
                i++;
                j--;
            }
        }

        // start ... j(.)i ... end
        // start ... j
        if (start + k - 1 <= j) {
            return quickSelect(nums, start, j, k);
        }
        // i ... end
        if (start + k - 1 >= i) {
            return quickSelect(nums, i, end, k - (i - start));
        }
        // j(.)i
        return nums[j+1];
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
