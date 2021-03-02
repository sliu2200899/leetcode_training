package com.jetbrains.classic.topic.twoPointers;

public class SquaresOfSortedArray {
    public int[] sortedSquares(int[] nums) {
        int[] res = new int[nums.length];

        int num = Integer.MAX_VALUE;
        int index = 0, i = 0;
        while (i < nums.length) {
            if (Math.abs(nums[i]) < num) {
                num = Math.abs(nums[i]);
                index = i;
            }
            i++;
        }

        i = 0;
        int left = index, right = index+1;
        while (left >= 0 || right <= nums.length - 1) {
            int lnum = left >= 0 ? Math.abs(nums[left]) : Integer.MAX_VALUE;
            int rnum = right <= nums.length - 1 ? Math.abs(nums[right]) : Integer.MAX_VALUE;
            if (lnum < rnum) {
                res[i] = lnum * lnum;
                left--;
            } else {
                res[i] = rnum * rnum;
                right++;
            }
            i++;
        }
        return res;
    }
}
