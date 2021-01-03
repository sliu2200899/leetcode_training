package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

public class LevenshteinDistance {
    // Levenshtein distance
    // means the least number of modification we need to change one string to another, the operation we can use is to add, delete and replace

    // at first, look at how to solve the problem using simple backtracking
    /*
    回溯是一个递归处理的过程。如果 a[i]与 b[j]匹配，我们递归考察 a[i+1]和 b[j+1]。
    如果 a[i]与 b[j]不匹配，那我们有多种处理方式可选：
        可以删除 a[i]，然后递归考察 a[i+1]和 b[j]；
        可以删除 b[j]，然后递归考察 a[i]和 b[j+1]；
        可以在 a[i]前面添加一个跟 b[j]相同的字符，然后递归考察 a[i]和 b[j+1];
        可以在 b[j]前面添加一个跟 a[i]相同的字符，然后递归考察 a[i+1]和 b[j]；
        可以将 a[i]替换成 b[j]，或者将 b[j]替换成 a[i]，然后递归考察 a[i+1]和 b[j+1]。
     */

    private char[] a = "mitcmu".toCharArray();
    private char[] b = "mtacnu".toCharArray();
    private int n = 6;
    private int m = 6;
    private int minDist = Integer.MAX_VALUE; // 存储结果
    // 调用方式 lwstBT(0, 0, 0);
    public void lwstBT(int i, int j, int edist) {
        if (i == n || j == m) {
            if (i < n) edist += (n-i);
            if (j < m) edist += (m - j);
            if (edist < minDist) minDist = edist;
            return;
        }
        if (a[i] == b[j]) { // 两个字符匹配
            lwstBT(i+1, j+1, edist);
        } else { // 两个字符不匹配
            lwstBT(i + 1, j, edist + 1); // 删除a[i]或者b[j]前添加一个字符
            lwstBT(i, j + 1, edist + 1); // 删除b[j]或者a[i]前添加一个字符
            lwstBT(i + 1, j + 1, edist + 1); // 将a[i]和b[j]替换为相同字符
        }
    }


    // based on the above program, we can get the recursion tree.
    // 每个节点代表一个状态，状态包含三个变量 (i, j, edist)，其中，edist 表示处理到 a[i]和 b[j]时，已经执行的编辑操作的次数。

    /*
        如果：a[i]!=b[j]，那么：min_edist(i, j)就等于：
        min(min_edist(i-1,j)+1, min_edist(i,j-1)+1, min_edist(i-1,j-1)+1)

        如果：a[i]==b[j]，那么：min_edist(i, j)就等于：
        min(min_edist(i-1,j)+1, min_edist(i,j-1)+1，min_edist(i-1,j-1))

        其中，min表示求三数中的最小值。
     */

    // refer to:  https://www.jianshu.com/p/12e9b9a9a350

    private static int minimum(int a,int b,int c){
        return Math.min(Math.min(a,b),c);
    }

    public static int computeLevenshteinDistance(CharSequence src,CharSequence dst){
        int[][] distance = new int[src.length() + 1][dst.length() + 1];

        for (int i = 0;i <= src.length();i++)
            distance[i][0] = i;
        for (int j = 0;j <= dst.length();j++)
            distance[0][j] = j;

        for (int i = 1;i <= src.length();i++){
            for (int j = 1;j <= dst.length();j++){
                int flag = (src.charAt(i - 1) == dst.charAt(j - 1)) ? 0 : 1;
                distance[i][j] = minimum(
                        distance[i - 1][j] + 1,
                        distance[i][j - 1] + 1,
                        distance[i - 1][j - 1] + flag);
            }
        }
        return distance[src.length()][dst.length()];
    }

    //测试方法
    public static void main(String args[]){
        String s1 = "xyzab";
        String s2 = "axyzc";

        String s3 = "等啊高原";
        String s4 = "阿登高原";

        String s5 = "xyz阿登高原";
        String s6 = "1y3等啊高原x";

        System.out.println("字符串(\"" + s1 + "\")和字符串(\"" + s2 + "\")的最小编辑距离为："+ computeLevenshteinDistance(s1,s2));
        System.out.println("字符串(\"" + s3 + "\")和字符串(\"" + s4 + "\")的最小编辑距离为："+ computeLevenshteinDistance(s3,s4));
        System.out.println("字符串(\"" + s5 + "\")和字符串(\"" + s6 + "\")的最小编辑距离为："+ computeLevenshteinDistance(s5,s6));

    }
}
