package com.jetbrains.classic.sorting;

import java.util.Arrays;

public class MaximumGap {
    /*
        164
        Given an integer array nums, return the maximum difference between two successive elements in its sorted form. If the array contains less than two elements, return 0.
        example:
            Input: nums = [3,6,9,1]
            Output: 3
            Explanation: The sorted form of the array is [1,3,6,9], either (3,6) or (6,9) has the maximum difference 3.


     */
    /*
        method 1: basic sort

     */
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }

        Arrays.sort(nums);

        int gap = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (i != 0) {
                gap = Math.max(gap, nums[i] - nums[i-1]);
            }
        }

        return gap;
    }

    /*
        method 2: radix sort

     */


}
