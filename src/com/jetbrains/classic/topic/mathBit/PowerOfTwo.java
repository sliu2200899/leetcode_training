package com.jetbrains.classic.topic.mathBit;

public class PowerOfTwo {

    /*
        bit manipulation
     */
    public boolean isPowerOfTwo(int n) {
        if (n < 1) return false;

        return (n & (n - 1)) == 0;
    }
    /*

        summary of bit manipulation
            1. get the right most 1 bit
                x & (-x)
                Basically, that works because of two's complement. In two's complement notation -x−x is the same as ¬x+1. In other words, to compute -x−x one has to revert all bits in x and then to add 1 to the result.
            2. turn off right most 1 bit
                x & (x - 1)
                To subtract 1 means to change the rightmost 1-bit to 0 and to set all the lower bits to 1.


     */

    /*
        follow up: power of three

        base conversion
        All we need to do is convert [1] the number to base 3 and check if it is written as a leading 1 followed by all 0.

        A couple of built-in Java functions will help us along the way.
        1. String baseChange = Integer.toString(number, base);
        2. boolean matches = myString.matches("123");
     */
    public boolean isPowerOfThree(int n) {
        return Integer.toString(n, 3).matches("^10*$");
    }

    /*
        follow up: power of four
        math...
        x = 4 ^ a, then, a = log4 x = 1/2 * logx is an integer.
        Here let's simply check if logx is an even number..
     */
    public boolean isPowerOfFour(int num) {
        return (num > 0) && (Math.log(num) / Math.log(2) % 2 == 0);
    }
}
