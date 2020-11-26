package com.jetbrains.classic.searchByStructure.sequentialSearch;

// no specific target, to search for an element that meet some condition at least we can decide
// either search on the left or right depending on some condition.

public class FindMinimumValueRotatedArray {
    // preferred method to solve the problem
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return -1;

        int n = nums.length;
        int start = 0, end = n - 1;
        /*
        The minimum element must satisfy one of two conditions:
                1) If rotate, A[min] < A[min - 1];
                2) If not, A[0].
        Therefore, we can use binary search: check the middle element, if it is less than previous one, then it is minimum.
        If not, there are 2 conditions as well: If it is greater than both left and right element, then minimum element should be on its right,
            otherwise on its left.
        */
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (mid != 0 && nums[mid] < nums[mid - 1]) {
                return nums[mid];
            }

            if (nums[mid] >= nums[start] && nums[mid] >= nums[end]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return nums[start];
    }

    // just narrow the range to two elements and then choose the smaller one among them.
    public int findMinOld(int[] nums) {
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

    // if contains duplicates (preferred way to solve this problem)
    // narrow the search scope by moving the start and end pointers
    public int findMinDup(int[] nums) {
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
