package com.example.leetcode.weeklycontest.old.test275;

import java.util.HashSet;
import java.util.Set;

/**
 * An n x n matrix is valid if every row and every column contains all the integers from 1 to n (inclusive).
 *
 * Given an n x n integer matrix matrix, return true if the matrix is valid. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[1,2,3],[3,1,2],[2,3,1]]
 * Output: true
 * Explanation: In this case, n = 3, and every row and column contains the numbers 1, 2, and 3.
 * Hence, we return true.
 * Example 2:
 *
 *
 * Input: matrix = [[1,1,1],[1,2,3],[1,2,3]]
 * Output: false
 * Explanation: In this case, n = 3, but the first row and the first column do not contain the numbers 2 or 3.
 * Hence, we return false.
 *
 */
public class CheckEveryRowColumnContainsAllNumbers {
    public static void main(String[] args) {

    }

    public boolean checkValid(int[][] matrix) {
        int length = matrix.length;
        int sum = (1 + length) * length / 2;
        int x = 0;
        int y = 0;
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                x += matrix[i][j];
                y += matrix[j][i];
                set1.add(matrix[i][j]);
                set2.add(matrix[j][i]);
                if(x > sum || y > sum)
                    return false;
            }
            if(x != sum || y != sum || set1.size() != length || set2.size() != length)
                return false;

            set1.clear();
            set2.clear();
            x = 0;
            y = 0;
        }
        return true;
    }
}
