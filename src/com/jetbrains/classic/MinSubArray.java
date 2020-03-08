package com.jetbrains.classic;

public class MinSubArray {

    // prefix sum to solve the problem
    public static int minSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        // for each loop, we only need current prefix sum
        int prefixSum = 0;
        int globalMin = Integer.MAX_VALUE, localMax = 0;
        // prefix - max;  -> globalMin of difference
        // max -> localMax of prefix sum
        for (int num: nums) {
            prefixSum += num;
            globalMin = Math.min(globalMin, prefixSum - localMax);  //
            localMax = Math.max(localMax, prefixSum);
        }

        return globalMin;
    }


    // use DP algo to solve the problem.
    public static int minSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        // f[i] = f[i - 1] < 0 ? f[i - 1] + nums[i] : nums[i]   f[i] is the minimum sum that ends with current number...
        int globalMin = nums[0], localMax = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            localMax = Math.min(localMax, nums[i] + localMax);
            globalMin = Math.min(globalMin, localMax);
        }

        return globalMin;
    }
}
