package com.jetbrains.classic.topic.mathBit;

public class ProductExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        /*
            analyze:
                the simplest way to solve the problem is just to product all of the elements in the array and for each of the elements, get the result by dividing itself.

                Instead of dividing the product of all the numbers in the array by the number at a given index to get the corresponding product,
                we can make use of the product of all the numbers to the left and all the numbers to the right of the index.
                Multiplying these two individual products would give us the desired result as well.

            algo:
                Initialize two empty arrays, L and R where for a given index i, L[i] would contain the product of all the numbers to the left of i and
                R[i] would contain the product of all the numbers to the right of i.

                We would need two different loops to fill in values for the two arrays.
                    L[0] = 1, R[n-1] = 1
                    L[i] = L[i-1] * nums[i-1]
                    R[i] = R[i+1] * nums[i+1]

                Once we have the two arrays set up properly, we simply iterate over the input array one element at a time, and for each element at index i, we find the
                    product except self as L[i] * R[i]

        */
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];

        left[0] = 1;
        right[n-1] = 1;

        for (int i = 1; i < n; ++i) {
            left[i] = left[i - 1] * nums[i - 1];
        }

        for (int i = n - 2; i >= 0; --i) {
            right[i] = right[i + 1] * nums[i + 1];
        }

        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            res[i] = left[i] * right[i];
        }

        return res;
    }

    /*
        optimize the space complexity:  O(1)
     */
    public int[] productExceptSelf2(int[] nums) {

        int n = nums.length;

        int[] res = new int[n];

        res[n-1] = 1;

        for (int i = n - 2; i >= 0; --i) {
            res[i] = res[i + 1] * nums[i + 1];
        }

        int sum = 1;
        for (int i = 0; i < n; ++i) {
            res[i] = sum * res[i];
            sum *= nums[i];
        }

        return res;
    }
}
