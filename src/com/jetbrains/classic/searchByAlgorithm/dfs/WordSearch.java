package com.jetbrains.classic.searchByAlgorithm.dfs;

public class WordSearch {
    /*
    backtracking
    We argue that a more accurate term to summarize the solution would be backtracking,
    which is a methodology where we mark the current path of exploration, if the path does
    not lead to a solution, we then revert the change (i.e. backtracking) and try another path.

    For the backtracking function backtrack(row, col, suffix), as a DFS algorithm, it is often implemented as a recursive function. The function can be broke down into the following four steps:

        Step 1). At the beginning, first we check if we reach the bottom case of the recursion,
                where the word to be matched is empty, i.e. we have already found the match for each prefix of the word.

        Step 2). We then check if the current state is invalid, either the position of the cell
                is out of the boundary of the board or the letter in the current cell does not match with the first letter of the word.

        Step 3). If the current step is valid, we then start the exploration of backtracking with
                the strategy of DFS. First, we mark the current cell as visited, e.g. any non-alphabetic
                letter will do. Then we iterate through the four possible directions, namely up, right, down and left.
                The order of the directions can be altered, to one's preference.

        Step 4). At the end of the exploration, we revert the cell back to its original state.
                Finally we return the result of the exploration.

    time: O(N * 3 ^ L) where N is the number of cells in the board and L is the length of the word to be matched.
          For the backtracking function, initially we could have at most 4 directions to explore,
          but further the choices are reduced into 3 (since we won't go back to where we come from).
          As a result, the execution trace after the first step could be visualized as a 3-ary tree,
          each of the branches represent a potential exploration in the corresponding direction.
          Therefore, in the worst case, the total number of invocation would be the number of nodes
          in a full 3-nary tree, which is about 3^L3

    space:  O(L) where L is the length of the word to be matched. if we can alter the original matrix...

     */
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0 || word == null || word.length() == 0)  return false;

        int m = board.length, n = board[0].length;

        boolean[][] isVisited = new boolean[m][n];

        // we use double for loop because the problem ask us to check if there exist some words
        // but we don't know where to start, in this case, we should use double for loop
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (dfs(board, i, j, word, 0, isVisited) ) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int i, int j, String word, int index, boolean[][] isVisited)  {
        // step 1: check the base case
        if (index >= word.length())
            return true;

        // step 2: check the boundaries     // in this case, we should put the checking here, but for other situation we need to put it inside the for loop
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(index) || isVisited[i][j])
            return false;

        // step 3: explore the neighbors in DFS
        isVisited[i][j] = true;  // board[i][j] = '#';

        for (int[] pair : pairs) {
            int newX = pair[0] + i;
            int newY = pair[1] + j;

            if(dfs(board, newX, newY, word, index + 1, isVisited)) {
                return true;
            }
        }

        isVisited[i][j] = false;  // board[i][j] = word.charAt(index);

        return false;
    }

    private int[][] pairs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
}
