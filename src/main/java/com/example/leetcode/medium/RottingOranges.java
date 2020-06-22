package com.example.leetcode.medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * In a given grid, each cell can have one of three values:
 *
 * the value 0 representing an empty cell;
 * the value 1 representing a fresh orange;
 * the value 2 representing a rotten orange.
 * Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.
 *
 * Example 1:
 * Input: [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 * Example 2:
 *
 * Input: [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
 * Example 3:
 *
 * Input: [[0,2]]
 * Output: 0
 * Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.
 */
public class RottingOranges {

    public static void main(String[] args) {
        int[][] grid = new int[][]{{2},{1},{1},{1},{2},{1},{1}};
        RottingOranges rottingOranges = new RottingOranges();
        int result = rottingOranges.orangesRotting(grid);
        System.out.println(result);
    }
    public int orangesRotting(int[][] grid) {
        int result = 0;
        int v = grid.length;
        int u = grid[0].length;
        Queue<OrangePosition> positionStack = new LinkedList<>();
        int freshOranges = 0;
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < u; j++) {
                if (grid[i][j] == 2) {
                    positionStack.add(new OrangePosition(i,j,0));
                } else if (grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }
        while (!positionStack.isEmpty()){
            OrangePosition orangePosition = positionStack.poll();
            int x = orangePosition.x;
            int y = orangePosition.y;
            int minute = orangePosition.minute;
            if(result<minute)
                result = minute;
            if(x != 0 && grid[x - 1][y] == 1){
                positionStack.add(new OrangePosition(x-1,y,minute+1));
                grid[x - 1][y] = 2;
                freshOranges--;
            }

            if(x != v-1 && grid[x + 1][y] == 1){
                positionStack.add(new OrangePosition(x+1,y,minute+1));
                grid[x + 1][y] = 2;
                freshOranges--;
            }

            if(y != 0 && grid[x][y -1 ] == 1){
                positionStack.add(new OrangePosition(x,y-1,minute+1));
                grid[x][y - 1] = 2;
                freshOranges--;
            }

            if(y != u-1 && grid[x][y + 1] == 1){
                positionStack.add(new OrangePosition(x,y + 1,minute+1));
                grid[x][y + 1] = 2;
                freshOranges--;
            }
        }
        if(freshOranges>0)
            return -1;
        return result;
    }

    public class OrangePosition{
        int x;
        int y;
        int minute;

        public OrangePosition(int x, int y, int minute) {
            this.x = x;
            this.y = y;
            this.minute = minute;
        }
    }

}
