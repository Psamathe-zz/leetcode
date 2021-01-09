package com.example.leetcode.challenge.test2020.August.week1;

/**
 * Design a data structure that supports the following two operations:
 *
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.
 *
 * Example:
 *
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * Note:
 * You may assume that all words are consist of lowercase letters a-z.
 * https://www.cnblogs.com/grandyang/p/4507286.html
 */
public class AddSearchWord {
    public static void main(String[] args) {
        AddSearchWord addSearchWord = new AddSearchWord();
        addSearchWord.addWord("bad");
        addSearchWord.addWord("dad");
        addSearchWord.addWord("mad");
        boolean b1 = addSearchWord.search("pad");
        boolean b2 = addSearchWord.search("bad");
        boolean b3 = addSearchWord.search(".ad");
        boolean b4 = addSearchWord.search("b..");
        System.out.println(b4);
    }
    /** Initialize your data structure here. */
    public TrieNode root;
    public AddSearchWord() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode p = root;
        for (char c : word.toCharArray()) {
            if (p.childNodes[c-'a'] == null) {
                p.childNodes[c-'a'] = new TrieNode();
            }
            p = p.childNodes[c-'a'];
        }
        p.isEnd = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return searchWord(word, root, 0);
    }
    boolean searchWord(String word, TrieNode p, int i) {
        if (i == word.length())
            return p.isEnd;
        if (word.charAt(i) == '.') {
            for (TrieNode a : p.childNodes) {
                if (a!=null && searchWord(word, a, i + 1))
                    return true;
            }
            return false;
        } else {
            return p.childNodes[word.charAt(i) - 'a'] != null && searchWord(word, p.childNodes[word.charAt(i) - 'a'], i + 1);
        }
    }

    public TrieNode trieNode = new TrieNode();

    public class TrieNode {
        /// 26个字符，也就是26叉树
        public TrieNode[] childNodes;

        boolean isEnd;

        /// 初始化
        public TrieNode() {
            childNodes = new TrieNode[26];
            isEnd = false;
        }
    }
}
