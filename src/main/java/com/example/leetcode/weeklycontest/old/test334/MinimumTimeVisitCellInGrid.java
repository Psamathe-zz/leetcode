package com.example.leetcode.weeklycontest.old.test334;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * You are given a m x n matrix grid consisting of non-negative integers where grid[row][col] represents the minimum time required to be able to visit the cell (row, col), which means you can visit the cell (row, col) only when the time you visit it is greater than or equal to grid[row][col].
 *
 * You are standing in the top-left cell of the matrix in the 0th second, and you must move to any adjacent cell in the four directions: up, down, left, and right. Each move you make takes 1 second.
 *
 * Return the minimum time required in which you can visit the bottom-right cell of the matrix. If you cannot visit the bottom-right cell, then return -1.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: grid = [[0,1,3,2],[5,1,2,5],[4,3,8,6]]
 * Output: 7
 * Explanation: One of the paths that we can take is the following:
 * - at t = 0, we are on the cell (0,0).
 * - at t = 1, we move to the cell (0,1). It is possible because grid[0][1] <= 1.
 * - at t = 2, we move to the cell (1,1). It is possible because grid[1][1] <= 2.
 * - at t = 3, we move to the cell (1,2). It is possible because grid[1][2] <= 3.
 * - at t = 4, we move to the cell (1,1). It is possible because grid[1][1] <= 4.
 * - at t = 5, we move to the cell (1,2). It is possible because grid[1][2] <= 5.
 * - at t = 6, we move to the cell (1,3). It is possible because grid[1][3] <= 6.
 * - at t = 7, we move to the cell (2,3). It is possible because grid[1][3] <= 7.
 * The final time is 7. It can be shown that it is the minimum time possible.
 * Example 2:
 *
 *
 *
 * Input: grid = [[0,2,4],[3,2,1],[1,0,4]]
 * Output: -1
 * Explanation: There is no path from the top left to the bottom-right cell.
 */
public class MinimumTimeVisitCellInGrid {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode.cn/problems/minimum-time-to-visit-a-cell-in-a-grid/solution/er-fen-da-an-bfspythonjavacgo-by-endless-j10w/
     */
    private final static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int minimumTime(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (grid[0][1] > 1 && grid[1][0] > 1) // 无法「等待」
            return -1;

        var dis = new int[m][n];
        for (int i = 0; i < m; ++i)
            Arrays.fill(dis[i], Integer.MAX_VALUE);
        dis[0][0] = 0;
        var pq = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
        pq.add(new int[]{0, 0, 0});
        for (;;) {
            var p = pq.poll();
            int d = p[0], i = p[1], j = p[2];
            if (i == m - 1 && j == n - 1)
                return d;
            for (var q : dirs) { // 枚举周围四个格子
                int x = i + q[0], y = j + q[1];
                if (0 <= x && x < m && 0 <= y && y < n) {
                    int nd = Math.max(d + 1, grid[x][y]);
                    nd += (nd - x - y) % 2; // nd 必须和 x+y 同奇偶
                    if (nd < dis[x][y]) {
                        dis[x][y] = nd; // 更新最短路
                        pq.add(new int[]{nd, x, y});
                    }
                }
            }
        }
    }
}
