package com.jetbrains.master;

public class MaxAreaWater {
    public static int maxArea(int[] height) {
        if (height == null || height.length == 0) return 0;

        int len = height.length;

        int maxArea = 0, left = 0, right = len - 1;
        while(left < right) {
            if (height[left] < height[right]) {
                maxArea = Math.max(maxArea, (right - left) * height[left]);
                left++;
            } else {
                maxArea = Math.max(maxArea, (right - left) * height[right]);
                right--;
            }
        }

        return maxArea;
    }
}
