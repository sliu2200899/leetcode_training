package com.jetbrains.classic.sorting;

public class QuickSort {
    public static void sort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }

        int mid = (start + end) / 2;
        int pivot = arr[mid]; // choose the right most element as pivot value

        swap(arr, mid, end);

        int partitionIndex = partition(arr, start, end, pivot);

        sort(arr, start, partitionIndex - 1);
        sort(arr, partitionIndex + 1, end);
    }

    public static int partition(int[] arr, int start, int end, int pivot) {
        int left = start;
        int right = end;

        while (left <= right) {
            while (left <= right && arr[left] < pivot) {
                left++;
            }

            while (left <= right && arr[right] >= pivot) {
                right--;
            }

            if (left <= right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }

        swap(arr, left, end);
        return left;
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
