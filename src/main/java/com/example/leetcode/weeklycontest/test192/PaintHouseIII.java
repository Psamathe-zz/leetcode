package com.example.leetcode.weeklycontest.test192;

import java.util.Arrays;

/**
 * There is a row of m houses in a small city, each house must be painted with one of the n colors (labeled from 1 to n),
 * some houses that has been painted last summer should not be painted again.
 *
 * A neighborhood is a maximal group of continuous houses that are painted with the same color.
 * (For example: houses = [1,2,2,3,3,2,1,1] contains 5 neighborhoods  [{1}, {2,2}, {3,3}, {2}, {1,1}]).
 *
 * Given an array houses, an m * n matrix cost and an integer target where:
 *
 * houses[i]: is the color of the house i, 0 if the house is not painted yet.
 * cost[i][j]: is the cost of paint the house i with the color j+1.
 * Return the minimum cost of painting all the remaining houses in such a way that there are exactly target neighborhoods, if not possible return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: houses = [0,0,0,0,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target = 3
 * Output: 9
 * Explanation: Paint houses of this way [1,2,2,1,1]
 * This array contains target = 3 neighborhoods, [{1}, {2,2}, {1,1}].
 * Cost of paint all houses (1 + 1 + 1 + 1 + 5) = 9.
 * Example 2:
 *
 * Input: houses = [0,2,1,2,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target = 3
 * Output: 11
 * Explanation: Some houses are already painted, Paint the houses of this way [2,2,1,2,2]
 * This array contains target = 3 neighborhoods, [{2,2}, {1}, {2,2}].
 * Cost of paint the first and last house (10 + 1) = 11.
 * Example 3:
 *
 * Input: houses = [0,0,0,0,0], cost = [[1,10],[10,1],[1,10],[10,1],[1,10]], m = 5, n = 2, target = 5
 * Output: 5
 * Example 4:
 *
 * Input: houses = [3,1,2,3], cost = [[1,1,1],[1,1,1],[1,1,1],[1,1,1]], m = 4, n = 3, target = 3
 * Output: -1
 * Explanation: Houses are already painted with a total of 4 neighborhoods [{3},{1},{2},{3}] different of target = 3.
 *
 *
 * Constraints:
 *
 * m == houses.length == cost.length
 * n == cost[i].length
 * 1 <= m <= 100
 * 1 <= n <= 20
 * 1 <= target <= m
 * 0 <= houses[i] <= n
 * 1 <= cost[i][j] <= 10^4
 */
public class PaintHouseIII {
    public static void main(String[] args) {
        int[] houses = new int[]{0,0,0,0,0};
        int[][] cost = new int[][]{
                {1,10},
                {10,1},
                {10,1},
                {1,10},
                {5,1},
        };
        int m = 5;
        int n = 2;
        int target = 3;
        PaintHouseIII paintHouseIII = new PaintHouseIII();
        int result = paintHouseIII.minCost(houses,cost,m,n,target);
        System.out.println(result);
    }

    /**
     * k : number of group
     * i : number of houses
     * c : color of last house / group
     * dp[k][i][c] min cost from k group using first i houses and i-th in the color C.
     * dp[k][i][c]  = min(dp[k - (c != cj])[c][cj]) + color[j][c]  if color[i] == 0 else 0
     * @param houses
     * @param cost
     * @param m
     * @param n
     * @param target
     * @return
     */
    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        int[][][] dp = new int[target+1][m+1][n+1];
        int s = 1;
        for (int i = 0; i <= target ; i++) {
            for (int j = 0; j <= m ; j++) {
                if(i == 0 && j == 0)
                    Arrays.fill(dp[i][j],0);
                else
                    Arrays.fill(dp[i][j],Integer.MAX_VALUE/2);
            }
        }
        int si,ei;
        int sj,ej;
        for (int i = 1; i <= target ; i++) {
            for (int j = i; j <= m; j++) {
                int hi = houses[j - 1];
                int hj = j >= 2 ? houses[j - 2]:0;
                if(hi != 0){
                    si = hi;
                    ei = hi;
                } else {
                    si = s;
                    ei = n;
                }
                if(hj != 0){
                    sj = hj;
                    ej = hj;
                } else {
                    sj = s;
                    ej = n;
                }
                for (int ci = si; ci <= ei ; ci++) {
                    int v = ci == hi ? 0 : cost[j-1][ci - 1];
                    for (int cj = sj; cj <= ej ; cj++) {
                        dp[i][j][ci] = Math.min(dp[i][j][ci],dp[i - (ci != cj?1:0)][j - 1][cj] + v);
                    }
                }
            }
        }
        int ans = Arrays.stream(dp[target][m]).min().orElse(Integer.MAX_VALUE/2);



        return ans != Integer.MAX_VALUE/2? ans: - 1;
    }


    /**
     * faster solution
     * @param houses
     * @param cost
     * @param m
     * @param n
     * @param target
     * @return
     */
    public static int minCostV1(int[] houses, int[][] cost, int m, int n, int target) {
        int[][][] mem = new int[m][target + 1][n + 1];
        for (int[][] i : mem) {
            for (int[] j : i) {
                Arrays.fill(j, -1);
            }
        }
        int min = minCost(target, 0, 0, houses, cost, mem);
        return min == Integer.MAX_VALUE ? -1 : min;
    }


    private static int minCost(int target, int curr, int prevColor, int[] houses, int[][] cost, int[][][] mem) {
        if (curr == houses.length && target == 0) {
            return 0;
        }
        if (target > houses.length - curr || target < 0) {
            return Integer.MAX_VALUE;
        }
        if (mem[curr][target][prevColor] != -1) {
            return mem[curr][target][prevColor];
        }

        // If current house has already been painted, no cost added for current house
        if (houses[curr] != 0) {
            // If the color has been set different from previous color, we need to make a new neighborhood for the current house
            if (houses[curr] != prevColor) {
                mem[curr][target][prevColor] = minCost(target - 1, curr + 1, houses[curr], houses, cost, mem);
            } else {
                mem[curr][target][prevColor] = minCost(target, curr + 1, houses[curr], houses, cost, mem);
            }
        } else {
            int min = Integer.MAX_VALUE;
            // If current house hasn't been painted yet, we try painting it with different colors and find the minimum cost
            for (int c = 1; c <= cost[0].length; c++) {
                int res = Integer.MAX_VALUE;
                if (c == prevColor) {
                    res = minCost(target, curr + 1, c, houses, cost, mem);
                } else {
                    res = minCost(target - 1, curr + 1, c, houses, cost, mem);
                }
                if (res != Integer.MAX_VALUE)
                    min = Math.min(min, res + cost[curr][c - 1]);
            }
            mem[curr][target][prevColor] = min;
        }
        return mem[curr][target][prevColor];
    }

}
