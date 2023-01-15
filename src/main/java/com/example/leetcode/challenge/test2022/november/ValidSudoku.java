package com.example.leetcode.challenge.test2022.november;

import java.util.HashSet;
import java.util.Set;

/**
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 *
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 * Note:
 *
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * Only the filled cells need to be validated according to the mentioned rules.
 *
 *
 * Example 1:
 *
 *
 * Input: board =
 * [["5","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * Output: true
 * Example 2:
 *
 * Input: board =
 * [["8","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * Output: false
 * Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 */
public class ValidSudoku {

    public static void main(String[] args) {

    }

    public boolean isValidSudoku(char[][] board) {
        Set<Character> used = new HashSet();
        // 先枚举行，检查每行是否合法
        for (int row = 0; row < 9; row++) {
            used.clear();
            for (int col = 0; col < 9; col++) {
                if (! checkValid(board[row][col], used)) {
                    return false;
                }
            }
        }
        // 先枚举列，检查每列是否合法
        for (int col = 0; col < 9; col++) {
            used.clear();
            for (int row = 0; row < 9; row++) {
                if (! checkValid(board[row][col], used)) {
                    return false;
                }
            }
        }
        // 每个分块的左上角的坐标为(i * 3, j * 3)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                used.clear();
                for (int row = i * 3; row < i * 3 + 3; row++) {
                    for (int col = j * 3; col < j * 3 + 3; col++) {
                        if (! checkValid(board[row][col], used)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    // 检查字符是否有冲突
    boolean checkValid(char c, Set<Character> used) {
        if (c == '.') {
            return true;
        }
        if (used.contains(c)) {
            return false;
        }
        used.add(c);
        return true;
    }
}
