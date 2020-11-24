package com.jetbrains.classic.searchByStructure.sequentialSearch;


/*
 binary search only applied to the sequential data and
 */


public class BinarySearch {
    /**
     * @param A an integer array sorted in ascending order
     * @param target an integer
     * @return an integer
     */
    public int findPosition(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0, end = nums.length - 1;
        // 要点1: start + 1 < end
        while (start + 1 < end) {
            // 要点2：start + (end - start) / 2
            int mid = start + (end - start) / 2;
            // 要点3：=, <, > 分开讨论，mid 不+1也不-1
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        // 要点4: 循环结束后，单独处理start和end
        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }
        return -1;
    }

    // preferred method
    // very basic no duplicates, and find some specific value

    public int binarySearchBasic(int[] a, int n, int value) {
        int start = 0, end = n - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (a[mid] > value) {
                end = mid - 1;
            } else if (a[mid] < value) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    // if we have duplicates in the sorted array
    // and we try to find the first element in the array
    public int binarySearchFindFirst(int[] a, int n, int value) {
        int start = 0, end = n - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (a[mid] > value) {
                end = mid - 1;
            } else if (a[mid] < value) {
                start = mid + 1;
            } else {
                if (mid == 0 || a[mid - 1] != value) return mid;
                else end = mid - 1;
            }
        }
        return -1;
    }


    // we try to find the last element in the array
    public int binarySearchFindLast(int[] a, int n, int value) {
        int start = 0, end = n - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (a[mid] > value) {
                end = mid - 1;
            } else if (a[mid] < value) {
                start = mid + 1;
            } else {
                if (mid == n - 1 || a[mid + 1] != value) return mid;
                else start = mid + 1;
            }
        }
        return -1;
    }

    // we try to find the first elements that is large or equal to the specific element
    public int binarySearchFindFirstLarge(int[] a, int n, int value) {
        int start = 0, end = n -1 ;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (a[mid] < value) {
                start = mid + 1;
            } else {
                if (mid == 0 || a[mid - 1] < value) return mid;
                else end = mid - 1;
            }
        }
        return -1;
    }


    // we try to find the last elements that is smaller or equal to the specific element
    public int binarySearchFindLastSmaller(int[] a, int n, int value) {
        int start = 0, end = n -1 ;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (a[mid] > value) {
                end = mid - 1;
            }
            else {
                if (mid == n - 1 || a[mid + 1] > value) return mid;
                else start = mid + 1;
            }

        }
        return -1;
    }
}
