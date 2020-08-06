package com.example.leetcode.algo;


import java.util.HashSet;

public class Trie {
    public static void Main() {
        Trie trie = new Trie();

    }

    public TrieNode trieNode = new TrieNode();

    public class TrieNode {
        /// 26个字符，也就是26叉树
        public TrieNode[] childNodes;

        /// 词频统计
        public int freq;

        /// 记录该节点的字符
        public char nodeChar;

        /// 插入记录时的编码id
        public HashSet<Integer> hashSet = new HashSet<Integer>();

        /// 初始化
        public TrieNode() {
            childNodes = new TrieNode[26];
            freq = 0;
        }
    }

    public void AddTrieNode(String word, int id) {
        AddTrieNode(trieNode, word, id);
    }

    public void AddTrieNode(TrieNode root, String word, int id) {
        if (word.length() == 0)
            return;

        //求字符地址，方便将该字符放入到26叉树中的哪一叉中
        int k = word.charAt(0) - 'a';

        //如果该叉树为空，则初始化
        if (root.childNodes[k] == null) {
            root.childNodes[k] = new TrieNode();

            //记录下字符
            root.childNodes[k].nodeChar = word.charAt(0);
        }

        //该id途径的节点
        root.childNodes[k].hashSet.add(id);

        String nextWord = word.substring(1);

        //说明是最后一个字符，统计该词出现的次数
        if (nextWord.length() == 0)
            root.childNodes[k].freq++;

        AddTrieNode(root.childNodes[k], nextWord, id);
    }

    public void DeleteTrieNode(TrieNode root, String word, int id) {
        if (word.length() == 0)
            return;

        //求字符地址，方便将该字符放入到26叉树种的哪一颗树中
        int k = word.charAt(0) - 'a';

        //如果该叉树为空,则说明没有找到要删除的点
        if (root.childNodes[k] == null)
            return;

        var nextWord = word.substring(1);

        //如果是最后一个单词，则减去词频
        if (word.length() == 0 && root.childNodes[k].freq > 0)
            root.childNodes[k].freq--;

        //删除途经节点
        root.childNodes[k].hashSet.remove(id);

        DeleteTrieNode(root.childNodes[k], nextWord, id);
    }


    public HashSet<Integer> SearchTrie(TrieNode root, String word, HashSet<Integer> hashSet) {
        if (word.length() == 0)
            return hashSet;

        int k = word.charAt(0) - 'a';

        var nextWord = word.substring(1);

        if (nextWord.length() == 0) {
            //采用动态规划的思想，word最后节点记录这途经的id
            hashSet = root.childNodes[k].hashSet;
        }

        SearchTrie(root.childNodes[k], nextWord, hashSet);

        return hashSet;
    }
}
