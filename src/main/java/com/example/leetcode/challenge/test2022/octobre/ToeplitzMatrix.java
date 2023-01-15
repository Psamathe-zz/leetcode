package com.example.leetcode.challenge.test2022.octobre;

/**
 * Given an m x n matrix, return true if the matrix is Toeplitz. Otherwise, return false.
 *
 * A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same elements.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
 * Output: true
 * Explanation:
 * In the above grid, the diagonals are:
 * "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
 * In each diagonal all elements are the same, so the answer is True.
 * Example 2:
 *
 *
 * Input: matrix = [[1,2],[2,2]]
 * Output: false
 * Explanation:
 * The diagonal "[1, 2]" has different elements.
 */
public class ToeplitzMatrix {
    public static void main(String[] args) {

    }

    public boolean isToeplitzMatrix(int[][] matrix) {
        int u = matrix.length;
        int v = matrix[0].length;
        int x;
        int y;
        int val;
        for (int i = 0; i < u; i++) {
            x = i;
            y = 0;
            val = matrix[x][y];
            while (x < u && y < v) {
                if(matrix[x][y] != val)
                    return false;
                x++;
                y++;
            }
        }
        for (int i = 1; i < v; i++) {
            x = 0;
            y = i;
            val = matrix[x][y];
            while (x < u && y < v) {
                if(matrix[x][y] != val)
                    return false;
                x++;
                y++;
            }
        }
        return true;

    }
}
