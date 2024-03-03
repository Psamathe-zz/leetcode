package com.example.leetcode.biweeklycontest.old.contest33;

/**
 * Given a 2D array of characters grid of size m x n, you need to find if there exists any cycle consisting of the same value in grid.
 *
 * A cycle is a path of length 4 or more in the grid that starts and ends at the same cell. From a given cell, you can move to one of the cells adjacent to it - in one of the four directions (up, down, left, or right), if it has the same value of the current cell.
 *
 * Also, you cannot move to the cell that you visited in your last move. For example, the cycle (1, 1) -> (1, 2) -> (1, 1) is invalid because from (1, 2) we visited (1, 1) which was the last visited cell.
 *
 * Return true if any cycle of the same value exists in grid, otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: grid = [["a","a","a","a"],["a","b","b","a"],["a","b","b","a"],["a","a","a","a"]]
 * Output: true
 * Explanation: There are two valid cycles shown in different colors in the image below:
 *
 * Example 2:
 *
 *
 *
 * Input: grid = [["c","c","c","a"],["c","d","c","c"],["c","c","e","c"],["f","c","c","c"]]
 * Output: true
 * Explanation: There is only one valid cycle highlighted in the image below:
 *
 * Example 3:
 *
 *
 *
 * Input: grid = [["a","b","b"],["b","z","b"],["b","b","a"]]
 * Output: false
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m <= 500
 * 1 <= n <= 500
 * grid consists only of lowercase English letters.
 */
public class DetectCyclesIn2DGrid {
    public static void main(String[] args) {

    }


    /**
     * https://www.acwing.com/file_system/file/content/whole/index/content/1188821/
     * @param grid
     * @return
     */
    int[][] map;
    int m;
    int n;
    int[][] directions = new int[][]{
            {1,0},
            {-1,0},
            {0,1},
            {0,-1}
    };
    public boolean containsCycle(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        map = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0 ){
                    if (dfs(i, j, 1, grid)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean dfs(int x, int y, int t, char[][] grid){
        map[x][y] = t;
        int nx;
        int ny;
        for (int i = 0; i < 4; i++) {
            nx = x + directions[i][0];
            ny = y + directions[i][1];
            if(nx < 0 || nx >= m || ny < 0 || ny >= n || grid[nx][ny] != grid[x][y]) {
                continue;
            }
            if (map[nx][ny] > 0 ) {
                if(t-map[nx][ny] >= 3) {
                    return true;
                }
                continue;
            }
            if (dfs(nx, ny, t+1, grid)) {
                return true;
            }
        }
        return false;
    }
}
