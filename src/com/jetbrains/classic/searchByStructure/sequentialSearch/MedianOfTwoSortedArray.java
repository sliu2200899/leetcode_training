package com.jetbrains.classic.searchByStructure.sequentialSearch;

public class MedianOfTwoSortedArray {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        /*
            analyze:

            based on the size ofo nums1 and nums2, len1 and len2
            if the sum of len1 and len2 is even, we know that median would be the average of two middle elements
            if the sum of len1 and len2 is odd, we know that median would be the middle element.

            [1,2]  nums1 with len1 2
            [3]  nums2 with len2 1


            algo:
                len1 and len2
                if ((len1 + len2) is even)
                    return (findkthElement(nums1, 0, nums2, 0,  (len1 + len2) / 2) +
                            findkthElement(nums1, 0, nums2, 0, (len1 + len2) / 2) + 1) / 2;
                else
                    return findkthElement(nums1, nums2, (len1 + len2) / 2 + 1);


                findkthElement(A, startA, B, startB, int k)
                    // base case
                    if (startA == A.lnegth) return B[startB + k - 1];
                    if (startB == B.length) return A[startA + k - 1];
                    if k == 1  return min(A[startA], B[startB])

                    // recursive case
                    // try to get the
                    int keyA = (startA + k/2 - 1 < A.length) ?
                                A[startA + k/2 - 1] :
                                Integer.MAX_VALUE;

                    int keyB = similar to above

                    if keyA < keyB
                        return findkthElement(A, startA + k/2, B, startB, k - k/2);
                    else
                        return findkthElement(A, startA, B, startB + k/2, k - k/2);

              time: O(log(n+m))
              space: O(log(n+m))
        */

        int len1 = nums1.length, len2 = nums2.length;
        int len = len1 + len2;

        if (len % 2 == 1) {
            return (double)findKthElement(nums1, 0, nums2, 0, len/2 +1);
        } else {
            return (findKthElement(nums1, 0, nums2, 0, len/2) +
                    findKthElement(nums1, 0, nums2, 0, len/2 + 1)) / 2.0;
        }
    }

    private int findKthElement(int[] A, int startA, int[] B, int startB, int k) {
        // base case
        if (startA == A.length) return B[startB + k - 1];
        if (startB == B.length) return A[startA + k - 1];
        if (k == 1) return Math.min(A[startA], B[startB]);

        // recursive case
        // each time, we try to get the k/2 th element in array A and array B,
        // compare those two numbers, if keyA is less than keyB, then it means all the numbers to the left of the keyA is less than keyB, so we can reduce the search scope by k/2.
        int keyA = startA + k/2 - 1 < A.length ?
                A[startA + k/2 - 1] :
                Integer.MAX_VALUE;

        int keyB = startB + k/2 - 1 < B.length ?
                B[startB + k/2 - 1] :
                Integer.MAX_VALUE;

        if (keyA < keyB) {
            return findKthElement(A, startA + k/2, B, startB, k - k/2);
        } else {
            return findKthElement(A, startA, B, startB + k/2, k - k/2);
        }
    }

    // follow up: median of k sorted array


}
