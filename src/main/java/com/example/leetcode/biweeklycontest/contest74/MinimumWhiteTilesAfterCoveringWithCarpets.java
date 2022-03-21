package com.example.leetcode.biweeklycontest.contest74;

import java.util.Arrays;

/**
 * You are given a 0-indexed binary string floor, which represents the colors of tiles on a floor:
 * <p>
 * floor[i] = '0' denotes that the ith tile of the floor is colored black.
 * On the other hand, floor[i] = '1' denotes that the ith tile of the floor is colored white.
 * You are also given numCarpets and carpetLen. You have numCarpets black carpets, each of length carpetLen tiles. Cover the tiles with the given carpets such that the number of white tiles still visible is minimum. Carpets may overlap one another.
 * <p>
 * Return the minimum number of white tiles still visible.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: floor = "10110101", numCarpets = 2, carpetLen = 2
 * Output: 2
 * Explanation:
 * The figure above shows one way of covering the tiles with the carpets such that only 2 white tiles are visible.
 * No other way of covering the tiles with the carpets can leave less than 2 white tiles visible.
 * Example 2:
 * <p>
 * <p>
 * Input: floor = "11111", numCarpets = 2, carpetLen = 3
 * Output: 0
 * Explanation:
 * The figure above shows one way of covering the tiles with the carpets such that no white tiles are visible.
 * Note that the carpets are able to overlap one another.
 */
public class MinimumWhiteTilesAfterCoveringWithCarpets {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/minimum-white-tiles-after-covering-with-carpets/solution/by-endlesscheng-pa3v/
     *
     * @param floor
     * @param numCarpets
     * @param carpetLen
     * @return
     */
    public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
        int n = floor.length();

        int[][] dp = new int[n + 1][numCarpets + 1];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        for (int j = 0; j < numCarpets + 1; j++) {
            dp[0][j] = 0;
        }
        int left = 0;
        for (int i = 1; i < n + 1; i++) {
            if (floor.charAt(i - 1) == '1') {
                left++;
            }
            dp[i][0] = left;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < numCarpets + 1; j++) {
                //--------当前是黑色的，直接不用盖
                if (floor.charAt(i - 1) == '0') {
                    dp[i][j] = dp[i - 1][j];
                }
                //--------当前是白色的
                else {
                    int a = dp[i - 1][j] + 1;
                    int b = dp[Math.max(0, i - carpetLen)][j - 1];
                    dp[i][j] = Math.min(a, b);
                }
            }
        }

        return dp[n][numCarpets];

    }
}
