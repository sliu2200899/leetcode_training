package com.jetbrains.master;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        // time complexity:  O(n)
        // space complexity:  O(n)

        Map<Integer, Integer> map = new HashMap<>();  // store the infor like (target - nums[i]) as well as the current index
        for (int i = 0; i < nums.length; ++i) {
            if (map.containsKey(nums[i])) {
                return new int[]{map.get(nums[i]), i};
            } else {
                map.putIfAbsent(target - nums[i], i);
            }
        }

        return new int[]{-1, -1};
    }
}
