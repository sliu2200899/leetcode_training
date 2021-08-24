package com.jetbrains.zCompanyInterviewPrep.amazon;

import java.util.Arrays;

public class MaximumAreaOfCakeAfterCuts {
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        // don't forget to modulo this special number as the result can be huge
        return (int) (this.max(h, horizontalCuts) * this.max(w, verticalCuts) % 1000000007);
    }

    private long max(int len, int[] nums) {
        Arrays.sort(nums);

        // initial cut
        long max = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            max = Math.max(max, nums[i] - nums[i-1]);
        }

        // finial cut
        max = Math.max(max, len - nums[nums.length - 1]);

        return max;
    }
}
