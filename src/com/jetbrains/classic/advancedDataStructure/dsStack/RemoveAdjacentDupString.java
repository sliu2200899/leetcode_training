package com.jetbrains.classic.advancedDataStructure.dsStack;

public class RemoveAdjacentDupString {

    /*
        Solving the problem using Stack
        Stack is suitable for the situation like need to delay processing elements or do some computation in the middle
        another thing need to notice is that we can use StringBuilder to act as Stack
     */
    public String removeDuplicates(String S) {
        StringBuilder sb = new StringBuilder();
        int len = 0;
        for (char c : S.toCharArray()) {
            if (len != 0 && c == sb.charAt(len - 1)) {
                sb.deleteCharAt(len - 1);
                len--;
            }
            else {
                sb.append(c);
                len++;
            }
        }
        return sb.toString();
    }
}
