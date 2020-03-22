package com.jetbrains.master;

import java.util.ArrayList;
import java.util.List;

public class CreateTargetArray {
    public int[] createTargetArray(int[] nums, int[] index) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; ++i) {
            list.add(index[i], nums[i]);
        }

        int[] res = new int[nums.length];
        for (int i = 0; i < res.length; ++i) {
            res[i] = list.get(i);
        }
        return res;
    }
}
