package com.jetbrains.zCompanyInterviewPrep.amazon;

import java.util.Arrays;

public class MaximumUnitsOnATruck {
    /*
        you are assigned to put some amount of boxes onto one truck.
        you are given 2D array boxTypes, where boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi]:
            1. numberOfBoxesi is the number of boxes of type i
            2. numberOfUnitsPerBoxi is the number of units in each box of the type i.

        also given an integer truckSize, which is the maximum number of boxes that can be put on the
        truck. You can choose any boxes to put on the truck as long as the nubmer of boxes doesn not
        exceed truckSize.

        return the maximum total number of units that can be put on the truck.

        example,
            boxTypes = [[1,3], [2,2], [3,1]], truckSize = 4
            return 8

     */

    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);

        int totalUnits = 0;
        int size = 0, boxIdx = 0;
        while (boxIdx < boxTypes.length) {
            int curNum = boxTypes[boxIdx][0];
            if (curNum + size <= truckSize) {
                totalUnits += (curNum * boxTypes[boxIdx][1]);
                size += curNum;
            } else {
                totalUnits += (truckSize - size) * boxTypes[boxIdx][1];
                break;
            }
            boxIdx++;
        }

        return totalUnits;
    }

}
