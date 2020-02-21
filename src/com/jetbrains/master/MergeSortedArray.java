package com.jetbrains.master;

public class MergeSortedArray {
    // just put the larger element to the end of the nums1 array and copy the number from the end to the front...
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m + n - 1;
        while (i >= 0 && n >= 1 && m >= 1) {
            if (nums2[n - 1] > nums1[m - 1]) {
                nums1[i] = nums2[n - 1];
                n--;
            } else {
                nums1[i] = nums1[m - 1];
                m--;
            }
            i--;
        }

        while (n >= 1) {
            nums1[i--] = nums2[n - 1];
            n--;
        }
    }

    /*
        in this problem, the nums1 do not have enough space to hold the element..
        so we need to create a new array to store all the elemnt..
     */
    public static int[] mergeTwoSortedArray(int[] arrA, int[] arrB) {

        if (arrA == null) return arrB;
        if (arrB == null) return arrA;

        int n = arrA.length;
        int m = arrB.length;

        int len = n + m;
        int[] res = new int[len];
        int i = 0, curA = 0, curB = 0;
        while (i < len) {
            if (curA == n) {
                res[i++] = arrB[curB++];
            } else if (curB == m) {
                res[i++] = arrA[curA++];
            } else {
                if (arrA[curA] < arrB[curB]) {
                    res[i++] = arrA[curA++];
                } else {
                    res[i++] = arrB[curB++];
                }
            }
        }

        return res;
    }
}
