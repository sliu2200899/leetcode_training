package com.jetbrains.master;

import java.util.HashSet;
import java.util.Set;

public class SumFourDivisors {
    public int sumFourDivisors(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int sum = 0;
        for (int i = 0; i < nums.length; ++i) {
            sum += getDivisors(nums[i]);
        }

        return sum;
    }

    private int getDivisors(int num) {
        Set<Integer> set = new HashSet<>();

        int i = 1;
        while (i * i <= num) {
            if (num % i == 0) {
                set.add(i);
                set.add(num / i);
            }
            i++;
        }

        int sum = 0;
        if (set.size() == 4) {
            for (Integer item : set) {
                sum += item;
            }
        }

        return sum;
    }
}
