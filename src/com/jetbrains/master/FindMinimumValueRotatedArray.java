package com.jetbrains.master;

public class FindMinimumValueRotatedArray {
    // just narrow the range to two elements and then choose the smaller one among them.
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int n = nums.length;
        int start = 0, end = n - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == nums[end]) {
                end = mid;
            } else if (nums[mid] < nums[end]) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (nums[start] > nums[end]) {
            return nums[end];
        }
        return nums[start];
    }

    // if contains duplicates
    public int findMin2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int n = nums.length;
        int start = 0, end = n - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == nums[end]) {
                end--;
            } else if (nums[mid] < nums[end]) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (nums[start] > nums[end]) {
            return nums[end];
        }
        return nums[start];
    }
}
