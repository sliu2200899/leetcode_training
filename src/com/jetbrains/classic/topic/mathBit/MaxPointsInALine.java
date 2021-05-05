package com.jetbrains.classic.topic.mathBit;

import java.util.HashMap;
import java.util.Map;

public class MaxPointsInALine {
    // 1. how to deal with duplicates
    // 2. how to deal with slope ratio  map<String, Integer>
    // 3. how to calculate gcd
    public int maxPoints(int[][] points) {
        if (points == null || points.length == 0 || points[0].length == 0) {
            return 0;
        }

        int result = 0;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < points.length; ++i) {
            map.clear();
            int max = 0, overlap = 0;
            for (int j = i + 1; j < points.length; ++j) {

                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];

                // deal with duplicates
                if (x == 0 && y == 0) {
                    overlap++;
                    continue;
                }

                // deal with slope ratio
                int gcd = getGcd(x, y);
                if (gcd != 0) {
                    x /= gcd;
                    y /= gcd;
                }

                String key = x + ":" + y;
                if (map.containsKey(key)) {
                    map.put(key, map.get(key) + 1);
                } else {
                    map.put(key, 1);
                }

                max = Math.max(max, map.get(key));
            }

            result = Math.max(result, max + overlap + 1);
        }

        return result;
    }


    private int getGcd(int a, int b) {
        if (b == 0) return a;
        else return getGcd(b, a%b);
    }
}
