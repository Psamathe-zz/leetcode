package com.example.leetcode.challenge.test2021.november;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.
 *
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * Explanation: Surrounded regions should not be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.
 * Example 2:
 *
 * Input: board = [["X"]]
 * Output: [["X"]]
 *
 */
public class SurroundedRegions {
    public static void main(String[] args) {
        SurroundedRegions surroundedRegions = new SurroundedRegions();
        surroundedRegions.solve(new char[][]{
                "XXXX".toCharArray(),
                "XOOX".toCharArray(),
                "XXOX".toCharArray(),
                "XOXX".toCharArray(),
        });
    }

    char[][] board;
    int u;
    int v;
    public void solve(char[][] board) {
        this.board = board;
        u = board.length;
        v = board[0].length;
        for (int i = 0; i < u; i++) {
            for (int j = 0; j < v; j++) {
                if(board[i][j] == 'O'){
                    if(i == 0 || i == u - 1 || j == 0 || j == v - 1){
                        board[i][j] = '$';
                        helper(i + 1, j);
                        helper(i - 1, j);
                        helper(i, j + 1);
                        helper(i, j - 1);
                    }
                }
            }
        }

        for (int i = 0; i < u; i++) {
            for (int j = 0; j < v; j++) {
                if(board[i][j] == '$')
                    board[i][j] = 'O';
                else if(board[i][j] == 'O')
                    board[i][j] = 'X';
            }
        }

    }

    public void helper(int i, int j){
        if(i < 0 || i > u - 1 || j < 0 || j > v - 1 || board[i][j] != 'O'){
            return;
        }
        board[i][j] = '$';
        helper(i + 1, j);
        helper(i - 1, j);
        helper(i, j + 1);
        helper(i, j - 1);
    }

}
