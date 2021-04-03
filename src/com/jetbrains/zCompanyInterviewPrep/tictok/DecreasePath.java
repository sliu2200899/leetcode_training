package com.jetbrains.zCompanyInterviewPrep.tictok;

public class DecreasePath {
    /*
        [面试经验] 新鲜字节，求解答
        refer to:  https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=721529&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D47%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
        在二维数组中找到最长的严格递减的路径
        {
        {1,2,3},
        {1,1,1},
        {1,1,1}
        }

        similar to 329
        dfs + memo

        time: O(mn). Each cell will be calculated once and only once. and each edge will be visited once and only once.
        space: O(mn). memo cache to store all the intermediat value
     */
    public int longestIncreasingPath(int[][] matrix) {
        // longest -> dfs
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        Integer[][] memo = new Integer[matrix.length][matrix[0].length];
        int len = 1;
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                len = Math.max(len, dfs(i, j, matrix, memo));
            }
        }

        return len;
    }

    private int dfs(int row, int col, int[][] matrix, Integer[][] memo) {
        if (memo[row][col] != null) {
            return memo[row][col];
        }

        int[][] pairs = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};

        int ans = 1;
        for (int[] pair : pairs) {
            int newx = pair[0] + row;
            int newy = pair[1] + col;

            if (newx < 0 || newx >= matrix.length || newy < 0 || newy >= matrix[0].length) continue;
            if (matrix[newx][newy] > matrix[row][col]) {
                ans = Math.max(ans, dfs(newx, newy, matrix, memo) + 1);
            }
        }


        memo[row][col] = ans;
        return ans;
    }

}
