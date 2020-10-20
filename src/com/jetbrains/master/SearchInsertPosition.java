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

    /*
        the problem is that we need to find the first number that is larger or equal to the target
     */

    public int searchInsert2(int[] nums, int target) {
        if(nums == null || nums.length == 0) return 0;

        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = start + ((end - start) >> 1);
            if (nums[mid] >= target) {
                if ((mid == 0) || (nums[mid - 1] < target)) return mid;
                else end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }
        // at this point, we cannot find any number larger or equal to target, so we return the length of the array
        return nums.length;
    }
}
