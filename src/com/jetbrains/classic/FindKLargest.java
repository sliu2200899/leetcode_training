package com.jetbrains.classic;

import java.util.PriorityQueue;
import java.util.Random;

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
        time complexity: O(n)
        O(NlogN) time complexity. Here there is no need to deal with both parts since now one knows in which part
        to search for N - kth smallest element, and that reduces average time complexity to O(N).
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

    /*
        another quick select
     */
    public int partition3(int[] nums, int start, int end, int pivot_index) {

        // 1. move pivot to end
        int pivot = nums[pivot_index];
        swap(nums, pivot_index, end);

        // 2. move all smaller elements to the left
        int store_index = start;
        for (int i = start; i <= end; i++) {
            if (nums[i] < pivot) {
                swap(nums, store_index, i);
                store_index++;
            }
        }

        // 3. move pivot to its final place
        swap(nums, store_index, end);

        return store_index;
    }

    public int quickselect3(int[] nums, int start, int end, int k_smallest) {
    /*
        Returns the k-th smallest element of list within left..right.
    */

        if (start == end) // If the list contains only one element,
            return nums[start];  // return that element

        // select a random pivot_index
        Random random_num = new Random();
        int pivot_index = start + random_num.nextInt(end - start);

        pivot_index = partition3(nums, start, end, pivot_index);

        // the pivot is on (N - k)th smallest position
        if (k_smallest == pivot_index)
            return nums[k_smallest];
            // go left side
        else if (k_smallest < pivot_index)
            return quickselect3(nums, start, pivot_index - 1, k_smallest);
        // go right side
        return quickselect3(nums, pivot_index + 1, end, k_smallest);
    }

    public int findKthLargest3(int[] nums, int k) {
        int size = nums.length;
        // kth largest is (N - k)th smallest
        return quickselect3(nums, 0, size - 1, size - k);
    }

}
