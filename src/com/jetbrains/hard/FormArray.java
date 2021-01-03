package com.jetbrains.hard;

import java.util.HashMap;
import java.util.Map;

public class FormArray {
    public boolean canFormArray(int[] arr, int[][] pieces) {
        if (arr == null || arr.length == 0 || pieces == null ||
                pieces.length == 0 || pieces[0].length == 0) return false;

        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < pieces.length; ++i) {
            map.put(pieces[i][0], pieces[i]);
        }

        int i = 0;
        while (i < arr.length) {
            // find target piece
            if (!map.containsKey(arr[i])) {
                return false;
            }
            //chekc target piece
            int[] target = map.get(arr[i]);
            for (int x : target) {
                if (x != arr[i]) {
                    return false;
                }
                i++;
            }
        }

        return true;
    }
}
