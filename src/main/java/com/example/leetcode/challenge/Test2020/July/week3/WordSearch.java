package com.example.leetcode.challenge.July.week3;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 * Example:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 *
 *
 * Constraints:
 *
 * board and word consists only of lowercase and uppercase English letters.
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 */
public class WordSearch {
    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'A','A'}
        };
        String word = "aaa";
        WordSearch wordSearch = new WordSearch();
        boolean res = wordSearch.exist(board,word);
        System.out.println(res);
    }

    /**
     */
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
}
