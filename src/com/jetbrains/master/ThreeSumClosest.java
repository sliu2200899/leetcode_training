package com.jetbrains.master;

import java.util.Arrays;

public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;

        // sort the array
        Arrays.sort(nums);

        // two pointers to solve the problem
        int diff = Integer.MAX_VALUE;
        int sumRst = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; ++i) {
            int j = i;
            int left = j + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[j] + nums[left] + nums[right];
                if (Math.abs(sum - target) < diff) {
                    diff = Math.abs(sum - target);
                    sumRst = sum;
                }
                if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return sumRst;
    }
}
