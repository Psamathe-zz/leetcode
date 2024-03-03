package com.example.leetcode.biweeklycontest.old.contest69;

/**
 * You are given an m x n binary matrix grid where each cell is either 0 (empty) or 1 (occupied).
 *
 * You are then given stamps of size stampHeight x stampWidth. We want to fit the stamps such that they follow the given restrictions and requirements:
 *
 * Cover all the empty cells.
 * Do not cover any of the occupied cells.
 * We can put as many stamps as we want.
 * Stamps can overlap with each other.
 * Stamps are not allowed to be rotated.
 * Stamps must stay completely inside the grid.
 * Return true if it is possible to fit the stamps while following the given restrictions and requirements. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0]], stampHeight = 4, stampWidth = 3
 * Output: true
 * Explanation: We have two overlapping stamps (labeled 1 and 2 in the image) that are able to cover all the empty cells.
 * Example 2:
 *
 *
 * Input: grid = [[1,0,0,0],[0,1,0,0],[0,0,1,0],[0,0,0,1]], stampHeight = 2, stampWidth = 2
 * Output: false
 * Explanation: There is no way to fit the stamps onto all the empty cells without the stamps going outside the grid.
 *
 */
public class StampingGrid {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/stamping-the-grid/solution/tan-xin-dong-tai-gui-hua-by-fan_hadoop-zpq3/
     * @param grid
     * @param x
     * @param y
     * @return
     */
    public boolean possibleToStamp(int[][] grid, int x, int y) {
        int m = grid.length, n = grid[0].length;
        int[][][] g1 = new int[m][n][2];
        int[][][] g2 = new int[m][n][2];
        for(int i = 0; i < m; i++){
            for(int j = 0;j < n; j++){
                if(grid[i][j] == 1) continue;
                if(i == 0 || grid[i-1][j] == 1){
                    g1[i][j][0] = 1;
                    g1[i][j][1] = (j > 0 ? g1[i][j-1][1] : 0) + 1;
                }else if(j == 0 || grid[i][j-1] == 1){
                    g1[i][j][0] = (i > 0 ? g1[i-1][j][0] : 0) + 1;
                    g1[i][j][1] = 1;
                }else{
                    g1[i][j][0] = Math.min(g1[i][j-1][0], g1[i-1][j][0]+1);
                    g1[i][j][1] = Math.min(g1[i][j-1][1]+1, g1[i-1][j][1]);
                }
            }
        }
        for(int i = m-1; i >= 0; i--){
            for(int j = n-1;j >= 0; j--){
                if(grid[i][j] == 1) continue;
                if(i == m-1 || grid[i+1][j] == 1){
                    g2[i][j][0] = 1;
                    g2[i][j][1] = (j != n-1 ? g2[i][j+1][1] : 0) + 1;
                }else if(j == n-1 || grid[i][j+1] == 1){
                    g2[i][j][0] = (i != m-1 ? g2[i+1][j][0] : 0) + 1;
                    g2[i][j][1] = 1;
                }else{
                    g2[i][j][0] = Math.min(g2[i+1][j][0]+1, g2[i][j+1][0]);
                    g2[i][j][1] = Math.min(g2[i+1][j][1], g2[i][j+1][1]+1);
                }
            }
        }
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1) continue;
                int px = i-g1[i][j][0]+1;
                int py = j-g1[i][j][1]+1;
                if(g2[px][py][0] < x || g2[px][py][1] < y){
                    return false;
                }
            }
        }
        return true;
    }

}
