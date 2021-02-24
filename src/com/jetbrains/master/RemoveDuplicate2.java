package com.jetbrains.master;

public class RemoveDuplicate2 {
    public int removeDuplicates(int[] nums) {
        /*
            similar to the remove duplicate 1,
            use two pointers i, and j where i is hte slow runner , and j is the fast runner.
            ..
                             j
            test:     [1,1,1,2,2,3]
                           i
            output: 1 1
            i: 0
            j: 0
            dup: 2

        */
        int i = 0, duplicate = 1;
        for (int j = 1; j < nums.length; ++j) {
            if (nums[i] != nums[j]) {
                nums[++i] = nums[j];
                duplicate = 1;
            } else if (duplicate < 2) {
                nums[++i] = nums[j];
                duplicate++;
            }
        }
        return i + 1;
    }
}
