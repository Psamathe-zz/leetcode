package com.example.leetcode.challenge.april.week3;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * https://www.cnblogs.com/skywang12345/p/3711483.html
 * 图的遍历
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 *
 * Input:
 * 11110
 * 11010
 * 11000
 * 00000
 *
 * Output: 1
 * Example 2:
 *
 * Input:
 * 11000
 * 11000
 * 00100
 * 00011
 *
 * Output: 3
 *
 * [["1","1","1"],["0","1","0"],["1","1","1"]]
 *                 {'1','1','1'},
 *                 {'0','1','0'},
 *                 {'1','1','1'}
 *
 *                 {'1','1','0','0','0'},
 *                 {'1','1','0','0','0'},
 *                 {'0','0','1','0','0'},
 *                 {'0','0','0','1','1'}
 */
public class NumberofIslands {
    static final int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    static final int[][] dirsv2 = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'1','1','1','1','1','0','1','1','1','1','1','1','1','1','1','0','1','0','1','1'},
                {'0','1','1','1','1','1','1','1','1','1','1','1','1','0','1','1','1','1','1','0'},
                {'1','0','1','1','1','0','0','1','1','0','1','1','1','1','1','1','1','1','1','1'},{'1','1','1','1','0','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'},{'1','0','0','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'},{'1','0','1','1','1','1','1','1','0','1','1','1','0','1','1','1','0','1','1','1'},{'0','1','1','1','1','1','1','1','1','1','1','1','0','1','1','0','1','1','1','1'},{'1','1','1','1','1','1','1','1','1','1','1','1','0','1','1','1','1','0','1','1'},{'1','1','1','1','1','1','1','1','1','1','0','1','1','1','1','1','1','1','1','1'},{'1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'},{'0','1','1','1','1','1','1','1','0','1','1','1','1','1','1','1','1','1','1','1'},{'1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'},{'1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'},{'1','1','1','1','1','0','1','1','1','1','1','1','1','0','1','1','1','1','1','1'},{'1','0','1','1','1','1','1','0','1','1','1','0','1','1','1','1','0','1','1','1'},{'1','1','1','1','1','1','1','1','1','1','1','1','0','1','1','1','1','1','1','0'},{'1','1','1','1','1','1','1','1','1','1','1','1','1','0','1','1','1','1','0','0'},{'1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'},{'1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'},{'1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'}};
        NumberofIslands numberofIslands = new NumberofIslands();
        int result = numberofIslands.numIslandsV2(grid);
        System.out.println(result);
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid,i,j);
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int x, int y) {
        // base case
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
            return;
        }
        if (grid[x][y] == '0' || grid[x][y] == '2') {
            return;
        }

        // recursive part
        grid[x][y] = '2';  // matrix[x][y] = 0;
        for (int[] dir : dirs) {
            int neiX = x + dir[0];
            int neiY = y + dir[1];
            dfs(grid, neiX, neiY);
        }
    }

    public int numIslandsV2(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    bfs(grid,i,j);
                }
            }
        }
        return count;
    }

    private void bfs(char[][] grid, int x, int y) {
        // base case
        Set<Point> stack = new HashSet<>();
        stack.add(new Point(x,y));
        while (!stack.isEmpty()){
            Point point = stack.iterator().next();
            stack.remove(point);
            grid[point.x][point.y] = '2';  // matrix[x][y] = 0;

            for (int[] dir : dirs) {
                int neiX = point.x + dir[0];
                int neiY = point.y + dir[1];
                if (neiX >= 0 && neiX < grid.length && neiY >= 0 && neiY < grid[0].length && grid[neiX][neiY]=='1') {
                    stack.add(new Point(neiX, neiY));
                }
            }
        }
    }

    class Point{
        int x;
        int y;
        public Point(int x,int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
