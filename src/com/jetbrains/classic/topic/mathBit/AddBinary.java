package com.jetbrains.classic.topic.mathBit;

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
        int idxa = a.length() - 1, idxb = b.length() - 1;
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (idxa >= 0 || idxb >= 0) {
            int numa = (idxa < 0 ? 0 : a.charAt(idxa) - '0');
            int numb = (idxb < 0 ? 0 : b.charAt(idxb) - '0');

            int num = numa + numb + carry;
            carry = num / 2;

            sb.insert(0, num % 2);
            if (idxa >= 0) idxa--;
            if (idxb >= 0) idxb--;
        }
        if (carry != 0) {
            sb.insert(0, carry);
        }

        return sb.toString();
    }

    /*
        follow up from facebook:
            provides you two numbers and asks to sum them up without using addition operation.
            bit manipulation
     */

}
