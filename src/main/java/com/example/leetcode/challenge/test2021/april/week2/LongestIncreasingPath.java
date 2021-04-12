package com.example.leetcode.challenge.test2021.april.week2;


/**
 * Given an m x n integers matrix, return the length of the longest increasing path in matrix.
 *
 * From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 * Example 2:
 *
 *
 * Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
 * Output: 4
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 * Example 3:
 *
 * Input: matrix = [[1]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * 0 <= matrix[i][j] <= 231 - 1
 * [[7,8,9],[9,7,6],[7,2,3]]
 */
public class LongestIncreasingPath {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {7,8,9},
                {9,7,6},
                {7,2,3}
        };
        LongestIncreasingPath longestIncreasingPath = new LongestIncreasingPath();
        longestIncreasingPath.longestIncreasingPath(matrix);
    }

    public int longestIncreasingPath(int[][] matrix) {
        int u = matrix.length;
        int v = matrix[0].length;
        int[][] count = new int[u][v];
        boolean next;
        do{
            next = false;
            for (int i = 0; i < u; i++) {
                for (int j = 0; j < v; j++) {
                    if(i > 0 && matrix[i][j] < matrix[i-1][j] && count[i][j] < count[i - 1][j] + 1 ) {
                        count[i][j] = count[i - 1][j] + 1;
                        next = true;
                    }
                    if(i < u - 1 && matrix[i][j] < matrix[i+1][j] && count[i][j] < count[i+1][j]+1){
                        count[i][j] = count[i+1][j]+1;
                        next = true;
                    }
                    if(j > 0 && matrix[i][j] < matrix[i][j-1] && count[i][j] < count[i][j-1]+1){
                        count[i][j] = count[i][j-1]+1;
                        next = true;
                    }
                    if(j < v - 1 && matrix[i][j] < matrix[i][j+1] && count[i][j] < count[i][j+1]+1){
                        count[i][j] = count[i][j+1]+1;
                        next = true;
                    }
                }
            }
        } while (next);
        int res = 0;
        for (int i = 0; i < u; i++) {
            for (int j = 0; j < v; j++) {
                res = Math.max(res,count[i][j]);
            }
        }
        return res + 1;
    }


    /**
     * faster solution
     * @param matrix
     * @return
     */
    public int longestIncreasingPathV0(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows][cols];
        int res = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dp[i][j] == 0) {
                    dfs(matrix, i, j, dp, Integer.MIN_VALUE);
                    res = Math.max(res, dp[i][j]);
                }
            }

        }
        return res;
    }

    private int dfs(int[][] matrix, int row, int col, int[][]dp, int pre) {
        if (row > matrix.length -1 || row < 0 || col > matrix[0].length - 1 || col < 0 || matrix[row][col] <= pre)
            return 0;
        if (dp[row][col] != 0)
            return dp[row][col];

        int left = dfs(matrix, row - 1, col, dp, matrix[row][col]);
        int right = dfs(matrix, row + 1, col, dp, matrix[row][col]);
        int up = dfs(matrix, row, col+1, dp, matrix[row][col]);
        int down = dfs(matrix, row, col -1, dp, matrix[row][col]);
        dp[row][col] = Math.max(up, Math.max(down, Math.max(left, right))) + 1;
        return dp[row][col];
    }
}
