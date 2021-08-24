package com.jetbrains.zCompanyInterviewPrep.amazon;

import java.util.Arrays;

// Given an array of integers, determine the number of ways the entire array be split into two non-empty subarrays, left and right,
// such that the sum of elements in the left subarray is greater than the sum of elements in the right subarray.

// Example
// arr =      [10, 4, -8, 7]
// presum     [0, 10, 14, 6, 13]
//                 i
//      left = 10,  right 3
// There are three ways to split it into two non-empty subarrays:
// [10] and [4, -8, 7], left sum = 10, right sum = 3
// [10, 4] and [-8, 7], left sum = 10 + 4 = 14, right sum = -8 + 7 = -1
// [10, 4, -8] and [7], left sum = 6, right sum = 7
// The first two satisfy the condition that left sum > right sum, so the return value should be 2.
public class SplitIntoTwo {
    public static void main(String[] args) {
        int[] input = {10, 4, -8, 7};

        System.out.println(wayOfSplit(input));
    }

    public static int wayOfSplit(int[] data) {
        if (data == null || data.length == 0) return 0;

        int sum = 0, left = data[0], res = 0;

        for (int i = 0; i < data.length; ++i) {
            sum += data[i];
        }

        for (int i = 1; i < data.length; ++i) {
            if (left > sum - left) res++;
            left += data[i];
        }

        return res;
    }
}