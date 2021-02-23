package com.jetbrains.classic.topic.twoPointers;

public class TrappingRainWater {
    /*

    analyze:
        Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
        Output: 6

        instead of computing the left and right parts seperately, we may think of some way to do it in one iteration.
        From the figure in dynamic programming approach, notice that as long as right_max[i]>left_max[i] (from element 0 to 6), the water trapped depends upon the left_max,
        and similar is the case when left_max[i]>right_max[i] (from element 8 to 11). So, we can say that if there is a larger bar at one end (say right), we are assured
        that the water trapped would be dependant on height of bar in current direction (from left to right). As soon as we find the bar at other end (right) is smaller, we start iterating in opposite direction
        (from right to left). We must maintain left_max and right_max during the iteration, but now we can do it in one iteration using 2 pointers, switching between the two.

    algo:

        Initialize left pointer to 0 and right pointer to size-1
        while left < right
            if (height[left] is smaller than height[right])
                if (height[left] >= left_max)  update left_max
                else add left_max - height[left] to ans
                add 1 to left
            else
                if (height[right] >= right_max) udpate right_max
                else add right_max - height[right] to ans
                subtract 1 from right

    time: O(n)
    space: O(1)

*/
    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;

        int n = height.length;

        int left = 0, right = n - 1;
        int l = height[0], r = height[n - 1];
        int sum = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                left++;
                if (height[left] < l) {
                    sum += (l - height[left]);
                } else {
                    l = height[left];
                }
            } else {
                right--;
                if (height[right] < r) {
                    sum += (r - height[right]);
                } else {
                    r = height[right];
                }
            }
        }
        return sum;
    }
}
