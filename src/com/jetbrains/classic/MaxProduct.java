package com.jetbrains.classic;

public class MaxProduct {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        //Loop through the array, each time remember the max and min value for the previous product,
        //the most important thing is to update the max and min value: we have to compare among max * A[i],
        //min * A[i] as well as A[i], since this is product, a negative * negative could be positive.
        int maxSoFar = nums[0], minLocal = nums[0], maxLocal = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            int temp = maxLocal;
            maxLocal = Math.max(Math.max(maxLocal * nums[i], minLocal * nums[i]), nums[i]);
            minLocal = Math.min(Math.min(temp * nums[i], minLocal * nums[i]), nums[i]);
            maxSoFar = Math.max(maxSoFar, maxLocal);
        }
        return maxSoFar;
    }
}
