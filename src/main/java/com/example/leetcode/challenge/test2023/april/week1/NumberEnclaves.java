package com.example.leetcode.challenge.test2023.april.week1;

/**
 * You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.
 *
 * A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.
 *
 * Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
 * Output: 3
 * Explanation: There are three 1s that are enclosed by 0s, and one 1 that is not enclosed because its on the boundary.
 * Example 2:
 *
 *
 * Input: grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
 * Output: 0
 * Explanation: All 1s are either on the boundary or can reach the boundary.
 *
 */
public class NumberEnclaves {
    public static void main(String[] args) {

    }

    int[][] map;
    public int numEnclaves(int[][] A) {
        int u = A.length;
        int v = A[0].length;
        map = A;
        int result = 0;
        for (int i = 0; i < u; i++) {
            search(i,0);
            search(i,v - 1);
        }
        for (int i = 0; i < v; i++) {
            search(0,i);
            search(u - 1,i);
        }
        for (int i = 0; i < u; i++) {
            for (int j = 0; j < v; j++) {
                if(map[i][j] == 1){
                    result++;
                }
            }
        }
        return result;
    }

    public void search(int x, int y){
        if(map[x][y] == 1) {
            map[x][y] = -1;
            if (x < map.length - 1)
                search(x + 1, y);
            if (x > 0)
                search(x - 1, y);
            if (y < map[0].length - 1)
                search(x, y + 1);
            if (y > 0)
                search(x, y - 1);
        }
    }
}
