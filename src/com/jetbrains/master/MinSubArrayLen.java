package com.jetbrains.master;

public class MinSubArrayLen {
    public int minSubArrayLen(int s, int[] nums) {
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
