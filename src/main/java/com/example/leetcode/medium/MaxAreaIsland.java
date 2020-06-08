package com.example.leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 *
 * Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)
 *
 * Example 1:
 *
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
 * Example 2:
 *
 * [[0,0,0,0,0,0,0,0]]
 * Given the above grid, return 0.
 * Note: The length of each dimension in the given grid does not exceed 50.
 */
public class MaxAreaIsland {
    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1,1,0,0,1,0,1,0,0},
                {1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,1,0,0},
        };
        MaxAreaIsland maxAreaIsland = new MaxAreaIsland();
        int result = maxAreaIsland.maxAreaOfIslandV2(grid);
        System.out.println(result);
    }
    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 1)
                    continue;
                max = Math.max(max,help(grid,i,j));
            }
        }
        return max;
    }

    /**
     * dfs
     * @param grid
     * @param i
     * @param j
     * @return
     */
    public int help(int[][] grid, int i , int j){
        int m = grid.length, n = grid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] <= 0)
            return 0;
        grid[i][j] *= 0;

        int num = 1;
        num +=help(grid,i - 1, j);
        num +=help(grid,i + 1, j);
        num +=help(grid,i, j - 1);
        num +=help(grid,i, j + 1);
        return num;
    }

    public int maxAreaOfIslandV2(int[][] grid) {
        int max = 0;
        int m = grid.length, n = grid[0].length;
        List<Location> dirs = new ArrayList<>();
        dirs.add(new Location(1,0));
        dirs.add(new Location(-1,0));
        dirs.add(new Location(0,1));
        dirs.add(new Location(0,-1));
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 1)
                    continue;
                int count = 0;
                Queue<Location> queue = new LinkedList<>();
                queue.add(new Location(i,j));
                grid[i][j] *= -1;
                while (!queue.isEmpty()){
                    Location location = queue.poll();
                    count++;
                    max = Math.max(max,count);
                    for (Location dir : dirs) {
                        int x = location.x + dir.x, y = location.y + dir.y;
                        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] <= 0)
                            continue;
                        grid[x][y] *= -1;
                        queue.add(new Location(x,y));
                    }
                }
            }
        }
        return max;
    }

    public class Location{
        int x;
        int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

}
