package com.example.leetcode.weeklycontest.test244;


/**
 * Given two n x n binary matrices mat and target, return true if it is possible to make mat equal to target by rotating mat in 90-degree increments, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: mat = [[0,1],[1,0]], target = [[1,0],[0,1]]
 * Output: true
 * Explanation: We can rotate mat 90 degrees clockwise to make mat equal target.
 * Example 2:
 *
 *
 * Input: mat = [[0,1],[1,1]], target = [[1,0],[0,1]]
 * Output: false
 * Explanation: It is impossible to make mat equal to target by rotating mat.
 * Example 3:
 *
 *
 * Input: mat = [[0,0,0],[0,1,0],[1,1,1]], target = [[1,1,1],[0,1,0],[0,0,0]]
 * Output: true
 * Explanation: We can rotate mat 90 degrees clockwise two times to make mat equal target.
 */
public class DetermineWhetherMatrix {
    public static void main(String[] args) {

    }

    public boolean findRotation(int[][] mat, int[][] target) {
        int length = mat.length;
        boolean b1 = true;
        boolean b2 = true;
        boolean b3 = true;
        boolean b4 = true;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if(mat[i][j] != target[i][j]) {
                    b1 = false;
                    break;
                }
            }
        }
        if(b1)
            return true;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if(mat[i][j] != target[j][length - 1 - i]) {
                    b2 = false;
                    break;
                }
            }
        }
        if(b2)
            return true;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if(mat[i][j] != target[length - 1 - i][length - 1 - j]) {
                    b3 = false;
                    break;
                }
            }
        }
        if(b3)
            return true;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if(mat[i][j] != target[length - 1 - j][i]) {
                    b4 = false;
                    break;
                }
            }
        }
        return b4;
    }
}
