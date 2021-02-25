package com.jetbrains.classic.topic.twoPointers.clashing;

import java.util.*;

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


    /*
        follow up: 4sum, 5sum, 6sum
        how to come up with a generalized approach to solve this problem
     */

    public List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        return kSum(nums, 0, 0, 3);
    }

    private List<List<Integer>> kSum(int[] nums, int target, int start, int k) {
        List<List<Integer>> res = new ArrayList<>();
        // base case
        if (start == nums.length || nums[start] * k > target || nums[nums.length - 1] * k < target) {
            return res;
        }

        if (k == 2) {
            return twoSum(nums, target, start);
        }

        // recursive case
        for (int i = start; i < nums.length; ++i) {
            if (i != start && nums[i] == nums[i - 1]) continue;
            List<List<Integer>> innerList = kSum(nums, target - nums[i], i + 1, k - 1);
            for (List<Integer> list : innerList) {
                res.add(new ArrayList(Arrays.asList(nums[i])));
                res.get(res.size() - 1).addAll(list);
            }
        }
        return res;
    }

    private List<List<Integer>> twoSum(int[] nums, int target, int start) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;

        int left = start, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum < target) {
                left++;
            } else if (sum > target) {
                right--;
            } else {
                res.add(Arrays.asList(nums[left], nums[right]));

                while (left + 1 < right && nums[left] == nums[left + 1]) left++;
                while (left < right - 1 && nums[right] == nums[right - 1]) right--;

                if (left < right) {
                    left++;
                    right--;
                }
            }
        }

        return res;
    }
}
