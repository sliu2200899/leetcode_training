package com.jetbrains.zCompanyInterviewPrep.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrimeAirRoute {
    /*
        input
            maxTravelDist = 7000
            forwardRouteList
                [[1, 2000], [2, 4000], [3, 6000]]
            returnRouteList
                [[1, 2000]]

        output:
            [2,1]
            this is the only one pair that utilize the maxtraveldist optimally.
            2000 + 4000 < 7000
     */
    public List<int[]> getPair(int[][] A, int[][] B, int target) {
        int sum = -1;
        List<int[]> result = new ArrayList<>();
        for (int[] a : A) {
            for (int[] b : B) {
                int s = a[1] + b[1];
                if (s > target || s < sum) {
                    continue;
                }

                if (s > sum) {
                    result.clear();
                    sum = s;
                }
                result.add(new int[]{a[0], b[0]});
            }
        }
        return result;
    }
}
