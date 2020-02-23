package com.jetbrains.classic;

import java.util.Arrays;

public class SortColor {
    public static void sortColors(int[] nums) {
        int left = 0, right = nums.length - 1;
        int cur = 0, key = 1;

        while (cur <= right) {
            if (nums[cur] < key) {
                swap(nums, cur++, left++);
            } else if (nums[cur] > key) {
                swap(nums, cur, right--);
            } else {
                cur++;
            }
        }
    }

    private static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
