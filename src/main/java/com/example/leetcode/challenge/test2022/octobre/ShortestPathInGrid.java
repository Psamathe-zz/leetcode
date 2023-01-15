package com.example.leetcode.challenge.test2022.octobre;

import java.util.Deque;
import java.util.LinkedList;

/**
 * You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle). You can move up, down, left, or right from and to an empty cell in one step.
 *
 * Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1) given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[0,0,0],[1,1,0],[0,0,0],[0,1,1],[0,0,0]], k = 1
 * Output: 6
 * Explanation:
 * The shortest path without eliminating any obstacle is 10.
 * The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).
 * Example 2:
 *
 *
 * Input: grid = [[0,1,1],[1,1,1],[1,0,0]], k = 1
 * Output: -1
 * Explanation: We need to eliminate at least two obstacles to find such a walk.
 */
public class ShortestPathInGrid {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/solution/bi-shi-chang-jian-de-lu-jing-wen-ti-by-h-eerk/
     * @param grid
     * @param k
     * @return
     */
    public int shortestPath(int[][] grid, int k) {
        int row = grid.length, col = grid[0].length;
        if (row == 1 && col == 1) {
            return 0;
        }

        // direction matrix
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Deque<int[]> queue = new LinkedList<>();
        int[][] visited = new int[row][col];
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                visited[i][j] = -1;
            }
        }

        queue.offerLast(new int[]{0, 0 ,k});
        visited[0][0] = k;
        int step = 0;

        while (!queue.isEmpty()) {
            int currSize = queue.size();
            step++;
            for (int i = 0; i < currSize; ++i) {
                int[] currPos = queue.pollFirst();

                for (int j = 0; j < 4; ++j) {
                    int nx = currPos[0] + dirs[j][0];
                    int ny = currPos[1] + dirs[j][1];
                    int currK = currPos[2];
                    // 保证下一步的位置在矩阵中
                    if (nx >= 0 && nx < row && ny >= 0 && ny < col) {
                        // 判断是否到达右下角
                        // 题目中明确了 grid[0][0] == grid[m-1][n-1] == 0，若目标点可以为1，需要修改此部分
                        if (nx == row - 1 && ny == col - 1) {
                            return step;
                        }

                        currK = grid[nx][ny] == 0 ? currK : --currK;

                        if (currK >= 0) {
                            if (visited[nx][ny] == -1 || (visited[nx][ny] != -1 && currK > visited[nx][ny]) ) {
                                queue.offerLast(new int[]{nx, ny, currK});
                                visited[nx][ny] = currK;
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }
}
