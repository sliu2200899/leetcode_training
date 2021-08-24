package com.jetbrains.zCompanyInterviewPrep.amazon;

import java.util.Arrays;

public class RoofCoversCars {
    // Finding the minimum length of the roof that covers K cars.... sliping widonw的题
    /*
         You are given an List of positions of cars as to where they are parked. You are also given an integer K.
         The cars needs to be covered with a roof. You have to find the minimum length of roof that takes to cover K cars.

         Input : 12,6,5,2      K = 3

         Explanation :  There are two possible roofs that can cover K cars. One that covers the car in 2,5,6 parking spots and
         another roof which covers 5,6,12 parking spots. The length of these two roofs are 5 and 8 respectively. Return 5
         since that's the roof with minimum length that covers K cars.

         Output : 5
     */
    public int minimumLenOfRoof(int[] nums, int K) {
        Arrays.sort(nums);

        int windowStart = 0, windowEnd = K - 1, ans = Integer.MAX_VALUE;
        for (;windowEnd < nums.length; windowStart++, windowEnd++) {
            ans = Math.min(ans, nums[windowEnd] - nums[windowStart] + 1);
        }

        return ans;
    }
}
