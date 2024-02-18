package com.example.leetcode.weeklycontest.contest2024.test384;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a 0-indexed m x n integer matrix matrix, create a new 0-indexed matrix called answer. Make answer equal to matrix, then replace each element with the value -1 with the maximum element in its respective column.
 *
 * Return the matrix answer.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[1,2,-1],[4,-1,6],[7,8,9]]
 * Output: [[1,2,9],[4,8,6],[7,8,9]]
 * Explanation: The diagram above shows the elements that are changed (in blue).
 * - We replace the value in the cell [1][1] with the maximum value in the column 1, that is 8.
 * - We replace the value in the cell [0][2] with the maximum value in the column 2, that is 9.
 * Example 2:
 *
 *
 * Input: matrix = [[3,-1],[5,2]]
 * Output: [[3,2],[5,2]]
 * Explanation: The diagram above shows the elements that are changed (in blue).
 */
public class ModifyMatrix {
    public static void main(String[] args) {
        ModifyMatrix modifyMatrix = new ModifyMatrix();
        int[][] ints = modifyMatrix.modifiedMatrix(new int[][]{
                {1,2,-1},
                {4,-1,6},
                {7,8,9}
        });

    }

    public int[][] modifiedMatrix(int[][] matrix) {
        Queue<Integer> queue = new LinkedList<>();
        int u = matrix.length;
        int v = matrix[0].length;
        int max;
        for (int i = 0; i < v; i++) {
            max = -1;
            for (int j = 0; j < u; j++) {
                max = Math.max(max, matrix[j][i]);
                if(matrix[j][i] == -1) {
                    queue.add(j);
                }
            }
            while (!queue.isEmpty()) {
                matrix[queue.poll()][i] = max;
            }
        }
        return matrix;
    }
}
