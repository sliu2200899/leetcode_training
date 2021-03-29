package com.jetbrains.classic.topic.array.intervalRange;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MeetingScheduler {
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

        // find the overlapping intervals, if the interval length is > duration, then we find it.
        int end_prev = list.get(0)[1];
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < list.size(); ++i) {
            int[] cur = list.get(i);
            if (end_prev < cur[0]) {
                end_prev = cur[1];
            } else {
                // have overlapping
                int olp = Math.min(end_prev, cur[1]) - cur[0];
                if (olp >= duration) {
                    res.add(cur[0]);
                    res.add(cur[0] + duration);
                    break;
                }
                end_prev = Math.max(end_prev, cur[1]);
            }
        }

        return res;
    }
}
