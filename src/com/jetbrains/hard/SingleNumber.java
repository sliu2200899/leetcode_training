package com.jetbrains.hard;

import java.util.HashMap;
import java.util.Map;

public class SingleNumber {
    // using the hash map
    public int singleNumber(int[] nums) {
        // single number
        if (nums == null || nums.length == 0) return 0;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        for (int num : nums) {
            if (map.get(num) == 1) {
                return num;
            }
        }
        return -1;
    }


    // using bit operation to achieve space complexity O(1)
    public int singleNumber2(int[] nums) {
        // single number
        if (nums == null || nums.length == 0) return 0;

        int a = 0;
        for (int num : nums) {
            a ^= num;
        }
        return a;
    }
}
