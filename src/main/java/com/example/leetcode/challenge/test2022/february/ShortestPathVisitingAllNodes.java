package com.example.leetcode.challenge.test2022.february;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You have an undirected, connected graph of n nodes labeled from 0 to n - 1. You are given an array graph where graph[i] is a list of all the nodes connected with node i by an edge.
 *
 * Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: graph = [[1,2,3],[0],[0],[0]]
 * Output: 4
 * Explanation: One possible path is [1,0,2,0,3]
 * Example 2:
 *
 *
 * Input: graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
 * Output: 4
 * Explanation: One possible path is [0,1,4,2,3]
 */
public class ShortestPathVisitingAllNodes {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/shortest-path-visiting-all-nodes/solution/tong-ge-lai-shua-ti-la-bfs-si-lu-zhuan-h-jl3s/
     * @param graph
     * @return
     */
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;

        // 多节点同时开始BFS
        boolean[][] visited = new boolean[n][1 << n];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            // 当前节点，状态
            queue.offer(new int[]{i, 1 << i});
            visited[i][1 << i] = true;
        }

        // 记录BFS的层数，当访问满了所有节点，返回这个层数即可
        int level = 0;
        while (!queue.isEmpty()) {
            level++;
            // 一层一层遍历
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                // 当前节点
                int[] element = queue.poll();
                int curr = element[0];
                int currState = element[1];
                // 下一层的节点
                for (int next : graph[curr]) {
                    // 注意这个位运算，计算路径状态
                    int nextState = currState | (1 << next);
                    // 提前判断访问了下一个节点满足所有节点都访问了，直接返回即可
                    if (nextState == ((1 << n) - 1)) {
                        return level;
                    }
                    // 下一个节点下一个状态未访问过，下探
                    if (!visited[next][nextState]) {
                        queue.offer(new int[]{next, nextState});
                        visited[next][nextState] = true;
                    }
                }
            }
        }

        return 0;
    }
}
