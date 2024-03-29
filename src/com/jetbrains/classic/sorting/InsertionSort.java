package com.jetbrains.classic.sorting;

public class InsertionSort {

    public static void sort(int[] arr) {
        int len = arr.length;

        for (int i = 1; i < arr.length; ++i) {
            int tmp = arr[i]; // store the value temporarily
            int j = i;  // initially set to be the same as i

            while (j > 0 && arr[j - 1] > tmp) {
                // shift one by one
                arr[j] = arr[j - 1];
                j--;
            }

            // if i is not the same as j variable, insert the tmp value into the correct index..
            if (j != i) {
                arr[j] = tmp;
            }
        }
    }

    /*
        insertion sort

     */
    public static void sort2(int[] arr) {
        if (arr == null) return;

        int n = arr.length;

        for (int i = 1; i < n; ++i) {
            int target = arr[i];

            int j = i-1;
            while (j >= 0 && arr[j] > target) {
                arr[j+1] = arr[j];
                --j;
            }

            arr[j+1] = target;
        }
    }
}
