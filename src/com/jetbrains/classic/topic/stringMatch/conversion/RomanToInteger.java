package com.jetbrains.classic.topic.stringMatch.conversion;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
    /*
    clarify:
        1, input, output, example
            can we assume the input string is valid roman numerals?  yes
            range of the input.   1<= x <= 3999

    algo:

        s = "LVIII"

        LVIII. in terms of the number it represents,  sorted in descending order

        s = "IX"

        IX.   sorted in ascending order

        s = "MCMXCIV"

        because we only have 6 types of subtraction
        enumerate all of the commbinations for a number

        iterate over the string
            first check two characters then check one character
            convert the substring to num
            sum the num

        return sum
*/
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int num = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (i + 1 < s.length() && map.containsKey(s.substring(i, i + 2))) {
                num += map.get(s.substring(i, i + 2));
                i++;
            } else {
                num += map.get(s.charAt(i) + "");
            }
        }
        return num;
    }

    static Map<String, Integer> map = new HashMap<>();

    static {
        map.put("M", 1000);
        map.put("D", 500);
        map.put("C", 100);
        map.put("L", 50);
        map.put("X", 10);
        map.put("V", 5);
        map.put("I", 1);
        map.put("IV", 4);
        map.put("IX", 9);
        map.put("XL", 40);
        map.put("XC", 90);
        map.put("CD", 400);
        map.put("CM", 900);
    }
}
