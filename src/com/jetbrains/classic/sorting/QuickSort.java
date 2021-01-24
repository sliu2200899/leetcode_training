package com.jetbrains.classic.sorting;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    public static void quick_sort(int[] arr, int start, int end) {

        if (start < end) {
            // 1. random
//        Random rand = new Random();
//        int pivot_index = rand.nextInt(end - start + 1) + start;

            // 2. mid
            int pivot_index = start + (end - start) / 2;

            pivot_index = partition(arr, start, end, pivot_index);

            quick_sort(arr, start, pivot_index - 1);
            quick_sort(arr, pivot_index + 1, end);
        }
    }

    private static int partition(int[] arr, int start, int end, int pivot_index) {

        if (start == end) return start;

        int pivot = arr[pivot_index];
        swap(arr, pivot_index, end);

        // split the array into two parts:   ( smaller ones    |    larger or equal to pivot)
        int store_index = start;  // store_index is used to keep track of all the smaller elements
        for (int i = start; i <= end; ++i) {  // i is the traversal pointer that scan through the array
            if (arr[i] < pivot) {
                swap(arr, store_index, i);
                store_index++;
            }
        }
        swap(arr, store_index, end);
        return store_index;
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }


    // simple implementation
    public static void quick_sort2(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }

        int pivot = arr[start + (end - start) / 2];
        int left = start;
        int right = end;

        while (left <= right) {
            while (left <= right && arr[left] < pivot) {
                left++;
            }
            while (left <= right && arr[right] > pivot) {
                right--;
            }
            if (left <= right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }
        quick_sort2(arr, start, right);
        quick_sort2(arr, left, end);
    }

    // preferred way to do
    public static void quick_sort3(int[] arr, int start, int end) {

        if (start >= end) {
            return;
        }

        int partitionIndex = partition3(arr, start, end);

        quick_sort3(arr, start, partitionIndex - 1);
        quick_sort3(arr, partitionIndex  + 1, end);
    }

    private static int partition3(int[] arr, int start, int end) {

        int mid = start + (end - start) / 2;
        int pivot = arr[mid];    // choose the right most value as pivot value
        swap(arr, mid, end);

        int endIndex = end;

        // put all the numbers equal to pivot to the right of the array
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


    /*
        quick sort
     */
    public static void quick_sort4(int[] arr) {
        if (arr == null) return;

        sort4(arr, 0, arr.length - 1);
    }

    private static void sort4(int[] arr, int start, int end) {
        if (start < end) {
            // partition
            int partition_index = partition4(arr, start, end);

            // sort4
            sort4(arr, start, partition_index-1);

            sort4(arr, partition_index+1, end);
        }
    }

    private static int partition4(int[] arr, int start, int end) {
        // get the partition element
        // put all the elements smaller than the partition element to the left part, and all the elements larger or equal to the partition element to teh right part.

        Random r = new Random();
        int partition_index = r.nextInt(end - start + 1) + start;
        int p_value = arr[partition_index];

        swap(arr, partition_index, end);
        int end_index = end;

        while (start <= end) {
            while (start <= end && arr[start] < p_value) start++;
            while (start <= end && arr[end] >= p_value) end--;

            if (start <= end) {
                swap(arr, start, end);
                start++;
                end--;
            }
        }
        // .... end start ...
        swap(arr, start, end_index);
        return start;
    }
}
