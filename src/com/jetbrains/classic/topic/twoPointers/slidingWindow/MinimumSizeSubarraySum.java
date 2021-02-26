package com.jetbrains.classic.topic.twoPointers.slidingWindow;

public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int s, int[] nums) {
        /*

            analyze:
                i, j where i faster runner and j slow runner
                iterate through the nums array, compute the cumulative sum during the process, and
                at the same time, we need to move j to get the minimal length of a subarray under the constraint.

            algo:
                j
                sum, len
                for i in nums
                    sum += nums[i]
                    while (sum >= target) {
                        sum -= nums[j];
                        j++;
                    }
                    len = max(len, i - j + 1)

            test:
                target = 7, nums = [2,3,1,2,4,3]
                                        j   i

                j   2
                sum 7
                len 4


        */

        if (nums == null || nums.length == 0) return 0;

        int index = 0, sum = 0, len = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            while (sum >= s) {
                len = Math.min(len, i - index + 1);
                sum -= nums[index++];
            }
        }

        return len == Integer.MAX_VALUE ? 0 : len;
    }

    /*
        time complexity is O(n)...
        space complexity is O(1) if input array is mutable...
     */
    public int minSubArrayLen2(int s, int[] nums) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            nums[i] = sum;
        }

        int min = nums.length + 1;
        for(int i = 0; i < nums.length; i++) {
            if (nums[i] < s) continue;
            int j = helper(nums[i], s, nums, 0, i);
            min = Math.min(min, i - j);
        }

        return min == nums.length + 1 ? 0 : min;
    }

    private int helper(int val, int s, int[] nums, int i, int j) {
        while(i < j) {
            int mid = i + (j - i + 1)/2;
            if (val - nums[mid] >= s) i = mid;
            else j = mid - 1;
        }
        return val - nums[i] >= s ? i : -1;
    }

}
