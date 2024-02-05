package com.example.leetcode.weeklycontest.old.test255;

import java.util.HashSet;

/**
 * You are given an m x n integer matrix mat and an integer target.
 *
 * Choose one integer from each row in the matrix such that the absolute difference between target and the sum of the chosen elements is minimized.
 *
 * Return the minimum absolute difference.
 *
 * The absolute difference between two numbers a and b is the absolute value of a - b.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: mat = [[1,2,3],[4,5,6],[7,8,9]], target = 13
 * Output: 0
 * Explanation: One possible choice is to:
 * - Choose 1 from the first row.
 * - Choose 5 from the second row.
 * - Choose 7 from the third row.
 * The sum of the chosen elements is 13, which equals the target, so the absolute difference is 0.
 * Example 2:
 *
 *
 * Input: mat = [[1],[2],[3]], target = 100
 * Output: 94
 * Explanation: The best possible choice is to:
 * - Choose 1 from the first row.
 * - Choose 2 from the second row.
 * - Choose 3 from the third row.
 * The sum of the chosen elements is 6, and the absolute difference is 94.
 * Example 3:
 *
 *
 * Input: mat = [[1,2,9,8,7]], target = 6
 * Output: 1
 * Explanation: The best choice is to choose 7 from the first row.
 * The absolute difference is 1.
 */
public class MinimizeDifference {
    public static void main(String[] args) {
        MinimizeDifference minimizeDifference = new MinimizeDifference();
        int res = minimizeDifference.minimizeTheDifference(new int[][]{
                {1},
                {2},
                {3}
        },100);
        System.out.println(res);
    }

    public int minimizeTheDifference(int[][] mat, int target) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < mat[0].length; i++) {
            set.add(mat[0][i]);
        }
        for (int i = 1; i < mat.length; i++) {
            HashSet<Integer> temp = new HashSet<>();
            for (int j = 0; j < mat[0].length; j++) {
                int limit = Integer.MAX_VALUE;
                for (int value : set) {
                    int x = value + mat[i][j];
                    if (x <= limit) {
                        temp.add(x);
                        if (x <= 2 * target) limit = 2 * target;
                    }
                }
            }
            set = temp;
        }
        int res = Integer.MAX_VALUE;
        for (int value : set) {
            res = Math.min(res, Math.abs(target - value));
        }
        return res;

    }
}
