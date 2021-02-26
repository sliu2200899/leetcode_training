package com.jetbrains.master;

import java.util.Arrays;

public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        /*
            analyze:
                sort(nums)
                i, j, k
                iterate the array
                    sum
                    compare sum and target
                    check the diff sum - target


            algo:

                sort(nums)
                for i
                    int first = nums[i]
                    int left = i + 1, right = nums.length - 1;
                    int diff = Integer.MAX_VALUE, res = 0;
                    while (left < right) {
                        int sum = ...
                        if (sum > target) {

                            right--;

                        } else if (sum < target) {

                            left++;

                        } else {
                            return target;
                        }

                        if (diff > abs(sum - target)) {
                            diff = abs(sum - target)
                            res = sum
                        }
                    }

                    return res;

            test:


        */

        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;

        Arrays.sort(nums);

        int diff = Integer.MAX_VALUE, res = 0;
        for (int i = 0; i < n - 2; ++i) {
            int first = nums[i];

            int left = i + 1, right = n - 1;
            while (left < right) {
                int sum = first + nums[left] + nums[right];
                if (sum > target) {
                    right--;
                }
                else if (sum < target) {
                    left++;
                }
                else {
                    return target;
                }
                if (diff > Math.abs(sum - target)) {
                    diff = Math.abs(sum - target);
                    res = sum;
                }
            }
        }
        return res;
    }
}
