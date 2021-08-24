package com.jetbrains.zCompanyInterviewPrep.amazon;

public class DecodeString {
    /*
         You are given an encoded string  and number of rows, Convert it to original string

         Input: mnesi___ya__k____mime  N = 3

         Output : my name is mike

         Explanation : Read the matrix in a diagonal way starting from [0][0] index until the end of row and start from the top
         again to decode it. _ are treated as space.

         m n e s i _ _

         _ y a _ _ k _

         _ _ _ m i m e
     */
    public String process(char[][] matrix, int N) {
        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0, rows = matrix.length, cols = matrix[0].length;

        while (j < cols) {
            int k = j;
            while (i < rows && k < cols) {
                sb.append(matrix[i][k] == '_' ? ' ' : matrix[i][k]);
                i++;
                k++;
            }
            i = 0;
            j++;
        }

        return sb.toString().trim();
    }
}
