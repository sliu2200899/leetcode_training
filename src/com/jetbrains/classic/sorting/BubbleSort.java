package com.jetbrains.classic.sorting;

public class BubbleSort {
    public static void sort (int[] arr) {
        // time complexity is O(n^2)
        // space complexity is O(1)

        // the idea behind the bubble sort
        // three steps in total
        // 1. compare two values at a time
        // 2. if the one on the left is bigger, swap them to Bubble up the bigger value to the right
        // 3. move one position to the right.

        int len = arr.length;
        for (int i = 0; i < len - 1; ++i) {
            boolean flag = false;
            for (int j = 0; j < len - i - 1; ++j) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = tmp;
                    flag = true;
                }
            }
            if (!flag) break;
        }
    }

    /*
        bubble sort:

     */

    public static void sort2(int[] arr) {
        if (arr == null) return;

        for (int i = 0; i < arr.length - 1; ++i) {
            boolean flag = false;
            for (int j = 0; j < arr.length - 1 - i; ++j) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                    flag = true;
                }
            }
            if (!flag) break;
        }
    }
}
