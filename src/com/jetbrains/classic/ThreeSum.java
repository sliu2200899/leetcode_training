package com.jetbrains.classic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;

        Arrays.sort(nums);   // in order to use two pointers, we need to have array sorted in advance.

        for (int i = 0; i < nums.length - 2; ++i) {
            // avoid duplicate situation
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            int first = nums[i];

            int left = i + 1, right = nums.length - 1;
            while (left < right) {      // note that two pointers, left < right not <=
                int sum = nums[left] + nums[right] + first;
                if (sum == 0) {
                    res.add(Arrays.asList(first, nums[left], nums[right]));

                    // avoid the duplicate triplets in the solution.
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    if (left < right) {
                        left++;
                        right--;
                    }
                }
                else if (sum < 0) {
                    left++;
                }
                else {
                    right--;
                }
            }
        }
        return res;
    }


}
