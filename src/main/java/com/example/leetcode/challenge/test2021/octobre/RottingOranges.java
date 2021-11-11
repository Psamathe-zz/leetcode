package com.example.leetcode.challenge.test2021.octobre;

/**
 *You are given an m x n grid where each cell can have one of three values:
 *
 * 0 representing an empty cell,
 * 1 representing a fresh orange, or
 * 2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 * Example 2:
 *
 * Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
 * Example 3:
 *
 * Input: grid = [[0,2]]
 * Output: 0
 * Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
 *
 */
public class RottingOranges {
    public static void main(String[] args) {
        RottingOranges rottingOranges = new RottingOranges();
        int res = rottingOranges.orangesRotting(new int[][]{
                {0,2}
        });
        System.out.println(res);
    }


    int[][] grid;
    int[][] count;
    int u;
    int v;
    int res;
    public int orangesRotting(int[][] grid) {
        this.grid = grid;
        u = grid.length;
        v = grid[0].length;
        count = new int[u][v];
        res = 0;
        int t;

        for (int i = 0; i < u; i++) {
            for (int j = 0; j < v; j++) {
                count[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < u; i++) {
            for (int j = 0; j < v; j++) {
                if(grid[i][j] == 2){
                    count[i][j] = 0;
                    t = 1;
                    helper(i - 1, j, t);
                    helper(i + 1, j, t);
                    helper(i, j + 1, t);
                    helper(i, j - 1, t);
                }
            }
        }
        for (int i = 0; i < u; i++) {
            for (int j = 0; j < v; j++) {
                if(grid[i][j] != 0 ){
                    if(count[i][j] == Integer.MAX_VALUE)
                        return -1;
                    else
                        res = Math.max(res, count[i][j]);
                }
            }
        }

        return res;
    }

    public void helper(int i, int j, int t){
        if(i < 0 || i >= u || j < 0 || j >= v || grid[i][j] != 1 || t >= count[i][j])
            return;
        count[i][j] = t;
        grid[i][j] = 2;
        helper(i - 1, j, t + 1);
        helper(i + 1, j, t + 1);
        helper(i, j + 1, t + 1);
        helper(i, j - 1, t + 1);
        grid[i][j] = 1;

    }
}
