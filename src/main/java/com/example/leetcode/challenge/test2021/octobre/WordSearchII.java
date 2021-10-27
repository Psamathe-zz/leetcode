package com.example.leetcode.challenge.test2021.octobre;

import java.util.ArrayList;
import java.util.Arrays;
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
        WordSearchII wordSearchII = new WordSearchII();
        wordSearchII.findWords(new char[][]{

        }, new String[]{"oath","pea","eat","rain"});
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        Arrays.stream(words).filter(word -> exist(board, word)).forEach(word -> res.add(word));
        return res;
    }


    String target;
    public boolean exist(char[][] board, String word) {
        char[] chars = word.toCharArray();
        target = word;
        int u = board.length;
        int v = board[0].length;
        if(u == 0 || v == 0)
            return false;
        for (int i = 0; i < u; i++) {
            for (int j = 0; j < v; j++) {
                if(chars[0] == board[i][j]){
                    if (helper(board, 0, i, j))
                        return true;
                }
            }
        }
        return false;
    }

    public boolean helper(char[][] board,int idx, int i, int j){
        if(idx == target.length())
            return true;
        else if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != target.charAt(idx))
            return false;
        else {
            char c = board[i][j];
            board[i][j] = '#';
            boolean res = helper(board, idx + 1, i + 1, j) || helper(board, idx + 1, i - 1, j) || helper(board, idx + 1, i, j + 1) || helper(board, idx + 1, i, j - 1);
            board[i][j] = c;
            return res;
        }
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
    int u;
    int v;
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
