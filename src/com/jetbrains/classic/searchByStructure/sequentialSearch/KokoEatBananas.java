package com.jetbrains.classic.searchByStructure.sequentialSearch;

import java.util.Arrays;

public class KokoEatBananas {
    public int minEatingSpeed(int[] piles, int h) {
        /*
            piles = [3,6,7,11], h = 8
            4

            len of piles.  4
            start: 1
            end: 11

            Binary search to find the smallest valid K.  (pattern of lower-bound Binary Search)

            Binary search between [1, 10^9] or [1, max(piles)] to find the result.
            Time complexity: O(NlogM)   M is max(piles) - 1 , N is num of piles
            space: O(1)
        */
        int start = 1, end = Arrays.stream(piles).max().getAsInt();
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (canEatAll(piles, mid, h)) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return start;
    }

    private boolean canEatAll(int[] piles, int k, int h) {
        int countHour = 0;  // hours take to eat all bananas at speek k

        for (int pile : piles) {
            countHour += (pile + k - 1) / k; //   (p + m - 1) / m equal to ceil(p / m) (just personal behavior)
        }
        return countHour <= h;
    }
}
