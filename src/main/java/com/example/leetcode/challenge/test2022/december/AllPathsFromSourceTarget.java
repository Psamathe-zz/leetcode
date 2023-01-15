package com.example.leetcode.challenge.test2022.december;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1 and return them in any order.
 *
 * The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: graph = [[1,2],[3],[3],[]]
 * Output: [[0,1,3],[0,2,3]]
 * Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
 * Example 2:
 *
 *
 * Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
 * Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
 */
public class AllPathsFromSourceTarget {
    public static void main(String[] args) {

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
