package com.example.leetcode.challenge.test2023.april.week1;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.
 *
 * Return the number of closed islands.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
 * Output: 2
 * Explanation:
 * Islands in gray are closed because they are completely surrounded by water (group of 1s).
 * Example 2:
 *
 *
 *
 * Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
 * Output: 1
 * Example 3:
 *
 * Input: grid = [[1,1,1,1,1,1,1],
 *                [1,0,0,0,0,0,1],
 *                [1,0,1,1,1,0,1],
 *                [1,0,1,0,1,0,1],
 *                [1,0,1,1,1,0,1],
 *                [1,0,0,0,0,0,1],
 *                [1,1,1,1,1,1,1]]
 * Output: 2
 */
public class NumberClosedIslands {
    public static void main(String[] args) {

    }

    int m;
    int n;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    /**
     * https://www.cnblogs.com/cnoodle/p/17062384.html
     * @param grid
     * @return
     */
    public int closedIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        // 把边界上所有的0变成1
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if ((i == 0 || i == m - 1 || j == 0 || j == n - 1) && grid[i][j] == 0) {
                    bfs(grid, i, j);
                }
            }
        }

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    count++;
                    bfs(grid, i, j);
                }
            }
        }
        return count;
    }

    private void bfs(int[][] grid, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { i, j });
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            grid[x][y] = 1;
            for (int k = 0; k < 4; k++) {
                int newX = x + dx[k];
                int newY = y + dy[k];
                if (newX >= 0 && newX < m && newY >= 0 && newY < n && grid[newX][newY] == 0) {
                    queue.offer(new int[] { newX, newY });
                }
            }
        }
    }
}
