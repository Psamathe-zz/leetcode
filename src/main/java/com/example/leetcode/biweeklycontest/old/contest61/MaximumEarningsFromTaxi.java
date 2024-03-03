package com.example.leetcode.biweeklycontest.old.contest61;

import java.util.*;

/**
 * There are n points on a road you are driving your taxi on. The n points on the road are labeled from 1 to n in the direction you are going, and you want to drive from point 1 to point n to make money by picking up passengers. You cannot change the direction of the taxi.
 *
 * The passengers are represented by a 0-indexed 2D integer array rides, where rides[i] = [starti, endi, tipi] denotes the ith passenger requesting a ride from point starti to point endi who is willing to give a tipi dollar tip.
 *
 * For each passenger i you pick up, you earn endi - starti + tipi dollars. You may only drive at most one passenger at a time.
 *
 * Given n and rides, return the maximum number of dollars you can earn by picking up the passengers optimally.
 *
 * Note: You may drop off a passenger and pick up a different passenger at the same point.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 5, rides = [[2,5,4],[1,5,1]]
 * Output: 7
 * Explanation: We can pick up passenger 0 to earn 5 - 2 + 4 = 7 dollars.
 * Example 2:
 *
 * Input: n = 20, rides = [[1,6,1],[3,10,2],[10,12,3],[11,12,2],[12,15,2],[13,18,1]]
 * Output: 20
 * Explanation: We will pick up the following passengers:
 * - Drive passenger 1 from point 3 to point 10 for a profit of 10 - 3 + 2 = 9 dollars.
 * - Drive passenger 2 from point 10 to point 12 for a profit of 12 - 10 + 3 = 5 dollars.
 * - Drive passenger 5 from point 13 to point 18 for a profit of 18 - 13 + 1 = 6 dollars.
 * We earn 9 + 5 + 6 = 20 dollars in total.
 */
public class MaximumEarningsFromTaxi {
    public static void main(String[] args) {
        MaximumEarningsFromTaxi maximumEarningsFromTaxi = new MaximumEarningsFromTaxi();
        maximumEarningsFromTaxi.maxTaxiEarnings(20, new int[][]{
                {1,6,1},
                {3,10,2},
                {10,12,3},
                {11,12,2},
                {12,15,2},
                {13,18,1},
        });
    }
    public long maxTaxiEarnings(int n, int[][] rides) {
        long[] dp = new long[n + 1];
        //按终点由小到大排序
        Arrays.sort(rides, Comparator.comparingInt(a -> a[1]));
        int index = 0;
        for (int i = 1; i <= n; i++) {
            //出租车从i-1位置走到i位置,不载人时他的最大收益等于dp[i-1]
            dp[i] = dp[i - 1];
            while (index < rides.length && rides[index][1] <= i) {
                //更新以i为终点载人情况的最大盈利
                dp[i] = Math.max(dp[i], dp[rides[index][0]] + rides[index][1] - rides[index][0] + rides[index][2]);
                index++;
            }
        }
        return dp[n];
    }

    public long maxTaxiEarningsVOld(int n, int[][] rides) {
        int module = 1000000007;
        Arrays.sort(rides, (o1, o2) -> {
            if(o1[0] == o2[0])
                return o1[1] - o2[1];
            return o1[0] - o2[0];
        });

        int start;
        int end;
        Map<Integer, Integer> map = new TreeMap<>();
        int res = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        int startMax = 0;
        for (int[] ride: rides){
            start = 0;
            while (!queue.isEmpty() && queue.peek()[0] <= ride[0]){
                start = Math.max(queue.poll()[1], start);
            }
            startMax = Math.max(startMax, start);
            end = Math.max(map.getOrDefault(ride[1], 0), startMax + (ride[1] - ride[0]) + ride[2]);
            end %= module;
            map.put(ride[1], end);
            queue.add(new int[]{ride[1], end});
            res = Math.max(end, res);
        }

        return res;
    }


}
