package com.jetbrains.classic.searchByStructure.sequentialSearch;

public class SearchRotatedArray {

    /*
        step1: check which part of subarray does the mid element stay,  nums[mid] > nums[end]
        step2: divided into three ranges for the target, discuss the three situations one by one.    nums[mid] > target && target > nums[start]
     */
    // preferred way
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        int n = nums.length;

        int start = 0, end = n - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] > nums[end]) {
                if (nums[mid] > target && target >= nums[start]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        return -1;
    }

    /*
    follow up, find target value in sorted array including duplicates
     */
    // preferred way
    public boolean search2(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;

        int n = nums.length;
        int start = 0, end = n - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return true;
            }

            if (nums[mid] > nums[end]) {
                if (nums[mid] > target && target >= nums[start]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else if (nums[mid] < nums[end]) {
                if (nums[mid] < target && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else {
                end--;
            }
        }

        return false;
    }
}
