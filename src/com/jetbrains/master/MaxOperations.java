package com.jetbrains.master;

import java.util.HashMap;
import java.util.Map;

public class MaxOperations {
    public int maxOperations(int[] nums, int k) {
        int count = 0;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        for (int i = 0; i < nums.length; ++i) {
            if (map.containsKey(k - nums[i]) &&
                    map.get(k - nums[i]) > 0 &&
                    map.get(nums[i]) > 0) {
                map.put(k - nums[i], map.get(k - nums[i]) - 1);
                map.put(nums[i], map.get(nums[i]) - 1);
                count++;
            }
        }

        return count;
    }
}
