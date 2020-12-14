package com.jetbrains.classic;

public class RotateArray {
    // method 1 brute force
    // time complexity is O(n * k)
    // space complexity is O(1)
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0) return;

        int n = nums.length;
        k = k % n;

        if (k == 0) return;

        for (int i = 0; i < k; ++i) {
            int last = nums[n - 1];
            for (int j = n - 2; j >= 0; --j) {
                nums[j + 1] = nums[j];
            }

            nums[0] = last;
        }
    }

    // method 2  using extra array
    // time complexity is O(n)
    // space complexity is O(n)
    public void rotate2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0) return;

        int n = nums.length;

        int[] a = new int[n];
        for (int i = 0; i < nums.length; ++i) {
            a[(i + k) % n] = nums[i];
        }

        for (int i = 0; i < nums.length; ++i) {
            nums[i] = a[i];
        }
    }

    // method 3  reverse the entire and part of the array
    // time complexity is O(n)
    // space complexity is O(1)
    public void rotate3(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0) return;

        int n = nums.length;

        k %= n;

        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;

            start++;
            end--;
        }
    }
}
