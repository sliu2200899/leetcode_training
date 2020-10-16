package com.jetbrains.master;

import java.util.HashMap;
import java.util.Map;

public class StrobogrammticNumber {

    // recursive approach
    public boolean isStrobogrammatic(String num) {
        // 0, 1, 8, 6, 9
        if (num == null || num.length() == 0) return true;
        if (num.length() == 1) {
            if (num.charAt(0) == '1' || num.charAt(0) == '8' || num.charAt(0) == '0')
                return true;
            else
                return false;
        }

        int start = 0, end = num.length() - 1;
        if (!(num.charAt(start) == '1' && num.charAt(end) == '1') &&
                !(num.charAt(start) == '8' && num.charAt(end) == '8') &&
                !(num.charAt(start) == '6' && num.charAt(end) == '9') &&
                !(num.charAt(start) == '9' && num.charAt(end) == '6') &&
                !(num.charAt(start) == '0' && num.charAt(end) == '0'))
            return false;

        if (start + 1 == end) return true;

        return isStrobogrammatic(num.substring(start + 1, end));
    }

    // iteratvie approvach
    public boolean isStrobogrammatic2(String num) {

        Map<Character, Character> map = new HashMap<>();
        map.put('6', '9');
        map.put('9', '6');
        map.put('0', '0');
        map.put('1', '1');
        map.put('8', '8');

        // 101
        int l = 0, r = num.length() - 1;
        while (l <= r) {
            if (!map.containsKey(num.charAt(l)) ||
                    map.get(num.charAt(l)) != num.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }

        return true;
    }
}
