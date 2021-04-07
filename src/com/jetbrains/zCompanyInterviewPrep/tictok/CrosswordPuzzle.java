package com.jetbrains.zCompanyInterviewPrep.tictok;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CrosswordPuzzle {
    private int ways = 0;

    private void solve(String[] matrix, List<String> words, int index, int n) {
        if (index == words.size()) {
            ways++;
            return;
        }

        // loop to check the words that can align vertically
//        for (int i = 0; i < n; ++i) {
//            for (int j = )
//        }

        // loop to check the words that can align horizontally
    }

    public static void main(String[] args) {
        String[] matrix = {"*#********", "*#********", "*#****#***", "*##***##**", "*#****#***", "*#****#***", "*#****#***", "*#*######*", "*#********", "***#######"};
        List<String> words = new ArrayList<>(Arrays.asList("PUNJAB", "JHARKHAND", "MIZORAM", "MUMBAI"));
        CrosswordPuzzle cp = new CrosswordPuzzle();
        cp.solve(matrix, words, 0, 10);

        System.out.println();
    }
}
