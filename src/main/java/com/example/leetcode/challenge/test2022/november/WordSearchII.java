package com.example.leetcode.challenge.test2022.november;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an m x n board of characters and a list of strings words, return all words on the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
 * Output: ["eat","oath"]
 * Example 2:
 *
 *
 * Input: board = [["a","b"],["c","d"]], words = ["abcb"]
 * Output: []
 */
public class WordSearchII {
    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        String[] words = new String[]{"oath", "pea", "eat", "rain"};
        com.example.leetcode.challenge.test2020.June.week5.WordSearchII wordSearchII = new com.example.leetcode.challenge.test2020.June.week5.WordSearchII();
        wordSearchII.findWordsV2(board, words);
    }

    /**
     * solution 1 => DFS
     *
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords(char[][] board, String[] words) {
        List<String> ans = new ArrayList<>();
        for (String word : words)
            if (helper(board, word))
                ans.add(word);
        return ans;
    }

    int u;
    int v;

    public boolean helper(char[][] board, String word) {
        if (board.length == 0) return false;
        u = board.length;
        v = board[0].length;
        for (int i = 0; i < u; i++)
            for (int j = 0; j < v; j++)
                if (searchDfs(board, word, 0, i, j))
                    return true;
        return false;
    }

    boolean searchDfs(char[][] board, String word, int d, int x, int y) {
        if (x < 0 || x == u || y < 0 || y == v || word.charAt(d) != board[x][y])
            return false;

        // Found the last char of the word
        if (d == word.length() - 1)
            return true;

        char cur = board[x][y];
        board[x][y] = '#';
        boolean found = searchDfs(board, word, d + 1, x + 1, y)
                || searchDfs(board, word, d + 1, x - 1, y)
                || searchDfs(board, word, d + 1, x, y + 1)
                || searchDfs(board, word, d + 1, x, y - 1);
        board[x][y] = cur;
        return found;
    }

    /**
     * solution 2 => trie
     * https://zxi.mytechroad.com/blog/searching/leetcode-212-word-search-ii/
     */

    public class TrieNode {
        TrieNode[] nodes;
        String word;

        public TrieNode() {
            this.nodes = new TrieNode[26];
            this.word = null;
        }
    }
    List<String> ans;
    public List<String> findWordsV1(char[][] board, String[] words) {
        TrieNode root = new TrieNode();

        // Add all the words into Trie.
        for (String  word :words){
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                TrieNode next = cur.nodes[c - 'a'];
                if (next == null)
                    next = new TrieNode();
                cur = next;
            }
            cur.word = word;
        }

        u = board.length;
        v = board[0].length;
        ans = new ArrayList<>();
        for (int i = 0; i < u; i++)
            for (int j = 0; j < v; j++)
                walk(i, j, root,board);

        return ans;

    }

    public void walk(int x, int y, TrieNode node, char[][] board) {
        if (x < 0 || x == u || y < 0 || y == v || board[y][x] == '#')
            return;

        char cur = board[x][y];
        TrieNode next_node = node.nodes[cur - 'a'];

        // Pruning, only expend paths that are in the trie.
        if (next_node == null)
            return;

        if (next_node.word != null) {
            ans.add(next_node.word);
            next_node.word = null;
        }

        board[x][y] = '#';
        walk(x + 1, y, next_node,board);
        walk(x - 1, y, next_node,board);
        walk(x, y + 1, next_node,board);
        walk(x, y - 1, next_node,board);
        board[x][y] = cur;
    }

    /**
     * faster solution
     */
    class Trie {
        Trie[] child = new Trie[26];
        boolean isWord = false;
        String word = null;
    }
    Trie root = new Trie();
    public List<String> findWordsV2(char[][] board, String[] words) {
        for(String word: words) {
            addToTrie(word);
        }
        List<String> result = new ArrayList<>();
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[0].length; j++) {
                dfs(board, i, j, root, result);
            }
        }
        return result;
    }

    public void dfs(char[][] board, int i, int j, Trie node, List<String> result) {
        if(i<0 || i >=board.length || j<0 || j >=board[0].length || board[i][j] == '#' || node == null || node.child[board[i][j] - 'a'] == null) return;
        char c = board[i][j];
        if(node.child[c-'a'].isWord) {
            result.add(node.child[c-'a'].word);
            node.child[c-'a'].isWord = false;
        }
        node = node.child[c-'a'];
        board[i][j] = '#';
        dfs(board, i+1, j, node, result);
        dfs(board, i-1, j, node, result);
        dfs(board, i, j+1, node, result);
        dfs(board, i, j-1, node, result);
        board[i][j] = c;
    }


    public void addToTrie(String word) {
        Trie node = root;
        for(char c: word.toCharArray()) {
            if(node.child[c-'a'] == null) {
                node.child[c-'a'] = new Trie();
            }
            node = node.child[c-'a'];
        }
        node.isWord = true;
        node.word = word;
    }
}
