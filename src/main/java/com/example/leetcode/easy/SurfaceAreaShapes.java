package com.example.leetcode.easy;

/**
 * On a N * N grid, we place some 1 * 1 * 1 cubes.
 *
 * Each value v = grid[i][j] represents a tower of v cubes placed on top of grid cell (i, j).
 *
 * Return the total surface area of the resulting shapes.
 *
 *
 *
 * Example 1:
 *
 * Input: [[2]]
 * Output: 10
 * Example 2:
 *
 * Input: [[1,2],[3,4]]
 * Output: 34
 * Example 3:
 *
 * Input: [[1,0],[0,2]]
 * Output: 16
 * Example 4:
 *
 * Input: [[1,1,1],[1,0,1],[1,1,1]]
 * Output: 32
 * Example 5:
 *
 * Input: [[2,2,2],[2,1,2],[2,2,2]]
 * Output: 46
 */
public class SurfaceAreaShapes {
    public static void main(String[] args) {
        int[][] grid = new int[][]{{1,2},{3,4}};
        SurfaceAreaShapes surfaceAreaShapes = new SurfaceAreaShapes();
        int result = surfaceAreaShapes.surfaceArea(grid);
        System.out.println(result);
    }
    public int surfaceArea(int[][] grid) {
        int result = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int value = grid[i][j] * 6;
                if(grid[i][j] > 1)
                    value = value - (grid[i][j] - 1) * 2;

                if(i+1 < grid.length && grid[i+1][j] > 0){
                    value -= Math.min(grid[i][j],grid[i+1][j]) * 2;
                }

                if(j+1 < grid[0].length && grid[i][j+1] > 0){
                    value -= Math.min(grid[i][j],grid[i][j+1]) * 2;
                }
                result += value;
            }
        }
        return result;
    }
}
