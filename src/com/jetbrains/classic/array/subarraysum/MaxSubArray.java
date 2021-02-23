package com.jetbrains.classic.array.subarraysum;

public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;

        // two options: prious sum and cur number as a single number in new array
        int global = nums[0], local = nums[0];
        for (int i = 1; i < n; ++i) {
            local = Math.max(local+nums[i], nums[i]);
            global = Math.max(global, local);
        }
        return global;
    }
}
