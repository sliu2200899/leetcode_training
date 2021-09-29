package com.jetbrains.zCompanyInterviewPrep.linkedIn;

public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int max_so_far = nums[0];
        int min_so_far = nums[0];
        int result = max_so_far;

        for (int i = 1; i < nums.length; ++i) {
            int curr = nums[i];

            // the accumulated product has been really bad (even compared to the current number).
            // This can happen when the current number has a preceding zero (e.g. [0,4]) or is preceded by a single negative number (e.g. [-3,5]).
            // that is the reason why we need Math.max(curr, ....)

            // This value will be picked if the accumulated product has been steadily increasing (all positive numbers).
            // that is the reason why we need Math.max(max_so_far * curr, min_so_far * curr);
            int temp_max = Math.max(curr, Math.max(max_so_far * curr, min_so_far * curr));
            int temp_min = Math.min(curr, Math.min(max_so_far * curr, min_so_far * curr));

            max_so_far = temp_max;
            min_so_far = temp_min;

            result = Math.max(max_so_far, result);
        }

        return result;
    }
}
