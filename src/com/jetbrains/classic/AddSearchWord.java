package com.jetbrains.classic;

public class AddSearchWord {

    private class TrieNode {
        public char data;
        public TrieNode[] children = new TrieNode[26];
        public boolean isEndingChar = false;

        public TrieNode(char data) {
            this.data = data;
        }
    }

    private TrieNode root;

    /** Initialize your data structure here. */
    public AddSearchWord() {
        root = new TrieNode('/');
    }

    public void addWord(String word) {
        // time: O(M) where M is the length of input word string
        // space: O(M) in the worst case newly inserted key doesn't share a prefix with the keys already inserted in the trie. We
        // have to add M new nodes, which takes O(M) space.
        char[] arr = word.toCharArray();
        TrieNode p = root;
        for (int i = 0; i < arr.length; ++i) {
            int index = arr[i] - 'a';
            if (p.children[index] == null) {
                TrieNode node = new TrieNode(arr[i]);
                p.children[index] = node;
            }
            p = p.children[index];
        }
        p.isEndingChar = true;
    }

    public boolean search(String word) {
        // recursively ...
        // time: O(M) for the well-defined words without dots, where M is the key length,
        //.      O(N * 26 ^ M) for the undefined words.
        // space: O(1)
        return helper(word, root, 0);
    }

    private boolean helper(String word, TrieNode node, int index) {
        // base case
        if (index == word.length()) {
            return node.isEndingChar;
        }

        // recursive case;
        if (word.charAt(index) != '.') {
            int ind = word.charAt(index) - 'a';
            return (node.children[ind] != null &&
                    helper(word, node.children[ind], index + 1));

        } else {
            for (TrieNode n : node.children) {
                if (n != null && helper(word, n, index + 1)) {
                    return true;
                }
            }
            return false;
        }
    }
}
