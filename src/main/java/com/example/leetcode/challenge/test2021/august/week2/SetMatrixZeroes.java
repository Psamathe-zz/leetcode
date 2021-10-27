package com.example.leetcode.challenge.test2021.august.week2;

/**
 * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's, and return the matrix.
 *
 * You must do it in place.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: [[1,0,1],[0,0,0],[1,0,1]]
 * Example 2:
 *
 *
 * Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 *
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[0].length
 * 1 <= m, n <= 200
 * -231 <= matrix[i][j] <= 231 - 1
 */
public class SetMatrixZeroes {
    public static void main(String[] args) {

    }

    public void setZeroes(int[][] matrix) {
        int u = matrix.length;
        int v = matrix[0].length;

        boolean[] x= new boolean[u];
        boolean[] y= new boolean[v];

        for (int i = 0; i < u; i++) {
            for (int j = 0; j < v; j++) {
                if(matrix[i][j] == 0 ){
                    x[i] = true;
                    y[j] = true;
                }
            }
        }


        for (int i = 0; i < u; i++) {
            for (int j = 0; j < v; j++) {
                if(x[i] || y[j])
                    matrix[i][j] = 0;
            }
        }
    }
}
