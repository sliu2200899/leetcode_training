package com.jetbrains.classic.topic.stringMatch.conversion;

public class MyAtoi {
    /*
    clarify:
        1. input, output, example
        2.
    algo:
        rules:
            1. no leading whitespace. trim()
            2. -, + need to consider
            3. digits one by one
            4. convert these digits to the integer
            5. 32-bit signed integer range...  deal with some overflow situation
            6. return

    approach 1: check the overflow before hand...

*/
    public int myAtoi(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int i = 0;
        boolean positive = true;

        while (i < s.length() && s.charAt(i) == ' ') {
            i++;
        }

        if (i < s.length() && (s.charAt(i) == '-' || s.charAt(i) == '+')) {
            positive = (s.charAt(i) == '-' ? false : true);
            i++;
        }

        if (i < s.length() && !Character.isDigit(s.charAt(i))) {
            return 0;
        }

        int result = 0;
        while(i < s.length() && Character.isDigit(s.charAt(i))) {
            if (result > Integer.MAX_VALUE / 10 ||
                    ((result == Integer.MAX_VALUE / 10) && (s.charAt(i) - '0' > Integer.MAX_VALUE % 10))) {
                return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = result * 10 + (s.charAt(i) - '0');
            ++i;
        }
        return positive ? result : -result;
    }

    /*
        approach 2: just check the overflow situation afterward
     */
    public int myAtoi2(String str) {
        int index = 0, sign = 1, total = 0;
        if(str.length() == 0) return 0;

        while(index < str.length() && str.charAt(index) == ' ')
            index++;

        if(index < str.length() && (str.charAt(index) == '+' || str.charAt(index) == '-')) {
            sign = str.charAt(index) == '+' ? 1 : -1;
            index++;
        }

        if(index < str.length() && !Character.isDigit(str.charAt(index))) return 0;

        int result = 0;
        while(index < str.length()) {
            if(!Character.isDigit(str.charAt(index))) break;
            char current = str.charAt(index++);
            int previous = result;
            result *= 10;
            if(previous != result/10) {
                return sign == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            result += (current - '0');
            if(result < 0) {
                return sign == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
        }
        return result * sign;
    }
}
