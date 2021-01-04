package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

public class RangSumQuery {
    int[] arr;
    /*

    immutable

    Notice in the code above we inserted a dummy 0 as the first element in the sum array.
    This trick saves us from an extra conditional check in sumRange function

    time: O(1) time per query, O(n) time pre-computation
    space: O(n)
     */
    public void NumArray(int[] nums) {
        int n = nums.length;
        arr = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            arr[i] = arr[i - 1] + nums[i - 1];
        }
    }

    public int sumRange(int i, int j) {
        return arr[j + 1] - arr[i];
    }
}
