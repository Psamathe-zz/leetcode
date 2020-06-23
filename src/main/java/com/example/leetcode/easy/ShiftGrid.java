package com.example.leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a 2D grid of size m x n and an integer k. You need to shift the grid k times.
 *
 * In one shift operation:
 *
 * Element at grid[i][j] moves to grid[i][j + 1].
 * Element at grid[i][n - 1] moves to grid[i + 1][0].
 * Element at grid[m - 1][n - 1] moves to grid[0][0].
 * Return the 2D grid after applying shift operation k times.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
 * Output: [[9,1,2],[3,4,5],[6,7,8]]
 * Example 2:
 *
 *
 * Input: grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
 * Output: [[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]
 * Example 3:
 *
 * Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9
 * Output: [[1,2,3],[4,5,6],[7,8,9]]
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m <= 50
 * 1 <= n <= 50
 * -1000 <= grid[i][j] <= 1000
 * 0 <= k <= 100
 */
public class ShiftGrid {
    public static void main(String[] args) {

    }

    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int u = grid.length;
        int v = grid[0].length;
        int[] temp = new int[u];
        while (k>=1){
            for (int i = 0; i < u; i++) {
                temp[i] = grid[i][v-1];
            }
            for (int i = u-1; i >= 1 ; i--) {
                for (int j = 0; j < u; j++) {
                    grid[j][i] = grid[j][i-1];
                }
            }
            for (int i = 1; i < u; i++) {
                grid[i][0] = temp[i - 1];
            }
            grid[0][0] = temp[u - 1];
            k--;
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int[] list : grid){
            result.add(Arrays.stream(list).boxed().collect(Collectors.toList()));
        }
        return result;
    }


    /**
     * faster solution
     * @param grid
     * @param k
     * @return
     */
    public List<List<Integer>> shiftGridV2(int[][] grid, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (grid == null || grid.length == 0) return result;

        final int rows = grid.length;
        final int cols = grid[0].length;
        final int lastPos = rows * cols;
        int nextPos = lastPos - (k % lastPos);
        for (int row = 0; row < rows; row++){
            List<Integer> rowRes = new ArrayList<>();

            for (int col = 0; col < cols; col++){
                if (nextPos == lastPos){
                    nextPos = 0;
                }

                int nextRow = nextPos / cols;
                int nextCol = nextPos % cols;

                rowRes.add(grid[nextRow][nextCol]);
                nextPos++;
            }

            result.add(rowRes);
        }

        return result;
    }

    /**
     * https://www.cnblogs.com/wentiliangkaihua/p/11886518.html
     * @param grid
     * @param k
     * @return
     */
    public static List<List<Integer>> shiftGridV3(int[][] grid, int k) {
        int[][] temp = new int[grid.length][grid[0].length]; // took temp grid
        int n = grid.length;
        int m = grid[0].length;
        int mod = n * m;
        k = k % mod; // minimize the k value
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int p = j + k; // defines which col
                int r = p / (m); // defines which row，因为整个process是column在移动所以移动整数倍m是一样的
                if (p < m) {
                    temp[i][p] = grid[i][j];
                } else {
                    temp[(i + r) % n][p % m] = grid[i][j];
                }
            }
        }
        // making temp grid into list
        List<List<Integer>> result = new LinkedList<>();
        for (int[] t : temp) {
            LinkedList<Integer> c = new LinkedList<>();
            for (int i : t) {
                c.add(i);
            }
            result.add(c);
        }
        return result;
    }
}
