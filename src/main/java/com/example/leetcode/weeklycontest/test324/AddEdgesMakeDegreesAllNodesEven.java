package com.example.leetcode.weeklycontest.test324;

import java.util.*;

/**
 * There is an undirected graph consisting of n nodes numbered from 1 to n. You are given the integer n and a 2D array edges where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi. The graph can be disconnected.
 *
 * You can add at most two additional edges (possibly none) to this graph so that there are no repeated edges and no self-loops.
 *
 * Return true if it is possible to make the degree of each node in the graph even, otherwise return false.
 *
 * The degree of a node is the number of edges connected to it.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 5, edges = [[1,2],[2,3],[3,4],[4,2],[1,4],[2,5]]
 * Output: true
 * Explanation: The above diagram shows a valid way of adding an edge.
 * Every node in the resulting graph is connected to an even number of edges.
 * Example 2:
 *
 *
 * Input: n = 4, edges = [[1,2],[3,4]]
 * Output: true
 * Explanation: The above diagram shows a valid way of adding two edges.
 * Example 3:
 *
 *
 * Input: n = 4, edges = [[1,2],[1,3],[1,4]]
 * Output: false
 * Explanation: It is not possible to obtain a valid graph with adding at most 2 edges.
 */
public class AddEdgesMakeDegreesAllNodesEven {
    public static void main(String[] args) {

    }
    public boolean isPossible(int n, List<List<Integer>> edges) {
        var g = new Set[n + 1];
        Arrays.setAll(g, e -> new HashSet<Integer>());
        for (var e : edges) {
            int x = e.get(0), y = e.get(1);
            g[x].add(y);
            g[y].add(x);
        }
        var odd = new ArrayList<Integer>();
        for (var i = 1; i <= n; ++i)
            if (g[i].size() % 2 > 0) odd.add(i);
        var m = odd.size();
        if (m == 0)
            return true;
        if (m == 2) {
            int x = odd.get(0), y = odd.get(1);
            if (!g[x].contains(y)) return true;
            for (var i = 1; i <= n; ++i)
                if (i != x && i != y && !g[i].contains(x) && !g[i].contains(y))
                    return true;
            return false;
        }
        if (m == 4) {
            int a = odd.get(0), b = odd.get(1), c = odd.get(2), d = odd.get(3);
            return !g[a].contains(b) && !g[c].contains(d) ||
                    !g[a].contains(c) && !g[b].contains(d) ||
                    !g[a].contains(d) && !g[b].contains(c);
        }
        return false;
    }

}
