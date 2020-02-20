package com.jetbrains.classic.sorting;

public class MergeSort {
    public static void sort(int[] arr, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;

            sort(arr, low, mid);
            sort(arr, mid + 1, high);

            merge(arr, low, mid, high);
        }
    }

    public static void merge(int[] arr, int low, int mid, int high) {
        // find sizes of two subarrays to be merged
        int n1 = mid - low + 1;
        int n2 = high - mid;

        // create tmp arrays
        int[] lowArr = new int[n1];
        int[] highArr = new int[n2];

        // copy data to temp arrays
        for (int i = 0; i < n1; ++i) {
            lowArr[i] = arr[low + i];
        }

        for (int i = 0; i < n2; ++i) {
            highArr[i] = arr[mid + i + 1];
        }

        // merge the temp arrays
        int i = 0, j = 0;
        int k = low;
        while (i < n1 && j < n2) {
            if (lowArr[i] <= highArr[j]) {
                arr[k++] = lowArr[i++];
            } else {
                arr[k++] = highArr[j++];
            }
        }

        while (i < n1) {
            arr[k++] = lowArr[i++];
        }
        while (j < n2) {
            arr[k++] = highArr[j++];
        }
    }
}
