package com.jetbrains.zCompanyInterviewPrep.karat;

import java.util.Arrays;
import java.util.List;

public class WordWrap {
    /*
        给一个word list 和最大的长度，要求把这些word用 - 串联起来，但不能超过最大的长度。
     */
    public static String wrap(List<String> words, int maxLen) {
        StringBuilder builder = new StringBuilder();
        for (String word : words) {
            if (builder.length() + word.length() + 3 > maxLen) {
                break;
            }
            builder.append(word).append(" - ");
        }
        if (builder.length() != 0) {
            builder.setLength(builder.length() - 3);
        }
        return builder.toString();
    }
    public static void main(String[] args) {
        List<String> words = Arrays.asList("shu", "liu", "get", "home");
        System.out.println(wrap(words, 18));
    }
}
