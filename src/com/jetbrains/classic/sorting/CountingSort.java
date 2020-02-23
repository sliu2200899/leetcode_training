package com.jetbrains.classic.sorting;

import java.util.Arrays;

public class CountingSort {
    public static void sort(int arr[], int k) {
        if (arr == null || arr.length == 0) return;

        int len = arr.length;

        int[] countArr = new int[k];  // length is the range of the input array
        Arrays.fill(countArr, 0);

        // initialize count array as 0
        for (int num : arr) {
            countArr[num]++;
        }

        // make summation to know how many elements are less than or equal to a particular number.
        for (int i = 1; i < countArr.length; ++i) {
            countArr[i] += countArr[i - 1];
        }

        // output array that will have sorted arr
        int[] output = new int[len];
        for (int i = len - 1; i >= 0; --i) {
            output[countArr[arr[i]] - 1] = arr[i];
            --countArr[arr[i]];
        }

        // copy the output array to arr, so that arr now contains sorted integars
        for (int i = 0; i < len; ++i) {
            arr[i] = output[i];
        }
    }
}