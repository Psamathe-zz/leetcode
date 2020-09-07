package com.example.leetcode.biweeklycontest.contest34;


/**
 * Given a square matrix mat, return the sum of the matrix diagonals.
 *
 * Only include the sum of all the elements on the primary diagonal and all the elements on the secondary diagonal that are not part of the primary diagonal.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: mat = [[1,2,3],
 *               [4,5,6],
 *               [7,8,9]]
 * Output: 25
 * Explanation: Diagonals sum: 1 + 5 + 9 + 3 + 7 = 25
 * Notice that element mat[1][1] = 5 is counted only once.
 * Example 2:
 *
 * Input: mat = [[1,1,1,1],
 *               [1,1,1,1],
 *               [1,1,1,1],
 *               [1,1,1,1]]
 * Output: 8
 * Example 3:
 *
 * Input: mat = [[5]]
 * Output: 5
 *
 *
 * Constraints:
 *
 * n == mat.length == mat[i].length
 * 1 <= n <= 100
 * 1 <= mat[i][j] <= 100
 */
public class MatrixDiagonalSum {
    public static void main(String[] args) {

    }

    public int diagonalSum(int[][] mat) {
        int res = 0;
        int length = mat.length;

        for (int i = 0; i < length; i++) {
            res += mat[i][i];
            res += mat[i][length - 1 - i];
        }
        if(length % 2 == 1){
            res -= mat[length/2][length/2];
        }

        return res;
    }
}
