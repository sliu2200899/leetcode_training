package com.jetbrains.master;

public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int start = 0, end = n - 1;
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }

        if (nums[start] < target && nums[end] < target) {
            return end + 1;
        }

        if (nums[start] > target && nums[end] > target) {
            return start;
        }

        if (nums[start] < target && nums[end] > target) {
            return end;
        }

        return -1;
    }
}
