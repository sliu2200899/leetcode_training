package com.jetbrains.classic.sorting;

// selection algorithm to find the k-th smallest element in an unordered list

import java.util.Random;

/*
Input: arr[] = {7, 10, 4, 3, 20, 15}
           k = 3
Output: 7

Input: arr[] = {7, 10, 4, 3, 20, 15}
           k = 4
Output: 10

 */
// leetcode 215
/*

The difference is, instead of recurring for both sides (after finding pivot),
it recurs only for the part that contains the k-th smallest element.
The logic is simple, if index of partitioned element is more than k, then we recur for left part.
If index is same as k, we have found the k-th smallest element and we return.
If index is less than k, then we recur for right part. This reduces the expected complexity
from O(n log n) to O(n), with a worst case of O(n^2).

 */
public class QuickSelect {
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

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
