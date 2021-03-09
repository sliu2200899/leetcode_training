package com.jetbrains.classic.topic.array.subarraysum;

import java.util.ArrayList;

public class ContinuousSubarraySum {

    /*
        similar to teh max subarray
        the only diff is to track teh start and edn index
     */
    public ArrayList<Integer> continuousSubArraySum(int[] nums) {
        ArrayList<Integer> result = new ArrayList<>();
        result.add(0);
        result.add(0);

        int n = nums.length;

        int start = 0, end = 0;
        int sum = 0;
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; ++i) {
            if (sum < 0) {
                sum = nums[i];
                start = end = i;
            } else {
                sum += nums[i];
                end = i;
            }

            if (sum > res) {
                res = sum;
                result.set(0, start);
                result.set(1, end);
            }
        }
        return result;
    }
}
