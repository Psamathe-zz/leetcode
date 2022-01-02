package com.example.leetcode.challenge.test2021.december;

/**
 * Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * Output: 4
 * Example 2:
 *
 *
 * Input: matrix = [["0","1"],["1","0"]]
 * Output: 1
 * Example 3:
 *
 * Input: matrix = [["0"]]
 * Output: 0
 */
public class MaximalSquare {
    public static void main(String[] args) {

    }

    int maxLen = 0;
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int rc = matrix.length;
        int cc = matrix[0].length;
        for (int i = 0; i < rc; i++) {
            for (int j = 0; j < cc; j++) {
                checkSquare(matrix, i, j);
            }
        }
        return maxLen*maxLen;
    }

    public void checkSquare(char[][] matrix, int r, int c) {
        if (matrix[r][c] == '0') {
            return;
        }
        if (r == 0 || c == 0) {
            maxLen = Math.max(maxLen, 1);
            return;
        }
        int max = 1;
        max = Math.min(Math.min(matrix[r - 1][c] - '0', matrix[r - 1][c - 1] - '0'), matrix[r][c - 1] - '0');
        maxLen = Math.max(max + 1, maxLen);
        matrix[r][c] = (char)(max + 1 + '0');
    }


}
