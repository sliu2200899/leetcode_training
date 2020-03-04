package com.jetbrains.classic;

public class MaxSubArray {
    // use prefix sum array to solve the problem
    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int[] prefix = new int[nums.length + 1];
        // prefix sum
        prefix[0] = 0;
        for (int i = 1; i < nums.length + 1; ++i) {
            prefix[i] = prefix[i - 1] + nums[i - 1];
        }

        // the idea is to find the biggest difference between the sums
        int max = Integer.MIN_VALUE, min = 0;  // why min is initialized to 0 because min shouldn't be Integer.MIN_VALUE,
        // we care about the difference ...
        for (int i = 1; i <= nums.length; ++i) {
            max = Math.max(max, prefix[i] - min);
            min = Math.min(min, prefix[i]);
        }
        return max;
    }

    // DP problem...  (Kadane's algo)
    public static int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        // DP problem, and state function is f[i] = f[i-1] > 0 ? nums[i] + f[i-1] : nums[i]
        int maxSoFar = nums[0], maxEnding = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            maxEnding = Math.max(maxEnding + nums[i], nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEnding);
        }

        return maxSoFar;
    }
}
