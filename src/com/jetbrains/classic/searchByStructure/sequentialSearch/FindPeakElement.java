package com.jetbrains.classic.searchByStructure.sequentialSearch;

public class FindPeakElement {
    /*
        solution 1: linear search

        analyze:
            linear scan
                we make use of the fact that two consecutive numbers nums[j]nums[j] and nums[j + 1]nums[j+1] are never equal.
                Thus, we can traverse over the numsnums array starting from the beginning. Whenever, we find a number nums[i]nums[i], we only need to check
                if it is larger than the next number nums[i+1]nums[i+1] for determining if nums[i]nums[i] is the peak element.
                The reasoning behind this can be understood by taking the following three cases which cover every case into which any problem can be divided.

            case 1:
                all the numbers appear in a descending order
                In this case, the first element corresponds to the peak element. We start off by checking if the current element is larger than the next one.
                The first element satisfies this criteria, and is hence identified as the peak correctly.

            case 2:
                all the numbers appear in ascending order
                In this case, we keep on comparing nums[i]nums[i] with nums[i+1]nums[i+1] to determine if nums[i]nums[i] is the peak element or not.
                None of the elements satisfy this criteria, indicating that we are currently on a rising slope and not on a peak. Thus, at the end, we need to
                return the last element as the peak element

            case 3:
                The peak appears somewhere in the middle.
                In this case, when we are traversing on the rising edge, as in Case 2, none of the elements will satisfy nums[i] > nums[i + 1]nums[i]>nums[i+1].
                We need not compare nums[i]nums[i] with nums[i-1]nums[i−1] on the rising slope as discussed above. When we finally reach the peak element, the
                condition nums[i] > nums[i + 1]nums[i]>nums[i+1] is satisfied.

        algo:
            for (i = 0; i < nums.length - 1; ++i) {
                if (nums[i] > nums[i + 1]) {
                    return i;
                }
            }
            return nums.length - 1;
    */
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) return -1;

        int n = nums.length;

        for (int i = 0; i < n - 1; ++i) {
            if (nums[i] > nums[i + 1]) {
                return i;
            }
        }
        return n - 1;
    }

    /*
        binary search
     */
    public int findPeakElement2(int[] nums) {

        // binary search by using start, end, mid
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > nums[mid + 1]) {
                end = mid;
            } else {
                start = mid;
            }
        }

        return nums[start] > nums[end] ? start : end;
    }
}
