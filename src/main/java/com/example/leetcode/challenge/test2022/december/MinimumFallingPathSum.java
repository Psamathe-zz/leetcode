package com.example.leetcode.challenge.test2022.december;

/**
 * Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.
 *
 * A falling path starts at any element in the first row and chooses the element in the next row that is either directly below or diagonally left/right. Specifically, the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
 * Output: 13
 * Explanation: There are two falling paths with a minimum sum as shown.
 * Example 2:
 *
 *
 * Input: matrix = [[-19,57],[-40,-5]]
 * Output: -59
 * Explanation: The falling path with a minimum sum is shown.
 */
public class MinimumFallingPathSum {
    public static void main(String[] args) {

    }

    public int minFallingPathSum(int[][] matrix) {
        int u = matrix.length;
        int v = matrix[0].length;
        int min;
        for (int i = 1; i < u; i++) {
            for (int j = 0; j < v; j++) {
                min = matrix[i-1][j];
                if(j >= 1)
                    min = Math.min(matrix[i-1][j-1],min);
                if(j < v - 1)
                    min = Math.min(matrix[i-1][j+1],min);
                matrix[i][j] += min;
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < v; i++) {
            res = Math.min(res, matrix[u - 1][i]);
        }
        return res;

    }
}
