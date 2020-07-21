package com.example.leetcode.medium;

import java.util.Arrays;

/**
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
 *
 * The distance between two adjacent cells is 1.
 *
 *
 *
 * Example 1:
 *
 * Input:
 * [[0,0,0],
 *  [0,1,0],
 *  [0,0,0]]
 *
 * Output:
 * [[0,0,0],
 *  [0,1,0],
 *  [0,0,0]]
 * Example 2:
 *
 * Input:
 * [[0,0,0],
 *  [0,1,0],
 *  [1,1,1]]
 *
 * Output:
 * [[0,0,0],
 *  [0,1,0],
 *  [1,2,1]]
 */
public class ZeroOneMatrix {
    public static void main(String[] args) {

    }

    /**
     * https://www.cnblogs.com/grandyang/p/6602288.html
     */

    public int[][] updateMatrix(int[][] matrix) {
        int u = matrix.length;
        int v = matrix[0].length;

        int[][] res = new int[u][v];
        for (int i = 0; i < u; i++) {
            Arrays.fill(res[i],Integer.MAX_VALUE - 1);
        }
        for (int i = 0; i < u; i++) {
            for (int j = 0; j < v; j++) {
                if (matrix[i][j] == 0) {
                    res[i][j] = 0;
                } else {
                    if (i > 0)
                        res[i][j] = Math.min(res[i][j], res[i - 1][j] + 1);
                    if (j > 0)
                        res[i][j] = Math.min(res[i][j], res[i][j - 1] + 1);
                }
            }
        }
        for (int i = u - 1; i >= 0; --i) {
            for (int j = v - 1; j >= 0; --j) {
                if (res[i][j] != 0 && res[i][j] != 1) {
                    if (i < u - 1)
                        res[i][j] = Math.min(res[i][j], res[i + 1][j] + 1);
                    if (j < v - 1)
                        res[i][j] = Math.min(res[i][j], res[i][j + 1] + 1);
                }
            }
        }
        return res;
    }
}
