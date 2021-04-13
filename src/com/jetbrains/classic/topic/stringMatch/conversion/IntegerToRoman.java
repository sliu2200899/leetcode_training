package com.jetbrains.classic.topic.stringMatch.conversion;

public class IntegerToRoman {
    /*
        greedy
        clarify:
            1. input, output, example
            2.

        algo:
            rules:
                1. 1-3.  III
                2.
            handling ambiguity
                there are several ways to represent a num
                    140
                        L + L + XL
                        C + X + X + X + X
                        C + XL  =>.  140. yes
                        ...
            So to represent a given integer, we look for the largest symbol that fits into it.
            We subtract that, and then look for the largest symbol that fits into the remainder,
            and so on until the remainder is 0. Each of the symbols we take out are appended onto the output Roman Numeral string.


        time: O(1)
        space: O(1)
     */
    private static final int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] symbols =  {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        // loop through each symbol, stopping if num becomes 0
        for (int i = 0; i < values.length && num > 0; ++i) {
            // repeat while the current symbol still fits into num
            while (values[i] <= num) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }
}
