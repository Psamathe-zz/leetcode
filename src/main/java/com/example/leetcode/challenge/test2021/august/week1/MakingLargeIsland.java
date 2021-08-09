package com.example.leetcode.challenge.test2021.august.week1;

import java.util.HashMap;
import java.util.HashSet;

/**
 * You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
 *
 * Return the size of the largest island in grid after applying this operation.
 *
 * An island is a 4-directionally connected group of 1s.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[1,0],[0,1]]
 * Output: 3
 * Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
 * Example 2:
 *
 * Input: grid = [[1,1],[1,0]]
 * Output: 4
 * Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
 * Example 3:
 *
 * Input: grid = [[1,1],[1,1]]
 * Output: 4
 * Explanation: Can't change any 0 to 1, only one island with area = 4.
 */
public class MakingLargeIsland {
    public static void main(String[] args) {

    }

    public int largestIsland(int[][] grid) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int color = 2; // 用不同的颜色标记找到的不同的岛
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int size = helper(grid, i, j, color);
                    // corner case, if its all 1's
                    if (size == n * n) {
                        return size;
                    }
                    map.put(color, size);
                    color++;
                }
            }
        }
        // corner case
        if (color == 2) {
            return 1;
        }

        int res = map.getOrDefault(2, 0);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 如果是岛的外围，则看看这个点能和多少已知的岛连起来组成更大的岛
                if (grid[i][j] == -1) {
                    HashSet<Integer> set = new HashSet<>();
                    set.add(i > 0 ? grid[i - 1][j] : 0);
                    set.add(i < n - 1 ? grid[i + 1][j] : 0);
                    set.add(j > 0 ? grid[i][j - 1] : 0);
                    set.add(j < n - 1 ? grid[i][j + 1] : 0);

                    int newSize = 1;
                    for (int c : set) {
                        newSize += map.getOrDefault(c, 0);
                    }
                    res = Math.max(res, newSize);
                }
            }
        }
        return res;
    }

    private int helper(int[][] grid, int i, int j, int color) {
        // 越界
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
            return 0;
        }
        if (grid[i][j] != 1) {
            if (grid[i][j] == 0) {
                grid[i][j] = -1; // 标记成-1表示当前这个坐标是某个岛屿的外围
            }
            return 0;
        }
        grid[i][j] = color;
        return 1 + helper(grid, i - 1, j, color) + helper(grid, i + 1, j, color) + helper(grid, i, j - 1, color)
                + helper(grid, i, j + 1, color);
    }
}
