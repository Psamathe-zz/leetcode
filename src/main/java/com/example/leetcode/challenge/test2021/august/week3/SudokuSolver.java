package com.example.leetcode.challenge.test2021.august.week3;


/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 *
 * A sudoku solution must satisfy all of the following rules:
 *
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * The '.' character indicates empty cells.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
 * Output: [["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
 * Explanation: The input board is shown above and the only valid solution is shown below:
 *
 *
 *
 *
 * Constraints:
 *
 * board.length == 9
 * board[i].length == 9
 * board[i][j] is a digit or '.'.
 * It is guaranteed that the input board has only one solution.
 */
public class SudokuSolver {
    public static void main(String[] args) {

    }


    /**
     * https://leetcode-cn.com/problems/sudoku-solver/solution/bfshui-su-suan-fa-by-jrdb9vuqky-pm9f/
     * @param board
     */
    public void solveSudoku(char[][] board) {
        //逐行扫描找到第一个'.'，以开始进行递归回溯查找
        int nextR = -1;
        int nextC = -1;
        tag:
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') {
                    nextR = i;
                    nextC = j;
                    break tag;
                }
            }
        }
        //初始位置找到，开始逐个数字尝试
        for (char x = '1'; x <= '9'; ++x) {
            board[nextR][nextC] = x;
            boolean valid = backtrack(board, nextR, nextC);
            if (valid) {
                return;
            }
        }
    }

    /**
     * BFS+回溯算法，在每个位置上尽力尝试
     */
    private boolean backtrack(char[][] board, int r, int c) {
        if (!valid(board, r, c)) {
            //r行c列的数字存在重复，无效
            return false;
        }
        //有效，则在r行开始逐行扫描找出下一个'.'，以继续搜索
        int nextR = -1;
        int nextC = -1;
        tag:
        for (int i = r; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') {
                    nextR = i;
                    nextC = j;
                    break tag;
                }
            }
        }
        if (nextR == -1) {
            //没有找到'.'，说明整个board的搜索已经完成，且得到一个有效的数独结果
            return true;
        }
        //存在下一个'.'
        for (char x = '1'; x <= '9'; ++x) {
            //用1～9对该位置进行尝试
            board[nextR][nextC] = x;
            boolean valid = backtrack(board, nextR, nextC);
            if (valid) {
                //如果递归结果是有效的，说明完成搜索，且得到一个有效的数独结果
                return true;
            }
        }
        //r行c列位置已经对1～9进行尝试，但均以失败告终，说明是前面某个位置的数字填写是不正确的，回溯，重新变成'.'，并回到上一层状态
        board[nextR][nextC] = '.';
        return false;
    }

    /**
     * 判断board中，r行c列的数字是否有效，按照数独规则就是r行、c列以及r行c列所在的小九宫格数字不会重复
     */
    private boolean valid(char[][] board, int r, int c) {
        char number = board[r][c];
        //检查一列中是否有重复
        for (int i = 0; i < 9; ++i) {
            if (i != r && number == board[i][c]) {
                return false;
            }
        }
        //检查一行中是否有重复
        for (int j = 0; j < 9; ++j) {
            if (j != c && number == board[r][j]) {
                return false;
            }
        }
        //检查r、c所在九宫格中是否有重复
        int r_ = r / 3 * 3;
        int c_ = c / 3 * 3;
        for (int i = r_; i < r_ + 3; ++i) {
            for (int j = c_; j < c_ + 3; ++j) {
                if (i != r && j != c && number == board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
