package com.jetbrains.zCompanyInterviewPrep.amazon;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ReassignPriority {
    /*
        e.g [1,4,3,4,9], 按照大小重新给从1开始的priority，得到结果 [1,3,2,3,4]。注意如果有重复，
        不需要跳着给priority。我之前看到的原题是要跳着给，结果应该是 [1,3,2,3,5], 因为4出现2次，
        所以下一个9的priority是5, 而不是4. ‍‌‌‌‍‍‌‌‍‌‌‌‍‍‍‍‍‌‍我的解题approach是 [hide=99] counting sort
     */
    public static void main(String[] args) {
        int[] nums = {1,4,3,4,9};
        System.out.println(Arrays.toString(process(nums)));
    }

    // [1, 3, 2, 3, 4]
    public static int[] process(int[] nums) {
        int[] res = new int[nums.length];

        int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);

        int rank = 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < sorted.length; ++i) {
            if (i == 0 || sorted[i] != sorted[i-1]) {
                map.put(sorted[i], rank++);
            }
        }

        for (int i = 0; i < nums.length; ++i) {
            res[i] = map.get(nums[i]);
        }

        return res;
    }

    // [1, 3, 2, 3, 5]
    public static int[] process2(int[] nums) {
        int[] res = new int[nums.length];

        int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);

        int rank = 1, num = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < sorted.length; ++i) {
            if (i == 0 || sorted[i] != sorted[i-1]) {
                rank += num;
                map.put(sorted[i], rank++);
                num = 0;
            } else {
                num++;
            }
        }

        for (int i = 0; i < nums.length; ++i) {
            res[i] = map.get(nums[i]);
        }

        return res;
    }

}
