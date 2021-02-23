package com.jetbrains.classic.array.subarraysum;

public class SubarraySum2 {

    /*
    Given an integer array, find a subarray where the sum of numbers is in a given interval. Your code should return the number of possible answers. (The element in the array should be positive)
    Example
    Given [1,2,3,4] and interval = [1,3], return 4. The possible answers are:
    [0, 0] [0, 1] [1, 1] [2, 2]
     */

    /*
        solution 1: brute force   time: O(n^3)  skip
        A straightforward solution is to enumerate all possible subarrays in O(n^2) time,
        then for each subarray, get its sum and check if this sum is in the given interval.
     */
    // just skip

    /*
         solution 2: prefix sum   time: O(n^2)
         Applying the BUD(Bottlenecks, Unnecessary work, Duplicated work) principle, we know that we are doing duplicated work when getting a subarray's sum.
         Each time we fix the start and end indices of a subarray,  it is only one element different from the previously checked subarray. But this solution does not use this
         condition. Instead, it calculates a subarray's sum from scratch. What can be optimized here is that we use prefix sum and calculate the sum of the first ith elements
         for i = 0, 1, ... n This preprocessing step takes O(n) time and O(n) space. When calculating a subarray's sum, only a O(1) subtraction is needed , thus making
         its runtime to the optimal O(n^2).
     */

    public int subarraySumII1(int[] A, int start, int end) {
        if(A == null || A.length == 0 || end < start){
            return 0;
        }
        int[] prefixSum = new int[A.length + 1];
        prefixSum[0] = 0;
        for(int i = 1; i <= A.length; i++){
            prefixSum[i] = prefixSum[i - 1] + A[i - 1];
        }
        int cnt = 0;
        for(int i = 1; i <= A.length; i++){
            for(int j = 0; j < i; j++){
                int diff = prefixSum[i] - prefixSum[j];
                if(diff >= start && diff <= end){
                    cnt++;
                }
            }
        }
        return cnt;
    }

    /*
        solution 4: binary search time: O(nlogn)
        refer to:  https://massivealgorithms.blogspot.com/2017/05/lintcode-138-404-subarray-sum-iii.html
     */


    /*
        solution 3: two pointer  time: O(n)
        (The element in the array should be positive)

        double sliding window

        因为这里我们的搜索目标是一个区间，所以可以等效于同时维护两个滑动窗口，一个窗口代表目标区间的最小值，另一个则是目标区间的最大值。因此，相较于常规使用一个指针来维护滑动窗口的尾部，
        我们需要使用两个指针分别来维护这两个滑动窗口。但是需要注意的时，这两个尾部指针并不完全独立，比如指向目标区间最大值的指针肯定不会处于指向目标区间最小值的指针之前。

        这里我们采用[lowTail, highTail)来标注符合要求的滑动窗口尾部区间，即[head, lowTail]是第一个以head开头并符合要求的子数组，而[head, highTail-1]是最后一个以head开头并符合要求的子数组。
     */
    public int subarraySumII3(int[] A, int start, int end) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int n = A.length;

        int counts = 0;
        int head = 0;
        int lowTail = 0, highTail = 0;
        int lowSum = 0, highSum = 0;

        while (head < n) {
            // find lower bound for window end(included)
            while (lowTail < n && lowSum + A[lowTail] < start) {
                lowSum += A[lowTail++];
            }

            // find upper bound for window end(excluded)
            if (highTail < lowTail) {
                highTail = lowTail;
                highSum = lowSum;
            }

            while (highTail < n && highSum + A[highTail] <= end) {
                highSum += A[highTail++];
            }

            // add numbers of valid subarray that starts at A[head]
            counts += (highTail - lowTail);

            highSum -= A[head];
            lowSum -= A[head];

            head++;
        }

        return counts;
    }
}
