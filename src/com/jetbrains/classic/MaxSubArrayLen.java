package com.jetbrains.classic;

import java.util.HashMap;

public class MaxSubArrayLen {
    // prefix array sum + two sum
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;

        // key: prefix sum
        // value: index
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        int sum = 0, size = 0;

        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                size = Math.max(size, i - map.get(sum - k) + 1);
            }

            if (!map.containsKey(sum)) map.put(sum, i + 1);
        }

        return size;
    }
}
