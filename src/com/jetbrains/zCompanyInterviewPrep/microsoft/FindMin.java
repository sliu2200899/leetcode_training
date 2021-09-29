package com.jetbrains.zCompanyInterviewPrep.microsoft;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class FindMin {
    /*
            public int findMin(int[] A) {
                int ans = 0;
                for (int i = 1; i < A.length; ++i) {
                    if (A[i] < ans) {
                        ans = A[i];
                    }
                }
                return ans;
            }

            has some problem, write code to generate int[] which is counter example
     */
    private Random generator = new Random();

    public int[] counterExample(int N) {
        int[] res = new int[N];
        Random rand = new Random();

        for (int i = 0; i < N; ++i) {
            res[i] = generator.nextInt(Integer.MAX_VALUE) + 1;
        }

        return res;
    }

    public static void main(String[] args) {
        FindMin f = new FindMin();
        System.out.println(Arrays.toString(f.counterExample(5)));
    }

}
