package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MaxSubArray {
    // use prefix sum array to solve the problem
    // greedy algo: pick the locally optimal move at each step, and that will lead to the globally optimal solution.
    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int[] prefix = new int[nums.length + 1];
        // prefix sum
        for (int i = 1; i < nums.length + 1; ++i) {
            prefix[i] = prefix[i - 1] + nums[i - 1];
        }

        // the idea is to find the biggest difference between the sums
        int max = Integer.MIN_VALUE, min = prefix[0];  // why min is initialized to 0 because min shouldn't be Integer.MIN_VALUE,
        // we care about the difference ...
        for (int i = 1; i <= nums.length; ++i) {
            max = Math.max(max, prefix[i] - min);
            min = Math.min(min, prefix[i]);
        }
        return max;
    }

    // DP problem
    public static int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        // DP problem, and state function is f[i] = f[i-1] > 0 ? nums[i] + f[i-1] : nums[i]
        // where f[i] is the max sum of the subarray ends with current number
        // will check the f[i-1] first, if it is < 0, we wanna start by itself instead adding the previous subarray
        int ans = nums[0], sum = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            sum = Math.max(sum + nums[i], nums[i]);
            if (sum > ans) ans = sum;
        }

        return ans;
        /*
            time: O(n)
            space: O(1)
         */
    }

    // Divide and conquer
    // time complexity O(nlogn)    proof of the merge sort...
    // space complexity O(logn)
    public int maxSubArray3(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        return helper(nums, 0, nums.length - 1);
    }

    private int helper(int[] nums, int start, int end) {
        if (start == end) return nums[start];

        int mid = start + (end - start) / 2;

        int leftSum = helper(nums, start, mid);
        int rightSum = helper(nums, mid + 1, end);
        int crossSum = crossSumHelper(nums, start, end, mid);

        return Math.max(Math.max(leftSum, rightSum), crossSum);
    }

    private int crossSumHelper(int[] nums, int left, int right, int mid) {
        if (left == right) return nums[left];

        int leftSubsum = Integer.MIN_VALUE;
        int currSum = 0;
        for (int i = mid; i >= left; --i) {
            currSum += nums[i];
            leftSubsum = Math.max(leftSubsum, currSum);
        }

        int rightSubsum = Integer.MIN_VALUE;
        currSum = 0;
        for (int i = mid + 1; i <= right; ++i) {
            currSum += nums[i];
            rightSubsum = Math.max(rightSubsum, currSum);
        }

        return leftSubsum + rightSubsum;
    }



    // follow up: how to print all the elements of subarray with largest sum
    public static List<Integer> maxSubArray4(int[] nums) {
        List<Integer> list = new LinkedList<>();
        if (nums == null || nums.length == 0) return list;

        int ans = nums[0], index = 0, sum = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            sum = Math.max(sum + nums[i], nums[i]);
            if (sum > ans) {
                index = i;
                ans = sum;
            }
        }

        while (index > 0 && ans != 0) {
            list.add(0, nums[index]);
            ans -= nums[index];
            index--;
        }

        return list;
    }
}
