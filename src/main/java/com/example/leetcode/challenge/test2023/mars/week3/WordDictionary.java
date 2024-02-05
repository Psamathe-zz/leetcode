package com.example.leetcode.challenge.test2023.mars.week3;


import java.util.ArrayList;
import java.util.List;

/**
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 *
 * Implement the WordDictionary class:
 *
 * WordDictionary() Initializes the object.
 * void addWord(word) Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 *
 *
 * Example:
 *
 * Input
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * Output
 * [null,null,null,null,false,true,true,true]
 *
 * Explanation
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 */
public class WordDictionary {
    public static void main(String[] args) {

    }

    class TrieNode {
        TrieNode[] children;
        boolean flag;

        public TrieNode() {
            children = new TrieNode[26];
            flag = false;
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
        }

    }
    TrieNode root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        char[] array = word.toCharArray();
        TrieNode cur = root;
        for (int i = 0; i < array.length; i++) {
            // 当前孩子是否存在
            if (cur.children[array[i] - 'a'] == null) {
                cur.children[array[i] - 'a'] = new TrieNode();
            }
            cur = cur.children[array[i] - 'a'];
        }
        // 当前节点代表结束
        cur.flag = true;
    }

    /**
     * Returns if the word is in the data structure. A word could contain the
     * dot character '.' to represent any one letter.
     */
    public boolean search(String word) {
        return searchHelp(word, root);
    }

    private boolean searchHelp(String word, TrieNode root) {
        char[] array = word.toCharArray();
        TrieNode cur = root;
        for (int i = 0; i < array.length; i++) {
            // 对于 . , 递归的判断所有不为空的孩子
            if(array[i] == '.'){
                for(int j = 0;j < 26; j++){
                    if(cur.children[j] != null){
                        if(searchHelp(word.substring(i + 1),cur.children[j])){
                            return true;
                        }
                    }
                }
                return false;
            }
            // 不含有当前节点
            if (cur.children[array[i] - 'a'] == null) {
                return false;
            }
            cur = cur.children[array[i] - 'a'];
        }
        // 当前节点是否为是某个单词的结束
        return cur.flag;
    }
}
