package com.jetbrains.classic;

public class SearchRotatedArray {

    /*
        step1: check which part of subarray does the mid element stay,  nums[mid] > nums[end]
        step2: divided into three ranges for the target, discuss the three situations one by one.    nums[mid] > target && target > nums[start]
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int n = nums.length;
        int start = 0, end = n - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > nums[end]) {
                if (nums[mid] > target && target >= nums[start]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else {
                if (nums[mid] < target && target <= nums[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }

        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }

        return -1;
    }

    /*
    follow up, find target value in sorted array including duplicates
     */

    public boolean search2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int n = nums.length;
        int start = 0, end = n - 1;
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[mid] > nums[end]) {
                if (nums[mid] > target && target >= nums[start]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else if (nums[mid] < nums[end]) {
                if (nums[mid] < target && target <= nums[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            } else {
                end--;
            }
        }

        if (nums[start] == target || nums[end] == target) {
            return true;
        }

        return false;
    }
}
