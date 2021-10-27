package com.example.leetcode.challenge.test2021.september.week2;

import java.util.*;

/**
 * You are given an undirected graph (the "original graph") with n nodes labeled from 0 to n - 1. You decide to subdivide each edge in the graph into a chain of nodes, with the number of new nodes varying between each edge.
 *
 * The graph is given as a 2D array of edges where edges[i] = [ui, vi, cnti] indicates that there is an edge between nodes ui and vi in the original graph, and cnti is the total number of new nodes that you will subdivide the edge into. Note that cnti == 0 means you will not subdivide the edge.
 *
 * To subdivide the edge [ui, vi], replace it with (cnti + 1) new edges and cnti new nodes. The new nodes are x1, x2, ..., xcnti, and the new edges are [ui, x1], [x1, x2], [x2, x3], ..., [xcnti+1, xcnti], [xcnti, vi].
 *
 * In this new graph, you want to know how many nodes are reachable from the node 0, where a node is reachable if the distance is maxMoves or less.
 *
 * Given the original graph and maxMoves, return the number of nodes that are reachable from node 0 in the new graph.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: edges = [[0,1,10],[0,2,1],[1,2,2]], maxMoves = 6, n = 3
 * Output: 13
 * Explanation: The edge subdivisions are shown in the image above.
 * The nodes that are reachable are highlighted in yellow.
 * Example 2:
 *
 * Input: edges = [[0,1,4],[1,2,6],[0,2,8],[1,3,1]], maxMoves = 10, n = 4
 * Output: 23
 * Example 3:
 *
 * Input: edges = [[1,2,4],[1,4,5],[1,3,1],[2,3,4],[3,4,5]], maxMoves = 17, n = 5
 * Output: 1
 * Explanation: Node 0 is disconnected from the rest of the graph, so only node 0 is reachable.
 *
 */
public class ReachableNodes {
    public static void main(String[] args) {

    }
    public int reachableNodes(int[][] edges, int M, int N) {
        Map<Integer, Map<Integer, Integer>> m = new HashMap<>();
        for (int[] edge : edges) {
            m.computeIfAbsent(edge[0], t -> new HashMap<>()).put(edge[1], edge[2]);
            m.computeIfAbsent(edge[1], t -> new HashMap<>()).put(edge[0], edge[2]);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        pq.add(new int[]{M, 0});
        Map<Integer, Integer> seen = new HashMap<>();
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            // step remain
            int step = top[0];
            // current node
            int node = top[1];
            if (!seen.containsKey(node)) {
                seen.put(node, step);
                if (m.containsKey(node)) {
                    for (int next : m.get(node).keySet()) {
                        // distance between current node and next node
                        int distance = m.get(node).get(next);
                        int left = step - distance - 1;
                        if (left >= 0 && m.containsKey(next)) {
                            pq.add(new int[]{left, next});
                        }
                    }
                }
            }
        }
        int res = seen.size();
        for (int[] edge : edges) {
            int a = seen.getOrDefault(edge[0], 0);
            int b = seen.getOrDefault(edge[1], 0);
            res += Math.min(a + b, edge[2]);
        }
        return res;
    }

}
