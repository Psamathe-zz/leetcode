package com.example.leetcode.challenge.test2021.september.week2;

/**
 * You are given an integer n. You have an n x n binary grid grid with all values initially 1's except for some indices given in the array mines. The ith element of the array mines is defined as mines[i] = [xi, yi] where grid[xi][yi] == 0.
 *
 * Return the order of the largest axis-aligned plus sign of 1's contained in grid. If there is none, return 0.
 *
 * An axis-aligned plus sign of 1's of order k has some center grid[r][c] == 1 along with four arms of length k - 1 going up, down, left, and right, and made of 1's. Note that there could be 0's or 1's beyond the arms of the plus sign, only the relevant area of the plus sign is checked for 1's.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 5, mines = [[4,2]]
 * Output: 2
 * Explanation: In the above grid, the largest plus sign can only be of order 2. One of them is shown.
 * Example 2:
 *
 *
 * Input: n = 1, mines = [[0,0]]
 * Output: 0
 * Explanation: There is no plus sign, so return 0.
 *
 */
public class LargestPlusSign {
    public static void main(String[] args) {
        LargestPlusSign largestPlusSign = new LargestPlusSign();
        largestPlusSign.orderOfLargestPlusSign(1, new int[][]{
                {0,0}
        });
    }

    int index;
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = 1;
            }
        }
        for (int[] mine: mines){
            map[mine[0]][mine[1]] = 0;
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(map[i][j] == 1){
                    index = 1;
                    helper(i, j, map);
                    res = Math.max(res, index);
                }
            }
        }
        return res;
    }

    public void helper(int x, int y, int[][] map){
        if(x + index < map.length && map[x + index][y] == 1 &&
                x - index >= 0 && map[x - index][y] == 1 &&
                y + index < map[0].length && map[x][y + index] == 1 &&
                y - index >= 0 && map[x][y - index] == 1){
            index++;
            helper(x, y, map);
        }
    }


    /**
     * https://leetcode-cn.com/problems/largest-plus-sign/solution/javadong-tai-gui-hua-jian-dan-yi-dong-by-dan-huang/
     * @param N
     * @param A
     * @return
     */
    public int orderOfLargestPlusSignV1(int N, int[][] A) {
        int [][][] dp = new int[N][N][4];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 4; k++) dp[i][j][k] = 1;
            }
        }
        for (int[] z : A) {
            for (int k = 0; k < 4; k++) dp[z[0]][z[1]][k] = 0;
        }
        // one-pass算左和上
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                if (dp[i][j][0] == 0) continue;
                dp[i][j][0] = 1 + dp[i][j-1][0];
                dp[i][j][1] = 1 + dp[i-1][j][1];
            }
        }
        // one-pass算右和下
        for (int i = N-2; i >= 0; i--) {
            for (int j = N-2; j >= 0; j--) {
                if (dp[i][j][2] == 0) continue;
                dp[i][j][2] = 1 + dp[i][j+1][2];
                dp[i][j][3] = 1 + dp[i+1][j][3];
            }
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int k = min(dp[i][j][0], dp[i][j][1], dp[i][j][2], dp[i][j][3]);
                ans = Math.max(ans, k);
            }
        }
        return ans;
    }

    int min(int a, int b, int c, int d) {
        return Math.min(Math.min(a, b), Math.min(c, d));
    }
}
