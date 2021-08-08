package com.jetbrains.classic.searchByStructure.sequentialSearch;

public class KthMissingPositive {
    /*
        time:  O(logN)
        space: O(1)

        find the last element whose number of prior missing positive is smaller than k

     */
    public int findKthPositive(int[] nums, int k) {
        // 6 - 3
        // (nums[i] - 1) - (i)  num of missing

        int n = nums.length;
        int left = 0, right = n - 1;
        int idx = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // find the last element whose number of prior missing positive is smaller than k
            if (nums[mid] - 1 - mid >= k) {
                right = mid - 1;
            } else {
                if (mid == n - 1 || (nums[mid + 1] - 1 - (mid+1) >= k)) {
                    idx = mid;
                    break;
                }
                left = mid + 1;
            }
        }

        if (idx == -1) {
            return k;
        }

        return nums[idx] + k - (nums[idx] - 1 - (idx));
    }
}
