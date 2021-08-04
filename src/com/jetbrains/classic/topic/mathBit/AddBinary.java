package com.jetbrains.classic.topic.mathBit;

import java.math.BigInteger;

public class AddBinary {
    /*
        bit by bit computation

        That's a good old classical algorithm, and there is no conversion from binary string to decimal and back here. Let's consider the numbers bit by bit starting from the lowest one and compute the carry this bit will add.
        Start from carry = 0. If number a has 1-bit in this lowest bit, add 1 to the carry. The same for number b: if number b has 1-bit in the lowest bit, add 1 to the carry. At this point the carry for the lowest bit could be equal to
        (00)2, (01)2, or (10)2
        Now append the lowest bit of the carry to the answer, and move the highest bit of the carry to the next order bit.

        time: O(max(n, m)) where n and m are lengths of the input strings a and b
        space: O(max(n, m))
     */
    public String addBinary(String a, String b) {
        // bit by bit computation
        StringBuilder sb = new StringBuilder();
        int idxa = a.length() - 1, idxb = b.length() - 1;
        int carry = 0;
        while (idxa >= 0 || idxb >= 0 || carry == 1) {
            int numA = (idxa < 0 ? 0 : a.charAt(idxa) - '0');
            int numB = (idxb < 0 ? 0 : b.charAt(idxb) - '0');

            int num = (numA + numB + carry);
            sb.insert(0, num % 2);

            carry = num / 2;
            if (idxa >= 0) idxa--;
            if (idxb >= 0) idxb--;
        }

        return sb.toString();
    }

    /*
        follow up from facebook:
            provides you two numbers and asks to sum them up without using addition operation.
            bit manipulation

        Algorithm
            Just try to summarize in my own words:

            1.sum without considering carry: x ^ y (XOR)
            2.get the carry bits: x & y (AND)
            3.shift carry bits 1 bit left: x & y << 1. so that carry is applied to the right position
            4.By above step. x + y becomes "sum without carries" + "all the carries"
            5.we repeat 1 - 3 in the loop. "sum without carries" + "all the carries" until carries becomes 0.
            6.When carries = 0, "sum without carries" is the actual sum.

        time:  O(N + M)  where N and M are the length of the input string a and b
        space: O(max(N, M))
     */
    public String addBinary2(String a, String b) {
        BigInteger x = new BigInteger(a, 2);
        BigInteger y = new BigInteger(b, 2);

        BigInteger zero = new BigInteger("0", 2);
        BigInteger carry, answer;
        while (y.compareTo(zero) != 0) {
            answer = x.xor(y);
            carry = x.and(y).shiftLeft(1);

            x = answer;
            y = carry;
        }

        return x.toString(2);  // return String value of x using radix 2
    }
}
