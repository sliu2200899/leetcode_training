package com.jetbrains.zCompanyInterviewPrep.Oracle;

public class DecodeString {
    int index = 0;
    public String decodeString(String s) {
        StringBuilder result = new StringBuilder();
        while (index < s.length() && s.charAt(index) != ']') {
            if (!Character.isDigit(s.charAt(index))) {
                result.append(s.charAt(index++));
            } else {
                int k = 0;
                // build k while next character is a digit
                while (index < s.length() && Character.isDigit(s.charAt(index))) {
                    k = k * 10 + s.charAt(index++) - '0';
                }

                index++;
                String decodeString = decodeString(s);

                index++;

                while (k-- > 0) {
                    result.append(decodeString);
                }
            }
        }

        return result.toString();
    }
}
