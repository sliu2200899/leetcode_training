package com.jetbrains;

import com.jetbrains.master.TwoSum;

public class Main {

    public static void main(String[] args) {
        // two sum
        TwoSum obj = new TwoSum();

        int[] nums = {1, 2, 6, 10, 15};
        int target = 8;

        int[] arr = obj.twoSum(nums, target);
        for (int ar : arr) {
            System.out.println(ar);
        }
    }
}
