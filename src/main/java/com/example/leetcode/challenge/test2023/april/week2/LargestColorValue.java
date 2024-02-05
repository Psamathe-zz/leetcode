package com.example.leetcode.challenge.test2023.april.week2;

import java.util.*;

/**
 * There is a directed graph of n colored nodes and m edges. The nodes are numbered from 0 to n - 1.
 *
 * You are given a string colors where colors[i] is a lowercase English letter representing the color of the ith node in this graph (0-indexed). You are also given a 2D array edges where edges[j] = [aj, bj] indicates that there is a directed edge from node aj to node bj.
 *
 * A valid path in the graph is a sequence of nodes x1 -> x2 -> x3 -> ... -> xk such that there is a directed edge from xi to xi+1 for every 1 <= i < k. The color value of the path is the number of nodes that are colored the most frequently occurring color along that path.
 *
 * Return the largest color value of any valid path in the given graph, or -1 if the graph contains a cycle.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: colors = "abaca", edges = [[0,1],[0,2],[2,3],[3,4]]
 * Output: 3
 * Explanation: The path 0 -> 2 -> 3 -> 4 contains 3 nodes that are colored "a" (red in the above image).
 * Example 2:
 *
 *
 *
 * Input: colors = "a", edges = [[0,0]]
 * Output: -1
 * Explanation: There is a cycle from 0 to 0.
 *
 */
public class LargestColorValue {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/largest-color-value-in-a-directed-graph/solution/tuo-bu-pai-xu-dp-javaban-by-pen999-yit3/
     * @param colors
     * @param edges
     * @return
     */
    public int largestPathValue(String colors, int[][] edges) {
        int[] d = new int[colors.length()];
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] e : edges) {
            int a = e[0];
            int b = e[1];
            if (!map.containsKey(a))
                map.put(a, new HashSet<>());
            map.get(a).add(b);
            d[b]++;
        }
        List<Integer> list = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < d.length; i++) {
            if (d[i] == 0)
                queue.add(i);
        }
        while (!queue.isEmpty()) {
            int t = queue.poll();
            list.add(t);
            if (map.containsKey(t)) {
                for (int u : map.get(t)) {
                    d[u]--;
                    if (d[u] == 0)
                        queue.add(u);
                }
            }
        }
        if (list.size() != colors.length())
            return -1;
        int max = 0;
        int[][] dp = new int[colors.length()][26];
        for (int x : list) {
            int col = colors.charAt(x) - 'a';
            dp[x][col] = Math.max(dp[x][col], 1);
            for (int i = 0; i < 26; i++) {
                if (map.containsKey(x)) {
                    for (int ne : map.get(x)) {
                        int c = colors.charAt(ne) - 'a';
                        dp[ne][i] = Math.max(dp[ne][i], dp[x][i] + (c == i ? 1 : 0));
                    }
                }
                max = Math.max(max, dp[x][i]);
            }
        }
        return max;
    }
}
