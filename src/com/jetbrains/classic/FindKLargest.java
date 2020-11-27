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
        preferred way to solve problem....
        time:  O(n)
     */
    public int findKthLargest3(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        return quick_select(nums, nums.length - k, 0, nums.length - 1);
    }

    // return N-k th smallest element in the array
    private int quick_select(int[] nums, int k, int start, int end) {
        if (start == end) {
            return nums[start];
        }
        // choose pivot_index
        Random random = new Random();
        int pivot_index = start + random.nextInt(end - start);

        pivot_index = partition(nums, start, end, pivot_index);

        if (k == pivot_index) return nums[pivot_index];
        else if (k < pivot_index) return quick_select(nums, k, start, pivot_index - 1);
        else return quick_select(nums, k, pivot_index + 1, end);
    }

    private int partition(int[] nums, int start, int end, int pivot_index) {

        // move pivot to end
        int pivot = nums[pivot_index];
        swap(nums, pivot_index, end);
        int endIndex = end;

        while (start <= end) {
            while (start <= end && nums[start] < pivot) {
                start++;
            }
            while (start <= end && nums[end] >= pivot) {
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

//    private void swap(int[] nums, int a, int b) {
//        int temp = nums[a];
//        nums[a] = nums[b];
//        nums[b] = temp;
//    }

}
