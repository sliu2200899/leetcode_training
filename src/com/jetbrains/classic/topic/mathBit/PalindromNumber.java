package com.jetbrains.classic.topic.mathBit;

public class PalindromNumber {
    public boolean isPalindrome(int x) {

        /*

            clarify:
                1. input, output, example,

            algo:
                First of all we should take care of some edge cases. All negative numbers are not palindrome,
                for example: -123 is not a palindrome since the '-' does not equal to '3'. So we can return false for all negative numbers.

                Now let's think about how to revert the last half of the number. For number 1221, if we do 1221 % 10, we get the last digit 1,
                to get the second to the last digit, we need to remove the last digit from 1221, we could do so by dividing it by 10, 1221 / 10 = 122.
                Then we can get the last digit again by doing a modulus by 10, 122 % 10 = 2, and if we multiply the last digit by 10 and add the second last digit,
                1 * 10 + 2 = 12, it gives us the reverted number we want. Continuing this process would give us the reverted number with more digits.

                Now the question is, how do we know that we've reached the half of the number?


            test:


            time: O(1)
            space:  O(1)

        */
        if (x < 0) return false;

        // create a stack...
        int origNum = x;
        int reverseNum = 0;
        while (x != 0) {
            int num = x % 10;

            if (reverseNum > Integer.MAX_VALUE / 10 || ((reverseNum == (Integer.MAX_VALUE / 10)) && (num >= 8))) {
                return false;
            }
            reverseNum = reverseNum * 10 + num;

            x = x / 10;
        }

        return origNum == reverseNum;
    }
}
