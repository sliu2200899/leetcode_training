package com.jetbrains.mj;

import java.util.ArrayList;

public class SongDuration {
    /*
    You are given a list of songs where the ith song has a duration of time[i] seconds. Return the number of pairs
    of songs for which their total duration in seconds is divisible by 60. Formally, we want the number of indices i, j
    such that i < j with (time[i] + time[j]) % 60 == 0.

    Input: time = [30,20,150,100,40]
    Output: 3
     */

    int count = 0;
    public int process(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        helper(nums, 0, new ArrayList<>());

        return count;
    }

    private void helper(int[] nums, int start, ArrayList<Integer> list) {
        if (list.size() > 2) return;
        if (list.size() == 2) {
            int sum = 0;
            for (int i : list) {
                sum += i;
            }
            if (sum % 60 == 0) count++;
        }

        for (int i = start; i < nums.length; ++i) {
            list.add(nums[i]);
            helper(nums, i + 1, list);
            list.remove(list.size() - 1);
        }
    }
}
