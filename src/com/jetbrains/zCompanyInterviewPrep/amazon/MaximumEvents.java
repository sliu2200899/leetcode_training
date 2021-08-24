package com.jetbrains.zCompanyInterviewPrep.amazon;

import java.util.Arrays;

public class MaximumEvents {
    /*
        maximum events he can attend
     */
    public static int maxEvents(int[] start, int[] duration) {
        if (start == null || start.length == 0) return 0;

        int[][] events = new int[start.length][2];

        for (int i = 0; i < start.length; i++) {
            events[i][0] = start[i];
            events[i][1] = events[i][0] + duration[i];
        }

        Arrays.sort(events, (a, b) -> a[1] - b[1]);
        int endTime = events[0][1];
        int res = 1;
        for (int i = 1; i < events.length; i++) {
            if (events[i][0] >= endTime) {
                res++;
                endTime = events[i][1];
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] start = new int[]{5,3,3,1,7};
        int[] duration = new int[]{2,2,1,2,1};
        System.out.println(maxEvents(start, duration));
    }
}
