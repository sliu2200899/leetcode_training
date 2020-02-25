package com.jetbrains.master;

public class RemoveDuplicate {
    public int removeDuplicates(int[] nums) {
        // use two pointers in the same order
        if (nums == null || nums.length == 0) return 0;

        int index = 0;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[index] != nums[i]) {
                nums[++index] = nums[i];
            }
        }
        return index + 1;
    }
}
