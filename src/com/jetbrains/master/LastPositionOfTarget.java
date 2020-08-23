package com.jetbrains.master;

public class LastPositionOfTarget {

    /*
    Find the last position of a target number in a sorted array. Return -1 if target does not exist.
    eg.
        Input: nums = [1,2,2,4,5,5], target = 2
        Output: 2
     */
    public int lastPosition(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int n = nums.length;
        int start = 0, end = n - 1;

        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if(nums[mid] == target) {
                start = mid;
            }
            else if (nums[mid] < target) {
                start = mid;
            }
            else {
                end = mid;
            }
        }

        if (nums[end] == target) {
            return end;
        }

        if (nums[start] == target) {
            return start;
        }

        return -1;
    }

    /*
      similar problem like first element of a target number
     */
}
