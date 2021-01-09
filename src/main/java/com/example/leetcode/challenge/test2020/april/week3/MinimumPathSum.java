package com.example.leetcode.challenge.test2020.april.week3;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 *
 * Example:
 *
 * Input:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * Output: 7
 * Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */
public class MinimumPathSum {

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1,3,1},
                {1,5,1},
                {4,2,1}
        };
        MinimumPathSum minimumPathSum = new MinimumPathSum();
        int result = minimumPathSum.minPathSum(grid);
        System.out.println(result);
    }

    public int minPathSum(int[][] grid) {
        int u = grid.length;
        int v = grid[0].length;
        int[][] result = new int[u][v];

        for(int i = 0; i < u; i++){
            for(int j = 0; j < v ; j++){
                if(i==0 && j == 0 ){
                    result[i][j] = grid[i][j];
                } else if(i==0) {
                    result[i][j] = result[i][j-1] + grid[i][j];
                } else if(j==0) {
                    result[i][j] = result[i-1][j] + grid[i][j];
                } else {
                    result[i][j] = result[i][j-1] < result[i-1][j] ? result[i][j-1] + grid[i][j] : result[i-1][j] + grid[i][j];
                }
            }
        }
        return result[u-1][v-1];
    }


    /**
     * less memory
     */

    public int minPathSumV3(int[][] grid) {
        int[][] cache=new int[grid.length][grid[0].length];
        return dfs(grid,cache,0,0);
    }

    private int dfs(int[][] grid,int[][] cache,int x,int y){
        if(x>=grid.length||x<0||y>=grid[0].length||y<0){
            return Integer.MAX_VALUE;
        }
        if(x==grid.length-1&&y==grid[0].length-1)
            return grid[x][y];
        if(cache[x][y]!=0)
            return cache[x][y];
        int right=dfs(grid,cache,x+1,y);
        int down=dfs(grid,cache,x,y+1);
        cache[x][y]=grid[x][y]+Math.min(right,down);
        return cache[x][y];
    }
}
