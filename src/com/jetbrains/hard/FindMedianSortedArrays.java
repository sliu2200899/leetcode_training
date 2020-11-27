package com.jetbrains.hard;

/*
To solve this problem, we need to understand "What is the use of median". In statistics, the median is used for:
    Dividing a set into two equal length subsets, that one subset is always greater than the other.



 */

public class FindMedianSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;

        if (len % 2 == 1) {
            return findKth(nums1, 0, nums2, 0, len / 2 + 1);
        } else {
            return (findKth(nums1, 0, nums2, 0, len / 2) +
                    findKth(nums1, 0, nums2, 0, len / 2 + 1)) / 2.0;
        }
    }

    private int findKth(int[] A, int startA, int[] B, int startB, int k) {
        // base case
        if (startA == A.length) return B[startB + k - 1];
        if (startB == B.length) return A[startA + k - 1];
        if (k == 1) return Math.min(A[startA], B[startB]);

        // recursive case
        int keyA = (startA + k / 2 - 1 < A.length)
                ? A[startA + k / 2 - 1]
                : Integer.MAX_VALUE;
        int keyB = (startB + k / 2 - 1 < B.length)
                ? B[startB + k / 2 - 1]
                : Integer.MAX_VALUE;
        if (keyA < keyB) {
            return findKth(A, startA + k/2, B, startB, k - k/2);
        } else {
            return findKth(A, startA, B, startB + k/2, k - k/2);
        }
    }
}
