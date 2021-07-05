package com.example.leetcode.challenge.test2021.june.week4;


/**
 * There is an m x n grid with a ball. The ball is initially at the position [startRow, startColumn]. You are allowed to move the ball to one of the four adjacent cells in the grid (possibly out of the grid crossing the grid boundary). You can apply at most maxMove moves to the ball.
 *
 * Given the five integers m, n, maxMove, startRow, startColumn, return the number of paths to move the ball out of the grid boundary. Since the answer can be very large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
 * Output: 6
 * Example 2:
 *
 *
 * Input: m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
 * Output: 12
 */
public class OutBoundaryPaths {
    public static void main(String[] args) {

    }


    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        int[][][] dp = new int[maxMove+1][m][n];
        for (int k = 1; k <= maxMove; ++k) {
            for (int x = 0; x < m; ++x) {
                for (int y = 0; y < n; ++y) {
                    long v1 = (x == 0) ? 1 : dp[k - 1][x - 1][y];
                    long v2 = (x == m - 1) ? 1 : dp[k - 1][x + 1][y];
                    long v3 = (y == 0) ? 1 : dp[k - 1][x][y - 1];
                    long v4 = (y == n - 1) ? 1 : dp[k - 1][x][y + 1];
                    dp[k][x][y] = (int)((v1 + v2 + v3 + v4) % 1000000007);
                }
            }
        }
        return dp[maxMove][startRow][startColumn];
    }
}
