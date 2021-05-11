package com.jetbrains.classic.topic.stringMatch.conversion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReverseWordsInString {
    /*
        basics:
            Given an input string s, reverse the order of the words.

            A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.

            Return a string of the words in reverse order concatenated by a single space.

            Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.
     */

    public String reverseWords(String s) {
        List<String> list = new ArrayList<>(Arrays.asList(s.trim().split("\\s+")));
        Collections.reverse(list);
        return String.join(" ", list);
    }

    /*
        follow up:
            Given a character array s, reverse the order of the words.

            A word is defined as a sequence of non-space characters. The words in s will be separated by a single space.

            Your code must solve the problem in-place, i.e. without allocating extra space.

            Constraints:

            1 <= s.length <= 105
            s[i] is an English letter (uppercase or lowercase), digit, or space ' '.
            There is at least one word in s.
            s does not contain leading or trailing spaces.
            All the words in s are guaranteed to be separated by a single space.
     */

    public void reverseWords2(char[] s) {
        // reverse character in each word
        int j = 0;
        for (int i = 0; i < s.length; ++i) {
            if (s[i] == ' ') {
                reverseChars(s, j, i - 1);
                j = i + 1;
            } else if (i == s.length - 1) {
                reverseChars(s, j, i);
            }
        }

        // reverse all the chars in the char array
        reverseChars(s, 0, s.length - 1);
    }

    private void reverseChars(char[] s, int start, int end) {
        while (start < end) {
            char tmp = s[start];
            s[start] = s[end];
            s[end] = tmp;

            start++;
            end--;
        }
    }
}
