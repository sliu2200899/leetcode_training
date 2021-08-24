package com.jetbrains.zCompanyInterviewPrep.amazon;

//Input string composed by '(', ')', '[', ']' and '?'
//Return how many different method to split input string and get two substring which composed by balanced parentheses.
//Character in substring can switch position and '?' can be treat as any kind of parenthes.
//Example: Input: "]()[?[]]"
//Should return 1
//Because there is only one way to split input at index 4, first substring "]()[" can be transformed to balanced parentheses "()[]" by switch position. And second substring "?[]]" can be transformed to balanced parentheses "[[]]" by replace '?' with '['.
public class SplitParenthesis {
    public static int splitParentheses(String input) {
        int res = 0;
        for (int i = 1; i + 1 < input.length(); i++) {
            if (isBalanced(input.substring(0,i)) && isBalanced(input.substring(i)))
                res++;
        }
        return res;
    }

    private static boolean isBalanced(String input) {
        if (input == null || input.length() == 0 || input.length() % 2 != 0) {
            return false;
        }
        int[] par = new int[5];
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                if (par[1] > 0) par[1]--;
                else par[0]++;
            } else if (input.charAt(i) == ')') {
                if (par[0] > 0) par[0]--;
                else par[1]++;
            } else if (input.charAt(i) == '[') {
                if (par[3] > 0) par[3]--;
                else par[2]++;
            } else if (input.charAt(i) == ']') {
                if (par[2] > 0) par[2]--;
                else par[3]++;
            }
            else if (input.charAt(i) == '?')
                count++;
        }

        int sum = 0;
        for (int i : par) {
            sum += i;
        }

        if (sum == 0 && count % 2 == 0)
            return true;
        else {
            if (sum == count)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String input = "]()[?[]]";
        //String input = "()[]??()";
        System.out.println(splitParentheses(input));
    }
}