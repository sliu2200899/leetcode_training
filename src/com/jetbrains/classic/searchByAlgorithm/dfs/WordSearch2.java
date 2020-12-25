package com.jetbrains.classic.searchByAlgorithm.dfs;

import java.util.ArrayList;
import java.util.List;

public class WordSearch2 {

    /*
        time: O(M * 4 * 3 ^(L - 1)))  where M is the number of cells in the board, and L is the maximum length of words
              The algorithm loops over all the cells in the board, therefore we have M as a factor in hte complexity formula.

        space: O(N)   where N is the total number of letters in the dictionary
     */


    private TrieNode root = new TrieNode();

    ArrayList<String> _result = new ArrayList<String>();
    char[][] _board = null;

    private class TrieNode {
        public TrieNode[] children = new TrieNode[26];
        public String word = null;

        public TrieNode () {
        }
    }

    private void insert(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); ++i) {
            int index = word.charAt(i) - 'a';
            if (p.children[index] == null) {
                TrieNode node = new TrieNode();
                p.children[index] = node;
            }
            p = p.children[index];
        }
        p.word = word;
    }

    public List<String> findWords(char[][] board, String[] words) {

        for (String s: words) {
            this.insert(s);
        }

        this._board = board;

        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (root.children[board[i][j] - 'a'] != null)
                    backtracking(i, j, root);
            }
        }

        return _result;
    }

    private void backtracking(int row, int col, TrieNode parent) {

        Character letter = this._board[row][col];
        TrieNode currNode = parent.children[letter - 'a'];

        // check if there is any match
        if (currNode.word != null) {
            this._result.add(currNode.word);
            currNode.word = null;
        }

        // mark the current letter before the exploration
        this._board[row][col] = '#';

        for (int[] pair : pairs) {
            int newX = pair[0] + row;
            int newY = pair[1] + col;

            if (newX < 0 || newX >= this._board.length || newY < 0 || newY >= this._board[0].length || this._board[newX][newY] == '#') continue;

            if (currNode.children[this._board[newX][newY] - 'a'] != null) {
                backtracking(newX, newY, currNode);
            }
        }

        // end of the exploration, restore teh original letter in the board
        this._board[row][col] = letter;
    }

    private int[][] pairs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
}
