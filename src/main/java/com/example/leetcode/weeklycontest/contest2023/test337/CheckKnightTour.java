package com.example.leetcode.weeklycontest.contest2023.test337;

/**
 * There is a knight on an n x n chessboard. In a valid configuration, the knight starts at the top-left cell of the board and visits every cell on the board exactly once.
 *
 * You are given an n x n integer matrix grid consisting of distinct integers from the range [0, n * n - 1] where grid[row][col] indicates that the cell (row, col) is the grid[row][col]th cell that the knight visited. The moves are 0-indexed.
 *
 * Return true if grid represents a valid configuration of the knight's movements or false otherwise.
 *
 * Note that a valid knight move consists of moving two squares vertically and one square horizontally, or two squares horizontally and one square vertically. The figure below illustrates all the possible eight moves of a knight from some cell.
 *
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[0,11,16,5,20],[17,4,19,10,15],[12,1,8,21,6],[3,18,23,14,9],[24,13,2,7,22]]
 * Output: true
 * Explanation: The above diagram represents the grid. It can be shown that it is a valid configuration.
 * Example 2:
 *
 *
 * Input: grid = [[0,3,6],[5,8,1],[2,7,4]]
 * Output: false
 * Explanation: The above diagram represents the grid. The 8th move of the knight is not valid considering its position after the 7th move.
 *
 * [[7,1,6],[4,2,5],[0,8,3]]
 */
public class CheckKnightTour {
    public static void main(String[] args) {
        CheckKnightTour checkKnightTour = new CheckKnightTour();
        boolean res  = checkKnightTour.checkValidGrid(new int[][]{
                {0,11,16,5,20},
                {17,4,19,10,15},
                {12,1,8,21,6},
                {3,18,23,14,9},
                {24,13,2,7,22}
        });
        System.out.println(res);
    }
    int x = 0;
    int y = 0;
    public boolean checkValidGrid(int[][] grid) {
        if(grid[x][y] != 0)
            return false;
        int next;
        do {
            next = helper(grid);

        } while (next > 0 && next + 1 < grid.length * grid.length);
        return next + 1 == grid.length * grid.length;
    }

    int[][] dir = new int[][]{
            {-1, -2},
            {1, -2},
            {2, -1},
            {2, 1},
            {1, 2},
            {-1, 2},
            {-2, 1},
            {-2, -1},
    };
    public int helper(int[][] grid) {
        int x0;
        int y0;
        for (int i = 0; i < 8; i++) {
            x0 = x + dir[i][0];
            y0 = y + dir[i][1];
            if( x0 >= 0 && x0 < grid.length && y0 >= 0 && y0 < grid.length && grid[x0][y0] == grid[x][y] + 1) {
                x = x0;
                y = y0;
                return grid[x][y];
            }
        }
        return -1;
    }
}
