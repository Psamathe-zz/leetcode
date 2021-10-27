package com.example.leetcode.challenge.test2021.octobre;

/**
 * You are given row x col grid representing a map where grid[i][j] = 1 represents land and grid[i][j] = 0 represents water.
 *
 * Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).
 *
 * The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island. One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
 * Output: 16
 * Explanation: The perimeter is the 16 yellow stripes in the image above.
 * Example 2:
 *
 * Input: grid = [[1]]
 * Output: 4
 * Example 3:
 *
 * Input: grid = [[1,0]]
 * Output: 4
 */
public class IslandPerimeter {
    public static void main(String[] args) {
        IslandPerimeter islandPerimeter = new IslandPerimeter();
        islandPerimeter.islandPerimeter(new int[][]{
                {0,1,0,0},
                {1,1,1,0},
                {0,1,0,0},
                {1,1,0,0},
        });
    }

    public int islandPerimeter(int[][] grid) {
        int u = grid.length;
        int v = grid[0].length;
        int res = 0;
        for (int i = 0; i < u; i++) {
            for (int j = 0; j < v; j++) {
                if(grid[i][j] == 1){
                    res += 4;
                    if(i > 0 && grid[i - 1][j] == 1)
                        res--;
                    if(i < u - 1 && grid[i + 1][j] == 1)
                        res--;
                    if(j > 0 && grid[i][j - 1] == 1)
                        res--;
                    if(j < v - 1 && grid[i][j + 1] == 1)
                        res--;
                    System.out.println(res);
                }
            }
        }
        return res;
    }
}
