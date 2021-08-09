package com.jetbrains.classic.topic.stringMatch;

public class ValidNumber {
    /*

    What are all of the possible character groups, and what can we say about them?
    Reading through the problem statement very carefully, we can ascertain:

    1.Digits (one of ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"])
        Both decimal numbers and integers must contain at least one digit.
        rules:
        First of all, there must always be at least one digit in the input for it to form
        a valid number. Let's use a variable seenDigit to indicate whether we have seen a digit yet.

    2.A sign ("+" or "-")
        Sign characters are optional for both decimal numbers and integers, but if one is present,
        it will always be the first character. Note, this means that a sign character can also appear
        immediately after an exponent.

        rules:
        If a sign is present, it must be the first character in a decimal number or integer.
        In a valid number, there are two possible locations for these signs - at the front of the number,
        or right after an exponent ("e" or "E") e.g., -63e+7. Therefore, if we see a sign, and it is not
        the first character of the input, and does not come immediately after an exponent ("e" or "E"),
        then we know the number is not valid.

    3.An exponent ("e" or "E")
        Exponents are also optional, but if the string contains one then it must be after a decimal
        number or an integer.

        An integer must follow the exponent.

        rules:
        There cannot be more than one exponent in a valid number, so we will use a variable seenExponent
        to indicate whether we have already seen an exponent.
        An exponent must appear after a decimal number or an integer. This means if we see an exponent,
        we must have already seen a digit.

    4.A dot (".")
        A decimal number should only contain one dot. Integers cannot contain dots.

        rules:
        There cannot be more than one dot in a valid number, since only integers are allowed after an exponent,
        so there cannot be more than one decimal number. We will use a variable seenDot to indicate whether
        we have seen a dot.
        If we see a dot appear after an exponent, the number is not valid, because integers cannot have dots.

    5.Anything else
        There will never be anything else in a valid number.
*/
    public boolean isNumber(String s) {
        int n = s.length();

        boolean seenDigit = false, seenExponent = false, seenDot = false;

        for (int i = 0; i < n; ++i) {
            char cur = s.charAt(i);

            if (Character.isDigit(cur)) {
                seenDigit = true;
            } else if (cur == '+' || cur == '-') {
                if (i > 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') {
                    return false;
                }
            } else if (cur == 'e' || cur == 'E') {
                if (seenExponent || !seenDigit) {
                    return false;
                }
                seenExponent = true;
                seenDigit = false;
            }

            else if (cur == '.') {
                if (seenDot || seenExponent) {
                    return false;
                }
                seenDot = true;
            } else {
                return false;
            }
        }

        return seenDigit;
    }
}
