package com.example.leetcode.challenge.test2021.june.week3;


/**
 * On an N x N grid, each square grid[i][j] represents the elevation at that point (i,j).
 *
 * Now rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distance in zero time. Of course, you must stay within the boundaries of the grid during your swim.
 *
 * You start at the top left square (0, 0). What is the least time until you can reach the bottom right square (N-1, N-1)?
 *
 * Example 1:
 *
 * Input: [[0,2],[1,3]]
 * Output: 3
 * Explanation:
 * At time 0, you are in grid location (0, 0).
 * You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
 *
 * You cannot reach point (1, 1) until time 3.
 * When the depth of water is 3, we can swim anywhere inside the grid.
 * Example 2:
 *
 * Input: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
 * Output: 16
 * Explanation:
 *  0  1  2  3  4
 * 24 23 22 21  5
 * 12 13 14 15 16
 * 11 17 18 19 20
 * 10  9  8  7  6
 *
 * The final route is marked in bold.
 * We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
 */
public class SwimRisingWater {
    public static void main(String[] args) {

    }

    private int n;
    private int[][] grid;
    private int[] dx = {-1, 1, 0, 0};
    private int[] dy = {0, 0, -1, 1};

    public int swimInWater(int[][] grid) {
        n = grid.length;
        this.grid = grid;
        int left = Math.max(grid[0][0], grid[n - 1][n - 1]);
        int right = n * n - 1;

        while(left <= right){
            int mid = (left + right) / 2;
            if(dfs(mid, 0, 0, new boolean[n][n])){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean dfs(int t, int x, int y, boolean[][] visited){
        if(x == n - 1 & y == n - 1){
            return true;
        }
        visited[x][y] = true;
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i], ny = y + dy[i];
            if(nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny] && grid[nx][ny] <= t){
                if(dfs(t, nx, ny, visited)){
                    return true;
                }
            }
        }
        return false;
    }

}
