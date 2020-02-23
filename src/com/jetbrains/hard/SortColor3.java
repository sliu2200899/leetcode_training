package com.jetbrains.hard;

public class SortColor3 {

    //what if the array contains some irrelevant numbers
    public static void sort(int[] nums) {
        if (nums == null || nums.length == 0) return;

        int n = nums.length;
        int low = 0, high = n - 1, cur = low;

        while (cur <= high) {
            // just before the swap, we need to check hte number at low index is the relevant number...
            if (nums[cur] == 0) {
                while (nums[low] != nums[cur] &&
                        nums[low] != 0 &&
                        nums[low] != 1 &&
                        nums[low] != 2) {
                    low++;
                }
                swap(nums, cur++, low++);
            } else if (nums[cur] == 2) {
                while (nums[high] != nums[cur] &&
                        nums[high] != 0 &&
                        nums[high] != 1 &&
                        nums[high] != 2) {
                    high--;
                }
                swap(nums, cur, high--);
            } else {
                cur++;
            }
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
