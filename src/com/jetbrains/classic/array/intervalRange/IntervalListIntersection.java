package com.jetbrains.classic.array.intervalRange;

import java.util.ArrayList;
import java.util.List;

public class IntervalListIntersection {
    /*
        use two pointers

     */
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> res = new ArrayList<>();

        int i = 0, j = 0;
        while (i < firstList.length && j < secondList.length) {

            // method to compute the overlapping area
            int lo = Math.max(firstList[i][0], secondList[j][0]);
            int hi = Math.min(firstList[i][1], secondList[j][1]);

            if (lo <= hi) {
                res.add(new int[]{lo, hi});
            }

            // remove the interval with the smallest endpoint why ?
            /*
                consider the case like:
                    first:  [2,9]
                    second: [4,6]

                    in this case, you should keep the first interval and discard the second interval.

            */
            if (firstList[i][1] < secondList[j][1]) {
                i++;
            } else {
                j++;
            }
        }

        return res.toArray(new int[0][0]);
    }
}
