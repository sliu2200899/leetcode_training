package com.jetbrains.classic;

public class TrapRainWater {
    public static int trapTwoPass(int[] height) {
        if (height == null || height.length == 0) return 0;

        int len = height.length;

        // find the peak element at first.
        int peak = Integer.MIN_VALUE;
        for (int i : height) {
            peak = Math.max(peak, i);
        }

        // calculate the water from both ends of the array
        int left = 0, right = len - 1, container = 0, min = 0;
        while (height[left] != peak) {
            if (height[left] < min) {
                container += (min - height[left]);
            } else {
                min = height[left];
            }
            left++;
        }

        min = 0;
        while (height[right] != peak) {
            if (height[right] < min) {
                container += (min - height[right]);
            } else {
                min = height[right];
            }
            right--;
        }

        while (left != right) {
            container += (peak - height[left]);
            left++;
        }

        return container;
    }

    public int trapOnePass(int[] height) {
        if (height == null || height.length == 0) return 0;

        int left = 0, right = height.length - 1, area = 0, lowBound = 0;
        while (left < right) {
            lowBound = Math.max(lowBound, Math.min(height[left], height[right]));
            if (height[left] < height[right]) {
                if (height[left] < lowBound) {
                    area += (lowBound - height[left]);
                }
                left++;
            } else {
                if (height[right] < lowBound) {
                    area += (lowBound - height[right]);
                }
                right--;
            }
        }
        return area;
    }
}
