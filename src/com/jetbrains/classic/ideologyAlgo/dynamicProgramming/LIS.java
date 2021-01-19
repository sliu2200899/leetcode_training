package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

import java.util.Arrays;

public class LIS {
    /*
        LIS is a DP with  state: one dimension, time: two dimension
        time: O(n^2)
        space: O(n)
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        int max = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(max, dp[i]);
                }
            }
        }

        return max;
    }

    /*
        DP + greedy + binary search
        for two subsequences of the same length [1,2,8] and [3,4,5] which one is better?
        [3,4,5] is better since the last element 5 is smaller than 8 which has more chance to expand.

        dp[i] the smallest ending number of a subsequence that has length + 1

        In this approach, we scan the array from left to right. We also make use of a dp array initialized with all 0's.
        This dp array is meant to store the increasing subsequence formed by including the currently encountered element.
        While traversing the nums array, we keep on filling the dp array with the elements encountered so far.

        An important point to be noted is that for Binary Search, we consider only that portion of the dp array in which
        we have made the updates by inserting some elements at their correct positions(which remains always sorted).
        Thus, only the elements upto the ith index in the dp array can determine the position of the current element in it.
        Since, the element enters its correct position(ii) in an ascending order in the dp array, the subsequence formed so far in it is surely an increasing subsequence.
        Whenever this position index ii becomes equal to the length of the LIS formed so far(len), it means, we need to update the len as len = len + 1

        summary:  dp array does not result in longest increasing subsequence, but length of dp array will give you length of LIS.
     */
    public int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int[] dp = new int[nums.length];
        int len = 0;

        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i+1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }
}
