package com.jetbrains.classic.advancedDataStructure.dsTrie;

public class AddSearchWord {

    private class TrieNode{
        String word;
        TrieNode[] children;
        public TrieNode() {
            this.children = new TrieNode[26];
        }
    }

    private TrieNode root;
    /** Initialize your data structure here. */
    public void WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        // time: O(M) where M is the length of input word string
        // space: O(M) in the worst case newly inserted key doesn't share a prefix with the keys already inserted in the trie. We
        // have to add M new nodes, which takes O(M) space.
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

    public boolean search(String word) {
        // recursively ...
        // time: O(M) for the well-defined words without dots, where M is the key length,
        //.      O(N * 26 ^ M) for the undefined words.
        // space: O(1)
        return helper(word, 0, root);
    }

    private boolean helper(String word, int index, TrieNode root) {
        // base case
        if(index == word.length()) {
            return root.word != null;
        }
        // recursive part
        char c = word.charAt(index);
        if(c == '.') {
            for (int i = 0; i < 26; ++i) {
                if (root.children[i] != null && helper(word, index+1, root.children[i])) {
                    return true;
                }
            }
            return false;
        } else {
            int num = c - 'a';
            if (root.children[num] == null) {
                return false;
            }
            return helper(word, index+1, root.children[num]);
        }
    }
}
