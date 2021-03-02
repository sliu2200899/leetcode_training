package com.jetbrains.classic.topic.mathBit;

import java.util.HashMap;
import java.util.Map;

public class NumPairsDivisibleBy60 {
    /*
        two sum variants
     */
    public int numPairsDivisibleBy60(int[] time) {
        for(int i = 0; i < time.length; i ++){
            time[i] = time[i] % 60;
        }
        int result = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < time.length; i ++){
            int cur = time[i];
            int remain = cur == 0 ? 0 : 60 - cur;
            if(map.containsKey(remain)){
                result += map.get(remain);
            }
            map.put(time[i], map.getOrDefault(time[i], 0) + 1);
        }
        return result;
    }

    /*
        using math
     */
    public int numPairsDivisibleBy602(int[] time) {
        int remainders[] = new int[60];
        int count = 0;
        for (int t: time) {
            if (t % 60 == 0) { // check if a%60==0 && b%60==0
                count += remainders[0];
            } else { // check if a%60+b%60==60
                count += remainders[60 - t % 60];
            }
            remainders[t % 60]++; // remember to update the remainders
        }
        return count;
    }

}
