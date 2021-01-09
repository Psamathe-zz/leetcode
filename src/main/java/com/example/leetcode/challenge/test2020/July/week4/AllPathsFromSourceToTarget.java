package com.example.leetcode.challenge.test2020.July.week4;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1, and return them in any order.
 *
 * The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j for which the edge (i, j) exists.
 *
 * Example:
 * Input: [[1,2], [3], [3], []]
 * Output: [[0,1,3],[0,2,3]]
 * Explanation: The graph looks like this:
 * 0--->1
 * |    |
 * v    v
 * 2--->3
 * There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
 * Note:
 *
 * The number of nodes in the graph will be in the range [2, 15].
 * You can print different paths in any order, but you should keep the order of nodes inside one path.
 */
public class AllPathsFromSourceToTarget {
    public static void main(String[] args) {
        int[][] graph = new int[][]{
                {1,2},
                {3},
                {3},
                {}
        };
        AllPathsFromSourceToTarget allPathsFromSourceToTarget = new AllPathsFromSourceToTarget();
        allPathsFromSourceToTarget.allPathsSourceTarget(graph);
    }
    int length;
    List<List<Integer>> res;
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        res = new ArrayList<>();
        length = graph.length;
        Stack<Integer> stack = new Stack<>();
        stack.add(0);
        dfs(0,graph,stack);
        return res;
    }

    public void dfs(int current, int[][] graph, Stack<Integer> stack){
        for (int i = 0; i < graph[current].length; i++) {
            stack.add(graph[current][i]);
            if(graph[current][i] == length -1){
                res.add(new ArrayList<>(stack));
                stack.pop();
            } else {
                dfs(graph[current][i], graph, stack);
                stack.pop();
            }
        }
    }
}
