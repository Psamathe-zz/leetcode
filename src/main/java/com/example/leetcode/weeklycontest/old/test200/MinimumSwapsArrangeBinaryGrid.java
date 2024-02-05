package com.example.leetcode.weeklycontest.old.test200;

/**
 * Given an n x n binary grid, in one step you can choose two adjacent rows of the grid and swap them.
 *
 * A grid is said to be valid if all the cells above the main diagonal are zeros.
 *
 * Return the minimum number of steps needed to make the grid valid, or -1 if the grid cannot be valid.
 *
 * The main diagonal of a grid is the diagonal that starts at cell (1, 1) and ends at cell (n, n).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[0,0,1],[1,1,0],[1,0,0]]
 * Output: 3
 * Example 2:
 *
 *
 * Input: grid = [[0,1,1,0],[0,1,1,0],[0,1,1,0],[0,1,1,0]]
 * Output: -1
 * Explanation: All rows are similar, swaps have no effect on the grid.
 * Example 3:
 *
 *
 * Input: grid = [[1,0,0],[1,1,0],[1,1,1]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 200
 * grid[i][j] is 0 or 1
 */
public class MinimumSwapsArrangeBinaryGrid {
    public static void main(String[] args) {

    }

    /**
     * https://www.acwing.com/file_system/file/content/whole/index/content/1133563/
     * @param grid
     * @return
     */
    public int minSwaps(int[][] grid) {
        int n = grid.length;
        int[] zeroNum = new int[n];

        for (int i = 0; i < n; ++i) {
            int j = n - 1;
            while (j >= 0 && grid[i][j] == 0) {
                ++zeroNum[i];
                --j;
            }
        }

        int res = 0;
        int temp;
        for (int i = 0; i < n - 1; ++i) {
            if (zeroNum[i] < n - i - 1) {
                int j = i + 1;
                while (j < n && zeroNum[j] < n - i - 1)
                    ++j;
                if (j == n) return -1;
                while (j > i) {
                    temp = zeroNum[j];
                    zeroNum[j] = zeroNum[j - 1];
                    zeroNum[j - 1] = temp;
                    --j;
                    ++res;
                }
            }
        }
        return res;
    }
}
