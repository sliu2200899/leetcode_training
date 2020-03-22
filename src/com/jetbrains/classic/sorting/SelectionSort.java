package com.jetbrains.classic.sorting;

public class SelectionSort {
    public static void sort(int[] arr) {
        int len = arr.length;

        // improve on the bubble sort, reduce the number of the swap O(n)
        // but time complexity and number of comparison is the same level.
        // for each iteration, just set the min value index and change the min value index when comparison and
        // just swap once in the end
        for (int i = 0; i < len - 1; ++i) {
            int min = i; // set initial min value's index

            // select a new minmum value's index
            for (int j = i + 1; j < len; ++j) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }

            if (min != i) {
                int tmp = arr[i];
                arr[i] = arr[min];
                arr[min] = tmp;
            }
        }
    }
//
//
//    // 1, 2, 3, 4, 7, 9  -> improvement of the bubble sort, no swap just keep the location of the element that need to swap in each iteration
//    public static void sort2(int[] arr) {
//        if (arr == null) return;
//
//        int len = arr.length;
//        for (int i = 0; i < len - 1; ++i) {
//            int min = i;
//            for (int j = 0; j < len - i - 1; ++j) {
//
//            }
//        }
//
//
//
//    }
}
