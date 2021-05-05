package com.jetbrains.classic.topic.array.intervalRange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MissingRange {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ans = new ArrayList<>();
        Arrays.sort(nums);

        if (nums == null || nums.length == 0) {
            String range = (lower == upper ? (upper + "") : (lower + "->" + upper));
            ans.add(range);
            return ans;
        }

        int rangeStart = lower;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] <= upper && nums[i] > rangeStart) {
                String range = (rangeStart == (nums[i] - 1) ? (rangeStart + "") : (rangeStart + "->" + (nums[i] - 1)));
                ans.add(range);
            }
            rangeStart = nums[i] + 1;
        }

        if (rangeStart <= upper) {
            String range = (rangeStart == upper ? (upper + "") : (rangeStart + "->" + upper));
            ans.add(range);
        }

        return ans;
    }
}
