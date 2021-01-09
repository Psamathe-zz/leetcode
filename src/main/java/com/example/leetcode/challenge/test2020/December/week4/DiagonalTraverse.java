package com.example.leetcode.challenge.test2020.December.week4;


/**
 * Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.
 *
 *
 *
 * Example:
 *
 * Input:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 *
 * Output:  [1,2,4,7,5,3,6,8,9]
 *
 * Explanation:
 *
 *
 *
 * Note:
 *
 * The total number of elements of the given matrix will not exceed 10,000.
 */
public class DiagonalTraverse {
    public static void main(String[] args) {

    }

    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix.length==0 || matrix[0].length==0) return new int[]{};
        int m = matrix.length;
        int n = matrix[0].length;
        int r = 0, c = 0, k = 0;
        int[] res = new int[m * n];
        int[][] dirs = new int[][]{{-1,1}, {1,-1}};
        for (int i = 0; i < m * n; ++i) {
            res[i] = matrix[r][c];
            r += dirs[k][0];
            c += dirs[k][1];
            if (r >= m) {r = m - 1; c += 2; k = 1 - k;}
            if (c >= n) {c = n - 1; r += 2; k = 1 - k;}
            if (r < 0) {r = 0; k = 1 - k;}
            if (c < 0) {c = 0; k = 1 - k;}
        }
        return res;
    }
}
