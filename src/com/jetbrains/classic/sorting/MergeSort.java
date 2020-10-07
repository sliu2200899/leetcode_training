package com.jetbrains.classic.sorting;

public class MergeSort {

    public static void sort(int[] arr) {
        sortHelper(arr, 0, arr.length - 1);
    }

    private static void sortHelper(int[] arr, int start, int end) {
        if (start < end) {
            int mid = start + (end - start) / 2;
            sortHelper(arr, start, mid);
            sortHelper(arr, mid + 1, end);

            merge(arr, start, mid, end);
        }
    }

    private static void merge(int[] arr, int start, int mid, int end) {
        // find sizes of the subarray to be merged
        int n1 = mid - start + 1;
        int n2 = end - mid;

        // create temp arrays
        int[] startArr = new int[n1];
        int[] endArr = new int[n2];

        // copy data to temp arrays
        for (int i = 0; i < n1; ++i) {
            startArr[i] = arr[i + start];
        }

        for (int i = 0; i < n2; ++i) {
            endArr[i] = arr[i + mid + 1];
        }

        // merge two sorted temp array
        int i = 0, j = 0;
        int base = start;
        while (i < n1 && j < n2) {
            if (startArr[i] <= endArr[j]) {
                arr[base++] = startArr[i++];
            } else {
                arr[base++] = endArr[j++];
            }
        }

        while (i < n1) {
            arr[base++] = startArr[i++];
        }

        while (i < n2) {
            arr[base++] = endArr[j++];
        }
    }
}
