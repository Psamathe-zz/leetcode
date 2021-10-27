package com.example.leetcode.weeklycontest.test262;

import java.util.Arrays;

/**
 * You are given a 2D integer grid of size m x n and an integer x. In one operation, you can add x to or subtract x from any element in the grid.
 *
 * A uni-value grid is a grid where all the elements of it are equal.
 *
 * Return the minimum number of operations to make the grid uni-value. If it is not possible, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[2,4],[6,8]], x = 2
 * Output: 4
 * Explanation: We can make every element equal to 4 by doing the following:
 * - Add x to 2 once.
 * - Subtract x from 6 once.
 * - Subtract x from 8 twice.
 * A total of 4 operations were used.
 * Example 2:
 *
 *
 * Input: grid = [[1,5],[2,3]], x = 1
 * Output: 5
 * Explanation: We can make every element equal to 3.
 * Example 3:
 *
 *
 * Input: grid = [[1,2],[3,4]], x = 2
 * Output: -1
 * Explanation: It is impossible to make every element equal.
 *
 * [[529,529,989],[989,529,345],[989,805,69]]
 * 92
 */
public class MakeUniValueGrid {
    public static void main(String[] args) {
        MakeUniValueGrid makeUniValueGrid = new MakeUniValueGrid();
        makeUniValueGrid.minOperations(new int[][]{
                {529,529,989},
                {989,529,345},
                {989,805,69},
        }, 92);
    }

    public int minOperations(int[][] grid, int x) {
        int m = grid.length;
        int n = grid[0].length;
        int base = grid[0][0] % x;
        int[] nums = new int[m * n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] % x != base)
                    return -1;
                nums[i * n + j] = grid[i][j] / x;
                System.out.println(nums[i * n + j]);
            }
        }

        // 排序并得到中位数
        Arrays.sort(nums);
        int median = nums[(m * n) / 2];
        int res = 0;
        for (int i = 0; i < m * n; i++) {
            res += Math.abs(nums[i] - median);
        }
        return res;
    }
}
