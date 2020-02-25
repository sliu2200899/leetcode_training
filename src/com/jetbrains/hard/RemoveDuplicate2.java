package com.jetbrains.hard;

public class RemoveDuplicate2 {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;

        int count = 1, index = 0;
        for (int i = 1; i < n; ++i) {
            if (nums[i] != nums[index]) {
                nums[++index] = nums[i];
                count = 1;
            } else if (count < 2) {
                nums[++index] = nums[i];
                count++;
            }
        }
        return index+1;
    }
}
