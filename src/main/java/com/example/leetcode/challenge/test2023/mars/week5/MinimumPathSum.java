package com.example.leetcode.challenge.test2023.mars.week5;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
 * Output: 7
 * Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
 * Example 2:
 *
 * Input: grid = [[1,2,3],[4,5,6]]
 * Output: 12
 */
public class MinimumPathSum {
    public static void main(String[] args) {

    }

    public int minPathSum(int[][] grid) {
        int u = grid.length;
        int v = grid[0].length;

        for (int i = 0; i < u; i++) {
            for (int j = 0; j < v; j++) {
                if(i > 0 && j > 0)
                    grid[i][j] += Math.min(grid[i-1][j], grid[i][j - 1]);
                else if(i > 0) {
                    grid[i][j] += grid[i-1][j];
                } else if(j > 0) {
                    grid[i][j] += grid[i][j - 1];
                }
            }
        }

        return grid[u - 1][v - 1];

    }
}
