package com.jetbrains.zCompanyInterviewPrep.amazon.vo;

import java.util.ArrayList;
import java.util.List;

public class CountLetters {
    public static List<String> process(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (Character.isLetter(ch)) {
                sb.append((ch + "").toLowerCase());
            }
        }

        String newS = sb.toString();
        List<String> res = new ArrayList<>();
        for (int i = 0; i < newS.length() - (n - 1); ++i) {  // newS.length() - n - 1
            res.add(newS.substring(i, i + n));   // i + n == newS.length()   i < newS.length() - n + 1
        }

        return res;
    }
    public static void main(String[] args) {
        for(String s : process("How are you lar? I am Fine.", 5)) {
            System.out.println(s);
        }
    }
}
