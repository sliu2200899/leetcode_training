package com.jetbrains.classic.topic.array;

public class FirstMissingPositive {
    /*
    clarify:
        1, input, output, example
        2. can we modify teh input array

*/
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        // mark numbers that < 0  or > n with a special number (n+1)
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] <= 0 || nums[i] > n) {
                nums[i] = n + 1;
            }
        }

        // in this case all the number is in the range from 1... n + 1

        // iterate over the nums array try to convert the nums[nums[i] - 1] => -1 * nums[nums[i] - 1]
        for (int i = 0; i < nums.length; ++i) {
            int num = Math.abs(nums[i]);

            if (num > n) continue;

            num--;   // -1 for zero index based array

            if (nums[num] > 0) {  // prevents double negative operations
                nums[num] = -1 * nums[num];
            }
        }

        // iterate over the nums array again, find hte first positive number
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] > 0) {
                return i+1;
            }
        }

        return n+1;
    }
}
