package com.jetbrains.classic;

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

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 0, 4);
    }
    public List<List<Integer>> kSum(int[] nums, int target, int start, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (start == nums.length || nums[start] * k > target || target > nums[nums.length - 1] * k)
            return res;
        if (k == 2)
            return twoSum(nums, target, start);
        for (int i = start; i < nums.length; ++i)
            if (i == start || nums[i - 1] != nums[i]) {}
//                for (var set : kSum(nums, target - nums[i], i + 1, k - 1)) {    //java 10 var
//                    res.add(new ArrayList<>(Arrays.asList(nums[i])));
//                    res.get(res.size() - 1).addAll(set);
//                }
        return res;
    }
    public List<List<Integer>> twoSum(int[] nums, int target, int start) {
        List<List<Integer>> res = new ArrayList<>();
        Set<Integer> s = new HashSet<>();
        for (int i = start; i < nums.length; ++i) {
            if (res.isEmpty() || res.get(res.size() - 1).get(1) != nums[i])
                if (s.contains(target - nums[i]))
                    res.add(Arrays.asList(target - nums[i], nums[i]));
            s.add(nums[i]);
        }
        return res;
    }
}
