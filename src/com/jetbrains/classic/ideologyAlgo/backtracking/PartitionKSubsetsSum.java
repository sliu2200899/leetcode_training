package com.jetbrains.classic.ideologyAlgo.backtracking;

import java.util.Arrays;

public class PartitionKSubsetsSum {
    /*
        backtracking:
        use an array to keep track of the sum of each subsets

        time: O(k^n) in the worst case
        space: O(N) the space used by recursive calls to search in our call stack
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null || nums.length == 0) return false;

        int n = nums.length;

        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % k != 0) return false;

        int subSum = sum / k;

        Arrays.sort(nums);
        int beginIndex = nums.length - 1;
        if (nums[beginIndex] > subSum) {
            return false;
        }

        while (beginIndex >= 0 && nums[beginIndex] == subSum) {
            beginIndex--;
            k--;
        }

        return recursion(new int[k], nums, beginIndex, subSum);
    }

    private boolean recursion(int[] subsets, int[] nums, int beginIndex, int target) {
        if (beginIndex < 0) {
            return true;
        }

        int selected = nums[beginIndex];
        for (int i = 0; i < subsets.length; ++i) {
            if (subsets[i] + selected <= target) {
                subsets[i] += selected;
                if (recursion(subsets, nums, beginIndex-1, target)) {
                    return true;
                }

                subsets[i] -= selected;
            }
        }

        return false;
    }
}
