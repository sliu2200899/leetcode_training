package com.jetbrains.zCompanyInterviewPrep.microsoft;

public class IsAblePair {
    public boolean isPair(int[] A) {
        int a = 0;
        for (int x : A) {
            a ^= x;
        }
        return a == 0;
    }

    public static void main(String[] args) {
        IsAblePair solution = new IsAblePair();
        int[] A = {1,2,2,3};
        System.out.println(solution.isPair(A));
    }

}
