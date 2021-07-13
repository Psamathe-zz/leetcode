package com.example.leetcode.challenge.test2021.July.week1;


/**
 * In MATLAB, there is a handy function called reshape which can reshape an m x n matrix into a new one with a different size r x c keeping its original data.
 *
 * You are given an m x n matrix mat and two integers r and c representing the row number and column number of the wanted reshaped matrix.
 *
 * The reshaped matrix should be filled with all the elements of the original matrix in the same row-traversing order as they were.
 *
 * If the reshape operation with given parameters is possible and legal, output the new reshaped matrix; Otherwise, output the original matrix.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: mat = [[1,2],[3,4]], r = 1, c = 4
 * Output: [[1,2,3,4]]
 * Example 2:
 *
 *
 * Input: mat = [[1,2],[3,4]], r = 2, c = 4
 * Output: [[1,2],[3,4]]
 */
public class ReshapeMatrix {
    public static void main(String[] args) {

    }

    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int u = mat.length;
        int v = mat[0].length;
        if(u * v != r * c)
            return mat;
        int[][] res = new int[r][c];
        int x = 0;
        int y = 0;
        for (int i = 0; i < u; i++) {
            for (int j = 0; j < v; j++) {
                y++;
                if(y == c){
                    y = 0;
                    x++;
                }
                res[x][y] = mat[i][j];
            }
        }
        return res;
    }
}
