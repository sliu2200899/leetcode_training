package com.jetbrains.classic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        // sanity check
        if (nums == null || nums.length == 0) return res;

        // Arrays.sort(nums);  // we can use two pointers to solve the problem..
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; ++i) {
            // avoid duplicate situation
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            int j = i;
            int left = j + 1, right = nums.length -1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    List<Integer> list = Arrays.asList(nums[i], nums[left], nums[right]);
                    res.add(list);

                    // avoid the duplicate triplets in the solution.
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    if (left < right) {
                        left++;
                        right--;
                    }

                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        return res;
    }
}
