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
            what if we cannot use built-in methods?

        algo:
            reverse teh words in the stringbuilder...
            private void reverse(StringBuilder sb, int left, int right) {}
     */

    public String reverseWords2(String s) {
        // convert string to stringBuilder
        // and trim spaces at the same time
        StringBuilder sb = trimSpaces(s);

        // reverse teh whole string
        reverse(sb, 0, sb.length() - 1);

        // reverse each word
        reverseEachWord(sb);

        return sb.toString();
    }

    private StringBuilder trimSpaces(String s) {
        int left = 0, right = s.length() - 1;
        // remove leading spaces
        while (left <= right && s.charAt(left) == ' ') ++left;

        // remove trailing spaces
        while (left <= right && s.charAt(right) == ' ') --right;

        // reduce multiple spaces to single one
        StringBuilder sb = new StringBuilder();
        while (left <= right) {
            char ch = s.charAt(left);

            if (ch != ' ') sb.append(ch);
            else if (sb.charAt(sb.length() - 1) != ' ') sb.append(ch);

            ++left;
        }

        return sb;
    }

    private void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            char tmp = sb.charAt(left);
            sb.setCharAt(left++, sb.charAt(right));
            sb.setCharAt(right--, tmp);
        }
    }

    private void reverseEachWord(StringBuilder sb) {
        int n = sb.length();
        int start = 0, end = 0;

        while (start < n) {
            // go to the end of the word
            while (end < n && sb.charAt(end) != ' ') ++end;

            reverse(sb, start, end - 1);

            start = end + 1;

            ++end;
        }
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

    public void reverseWords3(char[] s) {

        // reverse all the chars in the char array
        reverseChars(s, 0, s.length - 1);

        // reverse each word
        reverseEachWord(s);
    }

    private void reverseEachWord(char[] s) {
        int n = s.length;
        int start = 0, end = 0;
        while (start < n) {
            while (end < n && s[end] != ' ') end++;
            reverseChars(s, start, end - 1);
            start = end + 1;
            end++;
        }
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
