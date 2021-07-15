package com.jetbrains.classic.topic.mathBit;

public class StringToInteger {
    /*
        clarify:
            1. input, output, example
            2. rules
                1. no leading whitespace. trim()
                2. -, + need to consider
                3. digits one by one
                4. convert these digits to the integer
                5. 32-bit signed integer range...  deal with some overflow situation
                6. return
        algo:



    */
    public int myAtoi(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int i = 0;
        boolean positive = true;
        // deal with all of the leading zeros
        while (i < s.length() && s.charAt(i) == ' ') {
            i++;
        }

        // deal with the negative or positive sign
        if (i < s.length() && (s.charAt(i) == '-' || s.charAt(i) == '+')) {
            positive = (s.charAt(i) == '+');
            i++;
        }

        if (i < s.length() && !Character.isDigit(s.charAt(i))) {
            return 0;
        }

        int result = 0;
        while (i < s.length() && Character.isDigit(s.charAt(i)) ) {
            int num = (s.charAt(i) - '0');

            if ((result > Integer.MAX_VALUE / 10) ||
                    ((result == Integer.MAX_VALUE / 10) && (s.charAt(i) - '0' > Integer.MAX_VALUE % 10))) {
                return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            result = result * 10 + num;
            ++i;
        }

        return positive ? result : -result;
    }
}
