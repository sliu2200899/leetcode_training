package com.jetbrains.classic.sorting;

public class SortColor {
    /*
        analyze:
            nums = [2,0,2,1,1,0]
            [0,0,1,1,2,2]

            3 way partitioning, split the nums array into three parts
            In the first part, all of the elements are red, in the second part, all of the elements are white and the third part blue.
            we can use two pointers to check these two keys. lowerkey and higherkey


        algo:
            left, right
            cur
            key
            while (cur <= right)
                if nums[cur] < key:
                    swap(nums, cur++, left++)
                else if nums[cur] > key:
                    swap(nums, cur, right--)
                else
                    cur++;

        time: O(n)
        space: O(1)
    */
    public static void sortColors(int[] nums) {
        int left = 0, right = nums.length - 1;
        int cur = 0, key = 1;     // you need to know sort values

        while (cur <= right) {
            if (nums[cur] < key) {
                swap(nums, cur++, left++);
            } else if (nums[cur] > key) {
                swap(nums, cur, right--);
            } else {
                cur++;
            }
        }
    }

    private static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }


    /*
        follow up:  k way partitioning
     */
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

    /*
        follow up:  what if the array contains some irrelevant numbers
     */
    public static void sortColors3(int[] nums) {
        if (nums == null || nums.length == 0) return;

        int n = nums.length;
        int start = 0, end = n - 1, cur = start;

        while (cur <= end) {
            // just before the swap, we need to check hte number at low index is the relevant number...
            if (nums[cur] == 0) {
                // after the while loop, low will points at 1, we just swap 1 and nums[cur]
                while (nums[start] != nums[cur] &&
                        nums[start] != 0 &&
                        nums[start] != 1 &&
                        nums[start] != 2) {
                    start++;
                }
                swap(nums, cur++, start++);
            } else if (nums[cur] == 2) {
                while (nums[end] != nums[cur] &&
                        nums[end] != 0 &&
                        nums[end] != 1 &&
                        nums[end] != 2) {
                    end--;
                }
                swap(nums, cur, end--);
            } else {
                cur++;
            }
        }
    }
}
