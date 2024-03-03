package com.example.leetcode.biweeklycontest.test2024.contest125;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given an unrooted weighted tree with n vertices representing servers numbered from 0 to n - 1, an array edges where edges[i] = [ai, bi, weighti] represents a bidirectional edge between vertices ai and bi of weight weighti. You are also given an integer signalSpeed.
 *
 * Two servers a and b are connectable through a server c if:
 *
 * a < b, a != c and b != c.
 * The distance from c to a is divisible by signalSpeed.
 * The distance from c to b is divisible by signalSpeed.
 * The path from c to b and the path from c to a do not share any edges.
 * Return an integer array count of length n where count[i] is the number of server pairs that are connectable through the server i.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: edges = [[0,1,1],[1,2,5],[2,3,13],[3,4,9],[4,5,2]], signalSpeed = 1
 * Output: [0,4,6,6,4,0]
 * Explanation: Since signalSpeed is 1, count[c] is equal to the number of pairs of paths that start at c and do not share any edges.
 * In the case of the given path graph, count[c] is equal to the number of servers to the left of c multiplied by the servers to the right of c.
 * Example 2:
 *
 *
 * Input: edges = [[0,6,3],[6,5,3],[0,3,1],[3,2,7],[3,1,6],[3,4,2]], signalSpeed = 3
 * Output: [2,0,0,0,0,0,2]
 * Explanation: Through server 0, there are 2 pairs of connectable servers: (4, 5) and (4, 6).
 * Through server 6, there are 2 pairs of connectable servers: (4, 5) and (0, 5).
 * It can be shown that no two servers are connectable through servers other than 0 and 6.
 */
public class CountPairsConnectableServers {
    public static void main(String[] args) {

    }

    public int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
        int n = edges.length + 1;
        List<int[]>[] g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] e : edges) {
            int x = e[0];
            int y = e[1];
            int wt = e[2];
            g[x].add(new int[]{y, wt});
            g[y].add(new int[]{x, wt});
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int[] e : g[i]) {
                int cnt = dfs(e[0], i, e[1], g, signalSpeed);
                ans[i] += cnt * sum;
                sum += cnt;
            }
        }
        return ans;
    }

    private int dfs(int x, int fa, int sum, List<int[]>[] g, int signalSpeed) {
        int cnt = sum % signalSpeed == 0 ? 1 : 0;
        for (int[] e : g[x]) {
            int y = e[0];
            if (y != fa) {
                cnt += dfs(y, x, sum + e[1], g, signalSpeed);
            }
        }
        return cnt;
    }
}
