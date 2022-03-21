package com.example.leetcode.weeklycontest.test284;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * You are given an integer n denoting the number of nodes of a weighted directed graph. The nodes are numbered from 0 to n - 1.
 *
 * You are also given a 2D integer array edges where edges[i] = [fromi, toi, weighti] denotes that there exists a directed edge from fromi to toi with weight weighti.
 *
 * Lastly, you are given three distinct integers src1, src2, and dest denoting three distinct nodes of the graph.
 *
 * Return the minimum weight of a subgraph of the graph such that it is possible to reach dest from both src1 and src2 via a set of edges of this subgraph. In case such a subgraph does not exist, return -1.
 *
 * A subgraph is a graph whose vertices and edges are subsets of the original graph. The weight of a subgraph is the sum of weights of its constituent edges.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 6, edges = [[0,2,2],[0,5,6],[1,0,3],[1,4,5],[2,1,1],[2,3,3],[2,3,4],[3,4,2],[4,5,1]], src1 = 0, src2 = 1, dest = 5
 * Output: 9
 * Explanation:
 * The above figure represents the input graph.
 * The blue edges represent one of the subgraphs that yield the optimal answer.
 * Note that the subgraph [[1,0,3],[0,5,6]] also yields the optimal answer. It is not possible to get a subgraph with less weight satisfying all the constraints.
 * Example 2:
 *
 *
 * Input: n = 3, edges = [[0,1,1],[2,1,1]], src1 = 0, src2 = 1, dest = 2
 * Output: -1
 * Explanation:
 * The above figure represents the input graph.
 * It can be seen that there does not exist any path from node 1 to node 2, hence there are no subgraphs satisfying all the constraints.
 */
public class MinimumWeightedSubgraph {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/minimum-weighted-subgraph-with-the-required-paths/solution/by-stupid_tiger-hqit/
     * @param n
     * @param edges
     * @param src1
     * @param src2
     * @param dest
     * @return
     */
    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
        // g表示正向图，grev表示反向图
        ArrayList<long[]>[] g = new ArrayList[n];
        ArrayList<long[]>[] grev = new ArrayList[n];
        // 初始化
        for(int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
            grev[i] = new ArrayList<>();
        }
        // 建图
        for(int[] e : edges) {
            g[e[0]].add(new long[] {e[1], e[2]});
            grev[e[1]].add(new long[] {e[0], e[2]});
        }

        // Long[] d1 = dijstra(src1, g);
        // Long[] d2 = dijstra(src2, g);
        // Long[] d3 = dijstra(dest, grev);

        // long res = Long.MAX_VALUE;
        // for(int i = 0; i < n; i++) {
        //     if(d1[i] == null || d2[i] == null || d3[i] == null) {
        //         continue;
        //     }else {
        //         res = Math.min(res, d1[i] + d2[i] + d3[i]);
        //     }
        // }

        long res = Long.MAX_VALUE;
        long[] d1 = dijstra(src1, g);
        long[] d2 = dijstra(src2, g);
        long[] d3 = dijstra(dest, grev);
        for(int i = 0; i < n; i++) {
            if(d1[i] == Long.MAX_VALUE || d2[i] == Long.MAX_VALUE || d3[i] == Long.MAX_VALUE) {
                continue;
            }else {
                res = Math.min(res, d1[i] + d2[i] + d3[i]);
            }
        }
        return res == Long.MAX_VALUE ? -1 : res;
    }

    public long[] dijstra(int s,  ArrayList<long[]>[] g) {
        long[] dist = new long[g.length], poll;
        PriorityQueue<long[]> q = new PriorityQueue<>((n1, n2) -> Long.compare(n1[0], n2[0]));
        Arrays.fill(dist, Long.MAX_VALUE);
        q.offer(new long[] {0, (long)s});
        while(!q.isEmpty()) {
            poll = q.poll();
            long v = poll[0];
            int cur = (int)poll[1];
            if(dist[cur] == Long.MAX_VALUE) {
                dist[cur] = v;
                for(long[] e : g[cur]) {
                    q.offer(new long[] {v + e[1], e[0]});
                }
            }
        }
        return dist;
    }

}
