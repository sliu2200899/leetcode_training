package com.jetbrains.classic.sorting;

public class QuickSort {
    public static void sort(int[] arr, int start, int end) {

        if (start >= end) {
            return;
        }

        int mid = start + (end - start) / 2;
        int pivot = arr[mid];    // choose the right most value as pivot value

        swap(arr, mid, end);

        int partitionIndex = partition(arr, start, end, pivot);

        sort(arr, start, partitionIndex - 1);
        sort(arr, partitionIndex  + 1, end);
    }

    private static int partition(int[] arr, int start, int end, int pivot) {

        int endIndex = end;

        while (start <= end) {
            while (start <= end && arr[start] < pivot) {
                start++;
            }

            while (start <= end && arr[end] >= pivot) {
                end--;
            }

            if (start <= end) {
                swap(arr, start, end);
                start++;
                end--;
            }
        }

        swap(arr, start, endIndex);
        return start;
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
