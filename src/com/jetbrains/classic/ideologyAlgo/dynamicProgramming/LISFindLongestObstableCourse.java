package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

public class LISFindLongestObstableCourse {
    // just simiar to the LIS binary search approach
    public int[] longestObstacleCourseAtEachPosition(int[] A) {
        int n = A.length, len = 0, res[] = new int[n], dp[] = new int[n];
        for (int j = 0; j < n; ++j) {

            int num = A[j];
            int left = 0, right = len - 1;
            int i = len;
            while (left <= right) {
                int mid = left + (right - left) / 2;

                // find the first element that is larger than num
                if (dp[mid] <= num) {
                    left = mid + 1;
                } else {
                    if (mid == 0 || (dp[mid-1] <= num)) {
                        i = mid;
                        break;
                    }
                    right = mid - 1;
                }
            }

            dp[i] = num;

            if (len == i)
                len++;

            res[j] = i + 1;
        }
        return res;
    }

}
