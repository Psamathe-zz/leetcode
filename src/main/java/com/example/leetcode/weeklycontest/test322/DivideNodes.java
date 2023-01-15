package com.example.leetcode.weeklycontest.test322;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given a positive integer n representing the number of nodes in an undirected graph. The nodes are labeled from 1 to n.
 *
 * You are also given a 2D integer array edges, where edges[i] = [ai, bi] indicates that there is a bidirectional edge between nodes ai and bi. Notice that the given graph may be disconnected.
 *
 * Divide the nodes of the graph into m groups (1-indexed) such that:
 *
 * Each node in the graph belongs to exactly one group.
 * For every pair of nodes in the graph that are connected by an edge [ai, bi], if ai belongs to the group with index x, and bi belongs to the group with index y, then |y - x| = 1.
 * Return the maximum number of groups (i.e., maximum m) into which you can divide the nodes. Return -1 if it is impossible to group the nodes with the given conditions.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 6, edges = [[1,2],[1,4],[1,5],[2,6],[2,3],[4,6]]
 * Output: 4
 * Explanation: As shown in the image we:
 * - Add node 5 to the first group.
 * - Add node 1 to the second group.
 * - Add nodes 2 and 4 to the third group.
 * - Add nodes 3 and 6 to the fourth group.
 * We can see that every edge is satisfied.
 * It can be shown that that if we create a fifth group and move any node from the third or fourth group to it, at least on of the edges will not be satisfied.
 * Example 2:
 *
 * Input: n = 3, edges = [[1,2],[2,3],[3,1]]
 * Output: -1
 * Explanation: If we add node 1 to the first group, node 2 to the second group, and node 3 to the third group to satisfy the first two edges, we can see that the third edge will not be satisfied.
 * It can be shown that no grouping is possible.
 *
 */
public class DivideNodes {

    public static void main(String[] args) {

    }

    /**
     * https://leetcode.cn/problems/divide-nodes-into-the-maximum-number-of-groups/solution/mei-ju-qi-dian-pao-bfs-by-endlesscheng-s5bu/
     *
     */
    private List<Integer>[] g;
    private final List<Integer> nodes = new ArrayList<>();
    private int[] time, color; // time 充当 vis 数组的作用（避免在 BFS 内部重复创建 vis 数组）
    private int clock;

    public int magnificentSets(int n, int[][] edges) {
        g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (var e : edges) {
            int x = e[0] - 1, y = e[1] - 1;
            g[x].add(y);
            g[y].add(x);
        }

        time = new int[n];
        color = new int[n];
        var ans = 0;
        for (var i = 0; i < n; i++) {
            if (color[i] != 0) continue;
            nodes.clear();
            if (!isBipartite(i, 1)) return -1; // 如果不是二分图（有奇环），则无法分组
            // 否则一定可以分组
            var maxDepth = 0;
            for (var x : nodes) // 枚举连通块的每个点，作为起点 BFS，求最大深度
                maxDepth = Math.max(maxDepth, bfs(x));
            ans += maxDepth;
        }
        return ans;
    }

    // 二分图判定，原理见视频讲解
    private boolean isBipartite(int x, int c) {
        nodes.add(x);
        color[x] = c;
        for (var y : g[x])
            if (color[y] == c || color[y] == 0 && !isBipartite(y, -c))
                return false;
        return true;
    }

    // 返回从 start 出发的最大深度
    private int bfs(int start) {
        var depth = 0;
        time[start] = ++clock;
        var q = new ArrayList<Integer>();
        q.add(start);
        while (!q.isEmpty()) {
            var tmp = q;
            q = new ArrayList<>();
            for (var x : tmp)
                for (var y : g[x])
                    if (time[y] != clock) { // 没有在同一次 BFS 中访问过
                        time[y] = clock;
                        q.add(y);
                    }
            ++depth;
        }
        return depth;
    }

}
