package com.jetbrains.master;

import javafx.beans.binding.When;

public class RemoveDuplicate {
    public int removeDuplicates(int[] nums) {
        // use two pointers in the same order
        /*
            Since the array is already sorted, we can keep two pointers i and j, where i is the slow-runner while j is the fast-runner. As long as nums[i] = nums[j], we increment j to skip the duplicate.
            When we encounter nums[j] = nums[i], the duplicate run has ended so we must copy its value to nums[i + 1]. ii is then incremented and we repeat the same process again until j reaches the end of array.

            note that, fast runner should be one position ahead of slow runner.
         */
        if (nums == null || nums.length == 0) return 0;

        int i = 0;
        for (int j = 1; j < nums.length; ++j) {
            if (nums[i] != nums[j]) {
                nums[++i] = nums[j];
            }
        }
        return i + 1;
    }
}
