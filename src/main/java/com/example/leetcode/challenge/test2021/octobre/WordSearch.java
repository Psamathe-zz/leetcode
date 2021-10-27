package com.example.leetcode.challenge.test2021.octobre;

/**
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * Output: true
 * Example 2:
 *
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * Output: true
 * Example 3:
 *
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * Output: false
 *
 *
 * Constraints:
 *
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board and word consists of only lowercase and uppercase English letters.
 */
public class WordSearch {
    public static void main(String[] args) {

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
}
