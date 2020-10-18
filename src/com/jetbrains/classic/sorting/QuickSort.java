package com.jetbrains.classic.sorting;

import java.util.Arrays;

public class QuickSort {
    public static void quick_sort(int[] arr, int start, int end) {

        if (start < end) {
            int pi = partition(arr, start, end);

            quick_sort(arr, start, pi - 1);
            quick_sort(arr, pi + 1, end);
        }
    }

    private static int partition(int[] arr, int start, int end) {
        int mid = start + (end - start) / 2;
        int pivot = arr[mid];
        swap(arr, mid, end);

        // int pivot = arr[end];

        int i = start - 1;  // i is used to keep track of the smaller elements
        for (int j = start; j < end; ++j) {  // j is the traversal pointer that scan through the array
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, end);
        return i + 1;
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
