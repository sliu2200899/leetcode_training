package com.jetbrains.classic;

import java.util.Arrays;

public class WiggleSort {
    // reorder it in place and make sure "nums[0] <= nums[1] >= nums[2] <= ..."
    // time compleity is O(nlogN) for the sorting,
    public static void sort(int[] arr) {
        Arrays.sort(arr);

        int len = arr.length;
        for (int i = 0; i < len - 1; i+=2) {
            swap(arr, i, i + 1);
        }
    }

    // time complexity is O(n)
    public static void sort2(int[] nums) {
        if (nums == null || nums.length == 0) return;

        int len = nums.length;

        for (int i = 1; i < nums.length; ++i) {
            if ((i%2 == 1 && nums[i] < nums[i - 1]) || (i%2 == 0 && nums[i] > nums[i - 1])) {
                swap(nums, i, i - 1);
            }
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
