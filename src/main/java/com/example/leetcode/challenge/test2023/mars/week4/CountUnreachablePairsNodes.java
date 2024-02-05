package com.example.leetcode.challenge.test2023.mars.week4;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given an integer n. There is an undirected graph with n nodes, numbered from 0 to n - 1. You are given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes ai and bi.
 *
 * Return the number of pairs of different nodes that are unreachable from each other.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3, edges = [[0,1],[0,2],[1,2]]
 * Output: 0
 * Explanation: There are no pairs of nodes that are unreachable from each other. Therefore, we return 0.
 * Example 2:
 *
 *
 * Input: n = 7, edges = [[0,2],[0,5],[2,4],[1,6],[5,4]]
 * Output: 14
 * Explanation: There are 14 pairs of nodes that are unreachable from each other:
 * [[0,1],[0,3],[0,6],[1,2],[1,3],[1,4],[1,5],[2,3],[2,6],[3,4],[3,5],[3,6],[4,6],[5,6]].
 * Therefore, we return 14.
 */
public class CountUnreachablePairsNodes {
    public static void main(String[] args) {

    }

    /**
     * https://www.cnblogs.com/cnoodle/p/17256124.html
     * @param n
     * @param edges
     * @return
     */
    public long countPairs(int n, int[][] edges) {
        long count = 0;
        List<List<Integer>> g = buildGraph(n, edges);
        boolean[] visited = new boolean[n];

        long total = n;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int connected = dfs(g, visited, i, new int[1]);
                total -= connected;
                count += connected * total;
            }
        }
        return count;
    }

    private int dfs(List<List<Integer>> g, boolean[] visited, int cur, int[] count) {
        if (visited[cur]) {
            return count[0];
        }
        visited[cur] = true;
        count[0]++;
        for (int next : g.get(cur)) {
            dfs(g, visited, next, count);
        }
        return count[0];
    }

    private List<List<Integer>> buildGraph(int n, int[][] edges) {
        List<List<Integer>> g = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            g.get(e[0]).add(e[1]);
            g.get(e[1]).add(e[0]);
        }
        return g;
    }
}
