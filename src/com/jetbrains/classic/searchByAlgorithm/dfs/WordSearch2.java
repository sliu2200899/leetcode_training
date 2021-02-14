package com.jetbrains.classic.searchByAlgorithm.dfs;

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
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        if (board == null || board.length == 0 || board[0].length == 0) return res;

        int m = board.length, n = board[0].length;
        for (String s: words) {
            insert(s);
        }

        boolean[][] visited = new boolean[m][n];
        for (int i = 0;  i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                dfs(board, res, i, j, visited, root);
            }
        }
        return res;
    }

    private void dfs(char[][] board, List<String> res, int row, int col, boolean[][] visited, TrieNode parent) {
        // base case
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || visited[row][col] || parent.children[board[row][col] - 'a'] == null) {
            return;
        }

        // recursive case
        Character letter = board[row][col];
        TrieNode curNode = parent.children[letter - 'a'];

        if (curNode.isTerminated) {
            res.add(curNode.word);
            curNode.word = null;
            curNode.isTerminated = false;
        }

        visited[row][col] = true;

        for (int[] pair : pairs) {
            int x = pair[0] + row;
            int y = pair[1] + col;

            dfs(board, res, x, y, visited, curNode);
        }

        visited[row][col] = false;
    }

    private TrieNode root = new TrieNode('/');

    private void insert(String s) {
        TrieNode p = root;
        for (int i = 0; i < s.length(); ++i) {
            int index = s.charAt(i) - 'a';
            if (p.children[index] == null) {
                TrieNode newNode = new TrieNode(s.charAt(i));
                p.children[index] = newNode;
            }

            p = p.children[index];
        }
        p.isTerminated = true;
        p.word = s;
    }


    private class TrieNode {
        public char data;
        public TrieNode[] children = new TrieNode[26];
        public boolean isTerminated = false;
        public String word;

        public TrieNode(char data) {
            this.data = data;
        }
    }

    private int[][] pairs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
}
