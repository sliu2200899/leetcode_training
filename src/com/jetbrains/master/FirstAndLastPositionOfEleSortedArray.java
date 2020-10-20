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

    /*
        this approach:   find first and last elements that equal to the target
     */
    public int[] searchRange2(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};

        int[] res = new int[]{-1, -1};
        int n = nums.length;

        int start = 0, end = n - 1;
        while (start <= end) {
            int mid = start + ((end - start) >> 1);
            if (nums[mid] > target) {
                end = mid - 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                if ((mid == 0) || (nums[mid - 1] != target)) {
                    res[0] = mid;
                    break;
                }
                else end = mid - 1;
            }
        }

        if (res[0] == -1) return res;

        start = 0;
        end = nums.length - 1;
        while (start <= end) {
            int mid = start + ((end - start) >> 1);
            if (nums[mid] > target) {
                end = mid - 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                if ((mid == nums.length - 1) || (nums[mid + 1] != target)) {
                    res[1] = mid;
                    break;
                }
                else start = mid + 1;
            }
        }

        return res;
    }
}
