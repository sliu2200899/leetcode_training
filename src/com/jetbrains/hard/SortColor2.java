package com.jetbrains.hard;

public class SortColor2 {
    // k way partitioning
    public static void sortColors2(int[] colors, int k) {
        // write your code here
        if (colors == null || colors.length == 0) return;

        int left = 0, right = colors.length - 1, i = 0, lowKey = 1, highKey = k;
        while (lowKey < highKey) {
            i = left;
            while (i <= right) {
                if (colors[i] == lowKey) {
                    swap(colors, left++, i++);
                } else if (colors[i] == highKey) {
                    swap(colors, i, right--);
                } else {
                    i++;
                }
            }
            lowKey++;
            highKey--;
        }
    }

    private static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
