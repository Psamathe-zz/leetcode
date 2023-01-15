package com.example.leetcode.challenge.test2022.december;

import java.util.*;

/**
 * There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
 *
 * You want to determine if there is a valid path that exists from vertex source to vertex destination.
 *
 * Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
 * Output: true
 * Explanation: There are two paths from vertex 0 to vertex 2:
 * - 0 → 1 → 2
 * - 0 → 2
 * Example 2:
 *
 *
 * Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
 * Output: false
 * Explanation: There is no path from vertex 0 to vertex 5.
 */
public class FindIfPathExistsInGraph {
    public static void main(String[] args) {
        FindIfPathExistsInGraph findIfPathExistsInGraph = new FindIfPathExistsInGraph();
        boolean res = findIfPathExistsInGraph.validPath(3, new int[][]{
                {0,1},
                {1,2},
                {2,0}
        }, 0, 2);
        System.out.println(res);
    }


    Map<Integer, Set<Integer>> map = new HashMap<>();
    boolean[] visited;
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        visited = new boolean[n];
        computeMap(edges);
        return helper(source, destination);
    }

    public boolean helper(int source, int destination) {
        if(source == destination)
            return true;
        if(visited[source])
            return false;
        visited[source] = true;
        for(Integer next: map.getOrDefault(source, new HashSet<>())) {
            if(helper(next, destination)) {
                return true;
            }
        }
        return false;
    }

    public void computeMap(int[][] edges) {
        int a;
        int b;
        for(int[] edge: edges) {
            a = edge[0];
            b = edge[1];
            computePoint(b, a);
            computePoint(a, b);
        }
    }

    private void computePoint(int a, int b) {
        map.compute(b, (k, v) -> {
            if(v == null) {
                Set<Integer> set = new HashSet<>();
                set.add(a);
                return set;
            } else {
                v.add(a);
                return v;
            }
        });
    }
}
