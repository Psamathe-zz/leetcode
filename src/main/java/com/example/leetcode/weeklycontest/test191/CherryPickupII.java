package com.example.leetcode.weeklycontest.test191;

import java.util.Arrays;

/**
 * Given a rows x cols matrix grid representing a field of cherries. Each cell in grid represents the number of cherries that you can collect.
 *
 * You have two robots that can collect cherries for you, Robot #1 is located at the top-left corner (0,0) , and Robot #2 is located at the top-right corner (0, cols-1) of the grid.
 *
 * Return the maximum number of cherries collection using both robots  by following the rules below:
 *
 * From a cell (i,j), robots can move to cell (i+1, j-1) , (i+1, j) or (i+1, j+1).
 * When any robot is passing through a cell, It picks it up all cherries, and the cell becomes an empty cell (0).
 * When both robots stay on the same cell, only one of them takes the cherries.
 * Both robots cannot move outside of the grid at any moment.
 * Both robots should reach the bottom row in the grid.
 *
 *
 * Example 1:
 *
 *
 *
 * Input: grid = [[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
 * Output: 24
 * Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
 * Cherries taken by Robot #1, (3 + 2 + 5 + 2) = 12.
 * Cherries taken by Robot #2, (1 + 5 + 5 + 1) = 12.
 * Total of cherries: 12 + 12 = 24.
 * Example 2:
 *
 *
 *
 * Input: grid = [[1,0,0,0,0,0,1],[2,0,0,0,0,3,0],[2,0,9,0,0,0,0],[0,3,0,5,4,0,0],[1,0,2,3,0,0,6]]
 * Output: 28
 * Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
 * Cherries taken by Robot #1, (1 + 9 + 5 + 2) = 17.
 * Cherries taken by Robot #2, (1 + 3 + 4 + 3) = 11.
 * Total of cherries: 17 + 11 = 28.
 * Example 3:
 *
 * Input: grid = [[1,0,0,3],[0,0,0,3],[0,0,3,3],[9,0,3,3]]
 * Output: 22
 * Example 4:
 *
 * Input: grid = [[1,1],[1,1]]
 * Output: 4
 *
 *
 * Constraints:
 *
 * rows == grid.length
 * cols == grid[i].length
 * 2 <= rows, cols <= 70
 * 0 <= grid[i][j] <= 100
 */
public class CherryPickupII {
    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1,0,0,3},
                {0,0,0,3},
                {0,0,3,3},
                {9,0,3,3}
        };
        CherryPickupII cherryPickupII = new CherryPickupII();
        int result = cherryPickupII.cherryPickup(grid);
        System.out.println(result);
    }

    /**
     * https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-1463-cherry-pickup-ii/
     */
    int[][][] dp;
    public int cherryPickup(int[][] grid) {
        int u = grid.length;
        int v = grid[0].length;
        dp = new int[u][v][v];
        for (int i = 0; i < u; i++) {
            for (int j = 0; j < v; j++) {
                Arrays.fill(dp[i][j], - 1);
            }
        }
        int x = 0;
        int y1 = 0;
        int y2 = v - 1;
        int result = help(grid,x,y1,y2) ;
        return result;
    }

    public int help(int[][] grid,int x,int y1,int y2){
        if(dp[x][y1][y2] != -1)
            return dp[x][y1][y2];
        int result = 0;
        if(y1 != y2){
            result += grid[x][y1];
        }
        result += grid[x][y2];
        int temp = 0;
        if(x < grid.length - 1) {
            for (int i = Math.max(0,y1-1); i <= Math.min(grid[0].length - 1,y1+1); i++) {
                for (int j = Math.max(0,y2-1); j <= Math.min(grid[0].length - 1,y2+1); j++) {
                    int value = help(grid, x + 1, i, j);
                    if ( temp < value){
                        temp = value;
                    }
                }
            }
        }
        dp[x][y1][y2] = result + temp;
        return result + temp;
    }
}
