package com.jetbrains.master;

public class FirstAndLastPositionOfEleSortedArray {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        // first element of the target
        int n = nums.length;
        int start = 0, end = n - 1;
        while( start + 1 < end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                end = mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        int rangeStart = 0, rangeEnd = n - 1;
        if (nums[start] == target) {
            rangeStart = start;
        }
        else if (nums[end] == target) {
            rangeStart = end;
        }

        if (nums[start] != target && nums[end] != target) {
            return new int[]{-1, -1};
        }

        // last element of the target
        start = 0;
        end = n - 1;
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                start = mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (nums[end] == target) {
            rangeEnd = end;
        } else if (nums[start] == target) {
            rangeEnd = start;
        }

        return new int[]{rangeStart, rangeEnd};
    }
}
