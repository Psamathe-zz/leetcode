package com.example.leetcode.challenge.test2021.april.week4;


/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 *
 * An obstacle and space is marked as 1 and 0 respectively in the grid.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: 2
 * Explanation: There is one obstacle in the middle of the 3x3 grid above.
 * There are two ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down -> Down
 * 2. Down -> Down -> Right -> Right
 * Example 2:
 *
 *
 * Input: obstacleGrid = [[0,1],[0,0]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * m == obstacleGrid.length
 * n == obstacleGrid[i].length
 * 1 <= m, n <= 100
 * obstacleGrid[i][j] is 0 or 1.
 */
public class UniquePathsII {
    public static void main(String[] args) {

    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int u = obstacleGrid.length;
        int v = obstacleGrid[0].length;
        int[][] cur = new int[u][v];
        cur[0][0] = 1;
        for (int i = 0; i < u; i++) {
            for (int j = 0; j < v; j++) {
                if(obstacleGrid[i][j] == 1) {
                    cur[i][j] = 0;
                    continue;
                }
                if(i > 0 && cur[i-1][j] != 0){
                    cur[i][j] += cur[i-1][j];
                }
                if(j > 0 && cur[i][j-1] != 0){
                    cur[i][j] += cur[i][j-1];
                }
            }
        }
        return cur[u-1][v-1];
    }
}
