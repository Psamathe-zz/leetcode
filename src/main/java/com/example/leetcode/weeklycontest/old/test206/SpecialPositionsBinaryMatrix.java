package com.example.leetcode.weeklycontest.old.test206;

/**
 * Given a rows x cols matrix mat, where mat[i][j] is either 0 or 1, return the number of special positions in mat.
 *
 * A position (i,j) is called special if mat[i][j] == 1 and all other elements in row i and column j are 0 (rows and columns are 0-indexed).
 *
 *
 *
 * Example 1:
 *
 * Input: mat = [[1,0,0],
 *               [0,0,1],
 *               [1,0,0]]
 * Output: 1
 * Explanation: (1,2) is a special position because mat[1][2] == 1 and all other elements in row 1 and column 2 are 0.
 * Example 2:
 *
 * Input: mat = [[1,0,0],
 *               [0,1,0],
 *               [0,0,1]]
 * Output: 3
 * Explanation: (0,0), (1,1) and (2,2) are special positions.
 * Example 3:
 *
 * Input: mat = [[0,0,0,1],
 *               [1,0,0,0],
 *               [0,1,1,0],
 *               [0,0,0,0]]
 * Output: 2
 * Example 4:
 *
 * Input: mat = [[0,0,0,0,0],
 *               [1,0,0,0,0],
 *               [0,1,0,0,0],
 *               [0,0,1,0,0],
 *               [0,0,0,1,1]]
 * Output: 3
 *
 *
 * Constraints:
 *
 * rows == mat.length
 * cols == mat[i].length
 * 1 <= rows, cols <= 100
 * mat[i][j] is 0 or 1.
 */
public class SpecialPositionsBinaryMatrix {
    public static void main(String[] args) {

    }

    public int numSpecial(int[][] mat) {
        int u = mat.length;
        int v = mat[0].length;
        int[] uCount = new int[u];
        int[] vCount = new int[v];
        int count;
        int res = 0;
        for (int i = 0; i < u; i++) {
            count = 0;
            for (int j = 0; j < v; j++) {
                count += mat[i][j];
            }
            uCount[i] = count;
        }
        for (int i = 0; i < v; i++) {
            count = 0;
            for (int j = 0; j < u; j++) {
                count += mat[j][i];
            }
            vCount[i] = count;
        }

        for (int i = 0; i < u; i++) {
            for (int j = 0; j < v; j++) {
                if(mat[i][j] == 1 && uCount[i] == 1 && vCount[j] == 1)
                    res++;
            }
        }
        return res;
    }
}
