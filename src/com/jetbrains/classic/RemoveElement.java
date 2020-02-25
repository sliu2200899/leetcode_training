package com.jetbrains.classic;

public class RemoveElement {
    // similar to remove duplicates ... but the for loop need to start from 0 index.
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) return 0;

        int index = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != val) {
                nums[index++] = nums[i];
            }
        }

        return index;
    }

    // another solution is more suitable to the situation like the elements to remove are rare..
    public int removeElement2(int[] nums, int val) {
        if (nums == null || nums.length == 0) return 0;

        int index = nums.length - 1, cur = 0;
        while (cur <= index) {
            if (nums[cur] == val) {
                nums[cur] = nums[index--];
            }
            else {
                cur++;
            }
        }
        return cur;
    }
}
