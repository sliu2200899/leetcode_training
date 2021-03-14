package com.jetbrains.classic.advancedDataStructure.dsTrie;

import java.util.ArrayList;
import java.util.List;

public class WordSearch2 {

    /*
        time: O(M * 4 * 3 ^(L - 1)))  where M is the number of cells in the board, and L is the maximum length of words
              The algorithm loops over all the cells in the board, therefore we have M as a factor in hte complexity formula.

        space: O(N)   where N is the total number of letters in the dictionary
     */


    /*
        algo:
            build trie based on the words
            for loop the matrix
                dfs()

            dfs()
                // base case
                if cell's isTerminated == true
                    add cell string to list

                if (trie's node == null)
                    return false

                // recursive case
                for all 4 directions
                    generate newNode
                    dfs(newNode)

    */
    private class TrieNode {
        String word;
        TrieNode[] children;
        public TrieNode() {
            this.children = new TrieNode[26];
        }
    }

    private TrieNode root = new TrieNode();

    public List<String> findWords(char[][] board, String[] words) {
        // build the trie data structure based on the words
        for (String word : words) {
            insert(word);
        }

        // traverse the board matrix in depth first search manner based on the trie
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        List<String> list = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                dfs(board, i, j, visited, list, root);
            }
        }
        return list;
    }

    private void insert(String word) {
        char[] charArr = word.toCharArray();
        TrieNode p = root;
        for (int i = 0; i < charArr.length; ++i) {
            int index = charArr[i] - 'a';
            if (p.children[index] == null) {
                p.children[index] = new TrieNode();
            }
            p = p.children[index];
        }
        p.word = word;
    }

    private void dfs(char[][] board, int row, int col, boolean[][] visited, List<String> list, TrieNode parent) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || visited[row][col] || parent.children[board[row][col] - 'a'] == null) {
            return;
        }

        char c = board[row][col];
        TrieNode cur = parent.children[c - 'a'];

        if (cur.word != null) {
            list.add(cur.word);
            cur.word = null;
        }

        visited[row][col] = true;

        for (int[] pair : pairs) {
            int x = pair[0] + row;
            int y = pair[1] + col;

            dfs(board, x, y, visited, list, cur);
        }

        visited[row][col] = false;
    }

    private int[][] pairs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
}
