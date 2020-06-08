package com.example.leetcode.medium;

import java.util.Arrays;

/**
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 *
 * Range Sum Query 2D
 * The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.
 *
 * Example:
 * Given matrix = [
 *   [3, 0, 1, 4, 2],
 *   [5, 6, 3, 2, 1],
 *   [1, 2, 0, 1, 5],
 *   [4, 1, 0, 1, 7],
 *   [1, 0, 3, 0, 5]
 * ]
 *
 * sumRegion(2, 1, 4, 3) -> 8
 * sumRegion(1, 1, 2, 2) -> 11
 * sumRegion(1, 2, 2, 4) -> 12
 */
public class NumMatrix {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                };
        NumMatrix numMatrix = new NumMatrix(matrix);
        int result = numMatrix.sumRegion(2, 1, 4, 3);
        System.out.println(result);
    }
    int[][] sum;
    public NumMatrix(int[][] matrix) {
        if(matrix.length > 0) {
            sum = new int[matrix.length][matrix[0].length];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (i == 0 && j == 0) {
                        sum[i][j] = matrix[i][j];
                    } else if (i == 0) {
                        sum[i][j] = sum[i][j - 1] + matrix[i][j];
                    } else if (j == 0) {
                        sum[i][j] = sum[i - 1][j] + matrix[i][j];
                    } else {
                        sum[i][j] = sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1] + matrix[i][j];
                    }
                }
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if(sum.length == 0)
            return 0;
        if(row1 == 0 && col1 == 0){
            return sum[row2][col2];
        } else if(row1==0){
            return sum[row2][col2] - sum[row2][col1 - 1];
        } else if(col1==0){
            return sum[row2][col2] - sum[row1 - 1][col2];
        } else {
            return sum[row2][col2] + sum[row1 - 1][col1 - 1] - sum[row1 - 1][col2] - sum[row2][col1 - 1];
        }
    }
}
