package com.jetbrains.classic.searchByStructure.sequentialSearch;

import java.util.Arrays;

public class ShipWithinDays {
    public int shipWithinDays(int[] weights, int D) {
        /*
            analyze:
                weights = [1,2,3,4,5,6,7,8,9,10], D = 5
                15

                start = 14, end = 14;
                mid = 15;
                1st day: 1, 2, 3, 4, 10
                2nd day: 5, 6,     11
                3rd day: 8, 9.  17
                4th day: 9
                5th day: 10




                binary search for the minimum capacity that can ship all hte packages in D days

                if (canShip()) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }

                return start;
        */

        int n = weights.length;

        int start = Arrays.stream(weights).max().getAsInt(), end = 1000000;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (canShip(weights, D, mid)) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }

    private boolean canShip(int[] weights, int days, int sw) {
        int dnum = 1, dw = 0;
        for (int weight : weights) {
            dw += weight;
            if (dw > sw) {
                dnum++;
                dw = weight;
            }
        }
        return dnum <= days;
    }
}
