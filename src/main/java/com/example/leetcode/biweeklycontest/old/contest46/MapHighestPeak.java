package com.example.leetcode.biweeklycontest.old.contest46;


/**
 * You are given an integer matrix isWater of size m x n that represents a map of land and water cells.
 *
 * If isWater[i][j] == 0, cell (i, j) is a land cell.
 * If isWater[i][j] == 1, cell (i, j) is a water cell.
 * You must assign each cell a height in a way that follows these rules:
 *
 * The height of each cell must be non-negative.
 * If the cell is a water cell, its height must be 0.
 * Any two adjacent cells must have an absolute height difference of at most 1. A cell is adjacent to another cell if the former is directly north, east, south, or west of the latter (i.e., their sides are touching).
 * Find an assignment of heights such that the maximum height in the matrix is maximized.
 *
 * Return an integer matrix height of size m x n where height[i][j] is cell (i, j)'s height. If there are multiple solutions, return any of them.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: isWater = [[0,1],[0,0]]
 * Output: [[1,0],[2,1]]
 * Explanation: The image shows the assigned heights of each cell.
 * The blue cell is the water cell, and the green cells are the land cells.
 * Example 2:
 *
 *
 *
 * Input: isWater = [[0,0,1],[1,0,0],[0,0,0]]
 * Output: [[1,1,0],[0,1,1],[1,2,2]]
 * Explanation: A height of 2 is the maximum possible height of any assignment.
 * Any height assignment that has a maximum height of 2 while still meeting the rules will also be accepted.
 *
 *
 * Constraints:
 *
 * m == isWater.length
 * n == isWater[i].length
 * 1 <= m, n <= 1000
 * isWater[i][j] is 0 or 1.
 * There is at least one water cell.
 */
public class MapHighestPeak {
    public static void main(String[] args) {
        int[][] isWater = new int[][]{
                {0,0,1},
                {1,0,0},
                {0,0,0}
        };
        MapHighestPeak mapHighestPeak = new MapHighestPeak();
        mapHighestPeak.highestPeak(isWater);
    }

    public int[][] highestPeak(int[][] isWater) {
        int u = isWater.length;
        int v = isWater[0].length;
        int[][] res = new int[u][v];
        for (int i = 0; i < u; i++) {
            for (int j = 0; j < v; j++) {
                res[i][j] = isWater[i][j]==1 ? 0:2000;
            }
        }
        for (int i = 1; i < u; i++) {
            for (int j = 0; j < v; j++) {
                if(res[i][j] != 0){
                    res[i][j] = Math.min(res[i-1][j] + 1,res[i][j]);
                }
            }
        }

        for (int i = u - 2; i >= 0; i--) {
            for (int j = 0; j < v; j++) {
                if(res[i][j] != 0){
                    res[i][j] = Math.min(res[i+1][j] + 1,res[i][j]);
                }
            }
        }

        for (int i = 1; i < v; i++) {
            for (int j = 0; j < u; j++) {
                if(res[j][i] != 0){
                    res[j][i] = Math.min(res[j][i], res[j][i-1]+1);
                }
            }
        }

        for (int i = v - 2; i >= 0; i--) {
            for (int j = 0; j < u; j++) {
                if(res[j][i] != 0){
                    res[j][i] = Math.min(res[j][i], res[j][i+1]+1);
                }
            }
        }

        return res;
    }
}
