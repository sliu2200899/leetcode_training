package com.jetbrains.hard;

public class ReversePairs {
    /*
        Reverse pair is a pair of numbers (A[i], A[j]) such that A[i] > A[j] and i < j. Given an array, return the number of reverse pairs in the array.

        e.g. Input:  A = [2, 4, 1, 3, 5]
            Output: 3
            Explanation:
            (2, 1), (4, 1), (4, 3) are reverse pairs
     */
    /*
        method 1: brute force

        求解逆序度
     */
    public long reversePairs(int[] A) {
        // write your code here

        int n = A.length;
        int count = 0;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < n - i; ++j) {
                if (A[j] > A[j + 1]) {
                    swap(A, j, j + 1);
                    count++;
                }
            }
        }
        return count;
    }

    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    /*
        method 2: merge sort
     */

    long count = 0;
    public long reversePairs2(int[] A) {
        // write your code here

        helper(A, 0, A.length - 1);
        return count;
    }

    public long getCount() {
        return count;
    }

    private void helper(int[] A, int start, int end) {
        if (start < end) {
            int mid = start + (end - start) / 2;
            helper(A, start, mid);
            helper(A, mid + 1, end);

            merge(A, start, mid, end);
        }
    }

    private void merge(int[] A, int start, int mid, int end) {
        if (start > end) return;

        int lenLeft = mid - start + 1;
        int lenRight = end - mid;

        int[] leftArr = new int[lenLeft];
        int[] rightArr= new int[lenRight];

        for (int i = 0; i < lenLeft; ++i) {
            leftArr[i] = A[start + i];
        }

        for (int i = 0; i < lenRight; ++i) {
            rightArr[i] = A[mid + 1 + i];
        }

        int i = 0, j = 0, cur = 0;
        while (i < lenLeft  && j < lenRight) {
            if (leftArr[i] > rightArr[j]) {
                A[start + cur++] =  rightArr[j++];
                count += (mid - (i + start) + 1);
            } else {
                A[start + cur++] =  leftArr[i++];
            }
        }

        while (i < lenLeft) {
            A[start + cur++] =  leftArr[i++];
        }

        while (j < lenRight) {
            A[start + cur++] =  rightArr[j++];
        }
    }

}
