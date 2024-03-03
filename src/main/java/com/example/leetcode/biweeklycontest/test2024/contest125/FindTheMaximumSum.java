package com.example.leetcode.biweeklycontest.test2024.contest125;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * There exists an undirected tree with n nodes numbered 0 to n - 1. You are given a 0-indexed 2D integer array edges of length n - 1, where edges[i] = [ui, vi] indicates that there is an edge between nodes ui and vi in the tree. You are also given a positive integer k, and a 0-indexed array of non-negative integers nums of length n, where nums[i] represents the value of the node numbered i.
 *
 * Bogdan wants the sum of values of tree nodes to be maximum, for which Bogdan can perform the following operation any number of times (including zero) on the tree:
 *
 * Choose any edge [u, v] connecting the nodes u and v, and update their values as follows:
 * nums[u] = nums[u] XOR k
 * nums[v] = nums[v] XOR k
 * Return the maximum possible sum of the values Bogdan can achieve by performing the operation any number of times.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: nums = [1,2,1], k = 3, edges = [[0,1],[0,2]]
 * Output: 6
 * Explanation: Bogdan can achieve the maximum sum of 6 using a single operation:
 * - Choose the edge [0,2]. nums[0] and nums[2] become: 1 XOR 3 = 2, and the array nums becomes: [1,2,1] -> [2,2,2].
 * The total sum of values is 2 + 2 + 2 = 6.
 * It can be shown that 6 is the maximum achievable sum of values.
 * Example 2:
 *
 *
 * Input: nums = [2,3], k = 7, edges = [[0,1]]
 * Output: 9
 * Explanation: Bogdan can achieve the maximum sum of 9 using a single operation:
 * - Choose the edge [0,1]. nums[0] becomes: 2 XOR 7 = 5 and nums[1] become: 3 XOR 7 = 4, and the array nums becomes: [2,3] -> [5,4].
 * The total sum of values is 5 + 4 = 9.
 * It can be shown that 9 is the maximum achievable sum of values.
 * Example 3:
 *
 *
 * Input: nums = [7,7,7,7,7,7], k = 3, edges = [[0,1],[0,2],[0,3],[0,4],[0,5]]
 * Output: 42
 * Explanation: The maximum achievable sum is 42 which can be achieved by Bogdan performing no operations.
 */
public class FindTheMaximumSum {
    public static void main(String[] args) {

    }

    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        int n = nums.length;
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] e : edges) {
            int x = e[0];
            int y = e[1];
            g[x].add(y);
            g[y].add(x);
        }
        return dfs(0, -1, g, nums, k)[0];
    }

    private long[] dfs(int x, int fa, List<Integer>[] g, int[] nums, int k) {
        long f0 = 0, f1 = Long.MIN_VALUE; // f[x][0] 和 f[x][1]
        for (int y : g[x]) {
            if (y != fa) {
                long[] r = dfs(y, x, g, nums, k);
                long t = Math.max(f1 + r[0], f0 + r[1]);
                f0 = Math.max(f0 + r[0], f1 + r[1]);
                f1 = t;
            }
        }
        return new long[]{Math.max(f0 + nums[x], f1 + (nums[x] ^ k)),
                Math.max(f1 + nums[x], f0 + (nums[x] ^ k))};
    }
}
