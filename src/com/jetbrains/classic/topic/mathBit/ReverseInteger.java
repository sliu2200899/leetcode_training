package com.jetbrains.classic.topic.mathBit;

import java.util.Stack;

public class ReverseInteger {
    /*
    clarify:
        1. input, output, example
        2. can I use long variable here   no
        3.
    alog:
        w/ stack
        w/o. stack, array.   use match to solve the problem
            // pop operation
            pop = x % 10;
            x /= 10;

            // push operation
            temp = rev * 10 + pop;
            rev = temp;

            temp = rev * 10 + pop can cause overflow.
            rev * 10 + pop > max   =>.  rev > max / 10 or (rev == max/10 and pop > 7).    2^31 last digit is 8 2^1, 2^2, 2^3, 2^4.      2, 4, 8, 6,    2, 4, 8, 6
            rev * 10 + pop < min   =>  ...

*/
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }

        return rev;
    }

    /*
        alternative:  string + stack
     */
    public int reverse2(int x) {
        String s = "" + x;
        double tmp = 0;
        Stack<Integer> numStack = new Stack();
        if(x < 0){
            s = s.substring(1);
        }
        for(int i=0; i<s.length(); i++){
            numStack.push(Integer.parseInt("" + s.charAt(i)));
        }
        for(int i=s.length()-1; !numStack.empty(); i--) {
            int num = numStack.pop();
            tmp += num * Math.pow(10, i);
            if ((int)(tmp) != tmp) {
                return 0;
            }
        }
        return x>0 ? (int)tmp : (int)(0-tmp);
    }
}
