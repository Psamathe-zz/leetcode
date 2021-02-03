package com.example.leetcode.challenge.test2021.January.week4;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.
 *
 * A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
 *
 * Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
 * Output: 2
 * Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
 * This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
 * Example 2:
 *
 *
 *
 * Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
 * Output: 1
 * Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].
 * Example 3:
 *
 *
 * Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
 * Output: 0
 * Explanation: This route does not require any effort.
 *
 *
 * Constraints:
 *
 * rows == heights.length
 * columns == heights[i].length
 * 1 <= rows, columns <= 100
 * 1 <= heights[i][j] <= 106
 */
public class PathWithMinimumEffort {
    public static void main(String[] args) {

    }

    /**
     * https://www.cnblogs.com/sinkinben/p/13873825.html
     */
    int rows = -1, cols = -1;
    int[][] dir = new int[][]{{-1,0}, {0,-1}, {1,0}, {0,1}};
    public int minimumEffortPath(int[][] heights) {
        rows = heights.length;
        cols = heights[0].length;
        int l=0, r=(int)1e6;
        while (l<=r)
        {
            if (l == r) break;
            int m = l + (r-l)/2;
            if (bfs(heights, m)) r = m;
            else l = m+1;
        }
        return l;
    }

    boolean bfs(int[][] v, int val) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] vis = new boolean[rows][cols];
        q.add(new int[]{0,0});
        while (!q.isEmpty())
        {
            int i = q.peek()[0];
            int j = q.poll()[1];
            if (i == rows-1 && j == cols-1)
                return true;
            for (int[] d: dir) {
                int x = i+d[0], y = j+d[1];
                if (x < 0 || y < 0 || x >= rows || y >= cols || vis[x][y])
                    continue;
                if (Math.abs(v[i][j] - v[x][y]) <= val) {
                    q.add(new int[]{x,y});
                    vis[x][y] = true;
                }
            }
        }
        return false;
    }


    /**
     * faster solution
     * @param heights
     * @return
     */
    public int minimumEffortPathv0(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;

        int [][] cost = new int[m][n];
        boolean visited [][] = new boolean[m][n];

        for(int [] c : cost){
            Arrays.fill(c, Integer.MAX_VALUE);
        }

        int xDirs [] = new int[]{-1,1,0,0};
        int yDirs [] = new int[]{0,0,-1,1};

        PriorityQueue<int [] > pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.offer(new int []{0,0,0});
        visited[0][0] = true;
        int maxSoFar = 0;

        while(!pq.isEmpty()){
            int [] cur = pq.poll();

            visited[cur[0]][cur[1]] = true;

            if(cur[0] == m-1 && cur[1] == n-1)
                return cur[2];

            for(int k = 0; k < 4; k++){
                int x = cur[0] + xDirs[k];
                int y = cur[1] + yDirs[k];
                int currCos = cur[2];

                if(x >= 0 && x < m && y >=0 && y < n && !visited[x][y]){
                    int diff = Math.abs(heights[cur[0]][cur[1]] - heights[x][y]);
                    int maxDiff = Math.max(diff,currCos);

                    if(maxDiff < cost[x][y]){
                        cost[x][y] = maxDiff;
                        pq.offer(new int[]{x,y,cost[x][y]});
                    }
                }
            }
        }
        return cost[m-1][n-1];
    }
}
