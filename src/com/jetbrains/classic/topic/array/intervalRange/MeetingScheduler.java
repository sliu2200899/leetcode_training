package com.jetbrains.classic.topic.array.intervalRange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MeetingScheduler {

    /*
        time: O((m+n) log(m+n))
        space: O(mn)

     */
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        if (slots1 == null || slots1.length == 0 || slots2 == null || slots2.length == 0) {
            return new ArrayList<Integer>();
        }

        // merge slots1 and slots2
        List<int[]> list = new ArrayList<>();
        for (int[] pair : slots1) {
            list.add(pair);
        }
        for (int[] pair : slots2) {
            list.add(pair);
        }

        // sort them based on start time
        Collections.sort(list, (a, b) -> {
            return Integer.compare(a[0], b[0]);
        });

        List<Integer> res = new ArrayList<>();
        // find the overlapping intervals, if the interval length is > duration, then we find it.
        int end = list.get(0)[1];
        for (int i = 1; i < list.size(); ++i) {
            if (list.get(i)[0] >= end) {
                end = list.get(i)[1];
            } else {
                // have overlapping
                int endTime = Math.min(end, list.get(i)[1]);

                if (endTime - list.get(i)[0] >= duration) {
                    res.add(list.get(i)[0]);
                    res.add(list.get(i)[0] + duration);
                    return res;
                }

                end = Math.max(end, list.get(i)[1]);
            }
        }

        return res;
    }

    /*
        We want to sort both slots1 and slots2 by the start time first, then initialize two pointers,
        each of which points to the beginning of the two arrays. From there, we will compare the two slots,
        and move one pointer at a time if the common slot is smaller than duration.


        Here comes the question: how do we decide which pointer should be incremented?

        The answer is: we will always move the one that ends earlier. Assuming that we are comparing slots1[i] and slots2[j]
        and slots1[i][1] > slots2[j][1], we would always choose to move the pointer j. The reason is that, as both slots are sorted,
        if slots1[i][1] > slots2[j][1], we know slots1[i+1][0] > slots2[j][1] so that there will be no intersection between slots1[i+1] and slots2[j].

        time: O(MlogM + NlogN)
        space: O(1)
     */
    public List<Integer> minAvailableDuration2(int[][] slots1, int[][] slots2, int duration) {
        Arrays.sort(slots1, (a, b) -> a[0] - b[0]);
        Arrays.sort(slots2, (a, b) -> a[0] - b[0]);

        int pointer1 = 0, pointer2 = 0;

        while (pointer1 < slots1.length && pointer2 < slots2.length) {
            // find the boundaries of the intersection
            int intersectLeft = Math.max(slots1[pointer1][0], slots2[pointer2][0]);
            int intersectRight = Math.min(slots1[pointer1][1], slots2[pointer2][1]);

            if (intersectRight - intersectLeft >= duration) {
                return new ArrayList<Integer>(Arrays.asList(intersectLeft, intersectLeft + duration));
            }

            // always move the one that ends earlier
            if(slots1[pointer1][1] < slots2[pointer2][1]) {
                pointer1++;
            } else {
                pointer2++;
            }
        }

        return new ArrayList<Integer>();
    }
}
