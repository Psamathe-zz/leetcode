package com.example.leetcode.biweeklycontest.old.contest56;


import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * There is a country of n cities numbered from 0 to n - 1 where all the cities are connected by bi-directional roads. The roads are represented as a 2D integer array edges where edges[i] = [xi, yi, timei] denotes a road between cities xi and yi that takes timei minutes to travel. There may be multiple roads of differing travel times connecting the same two cities, but no road connects a city to itself.
 *
 * Each time you pass through a city, you must pay a passing fee. This is represented as a 0-indexed integer array passingFees of length n where passingFees[j] is the amount of dollars you must pay when you pass through city j.
 *
 * In the beginning, you are at city 0 and want to reach city n - 1 in maxTime minutes or less. The cost of your journey is the summation of passing fees for each city that you passed through at some moment of your journey (including the source and destination cities).
 *
 * Given maxTime, edges, and passingFees, return the minimum cost to complete your journey, or -1 if you cannot complete it within maxTime minutes.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: maxTime = 30, edges = [[0,1,10],[1,2,10],[2,5,10],[0,3,1],[3,4,10],[4,5,15]], passingFees = [5,1,2,20,20,3]
 * Output: 11
 * Explanation: The path to take is 0 -> 1 -> 2 -> 5, which takes 30 minutes and has $11 worth of passing fees.
 * Input: maxTime = 29, edges = [[0,1,10],[1,2,10],[2,5,10],[0,3,1],[3,4,10],[4,5,15]], passingFees = [5,1,2,20,20,3]
 * Output: 48
 * Explanation: The path to take is 0 -> 3 -> 4 -> 5, which takes 26 minutes and has $48 worth of passing fees.
 * You cannot take path 0 -> 1 -> 2 -> 5 since it would take too long.
 * Example 3:
 *
 * Input: maxTime = 25, edges = [[0,1,10],[1,2,10],[2,5,10],[0,3,1],[3,4,10],[4,5,15]], passingFees = [5,1,2,20,20,3]
 * Output: -1
 * Explanation: There is no way to reach city 5 from city 0 within 25 minutes.
 */
public class MinimumCostReachDestination {
    public static void main(String[] args) {

    }

    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
        int n = passingFees.length;
        HashMap<Integer, HashMap<Integer, Integer>> e = new HashMap<>();
        for (int[] edge : edges) {
            HashMap<Integer, Integer> tmp = e.getOrDefault(edge[0], new HashMap<>());
            Integer oldValue = tmp.getOrDefault(edge[1], Integer.MAX_VALUE);
            tmp.put(edge[1], Math.min(edge[2], oldValue));
            e.put(edge[0], tmp);

            tmp = e.getOrDefault(edge[1], new HashMap<>());
            oldValue = tmp.getOrDefault(edge[0], Integer.MAX_VALUE);
            tmp.put(edge[0], Math.min(edge[2], oldValue));
            e.put(edge[1], tmp);
        }
        PriorityQueue<Integer[]> q = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] integers, Integer[] t1) {
                return integers[1] - t1[1];
            }
        });
        int[][] dis = new int[n][maxTime + 1];
        boolean[][] vis = new boolean[n][maxTime + 1];
        for (int i = 0; i < n; i++) Arrays.fill(dis[i], Integer.MAX_VALUE);
        q.add(new Integer[]{0, passingFees[0], 0});//到达节点0，通行费为passingFees[0],时间为0
        dis[0][0] = passingFees[0];
        while (!q.isEmpty()) {
            Integer[] top = q.poll();
            if (top[0] == n - 1) return top[1];
            if (dis[top[0]][top[2]] < top[1]) continue;
            if (vis[top[0]][top[2]]) continue;
            vis[top[0]][top[2]] = true;
            HashMap<Integer, Integer> tmp = e.getOrDefault(top[0], new HashMap<>());
            if (tmp.size() == 0) continue;
            for (Integer key : tmp.keySet()) {
                int tim = tmp.get(key) + top[2];
                int cost = top[1] + passingFees[key];
                if (tim <= maxTime && dis[key][tim] > cost) {
                    q.add(new Integer[]{key, cost, tim});
                    dis[key][tim] = cost;
                }
            }
        }
        return -1;
    }

}
