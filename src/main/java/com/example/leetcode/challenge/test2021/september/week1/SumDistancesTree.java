package com.example.leetcode.challenge.test2021.september.week1;

import java.util.*;
import java.util.stream.Collectors;

/**
 * There is an undirected connected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.
 *
 * You are given the integer n and the array edges where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
 *
 * Return an array answer of length n where answer[i] is the sum of the distances between the ith node in the tree and all other nodes.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
 * Output: [8,12,6,10,10,10]
 * Explanation: The tree is shown above.
 * We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
 * equals 1 + 1 + 2 + 2 + 2 = 8.
 * Hence, answer[0] = 8, and so on.
 * Example 2:
 *
 *
 * Input: n = 1, edges = []
 * Output: [0]
 * Example 3:
 *
 *
 * Input: n = 2, edges = [[1,0]]
 * Output: [1,1]
 *
 */
public class SumDistancesTree {
    public static void main(String[] args) {
        SumDistancesTree sumDistancesTree = new SumDistancesTree();
        int[] res = sumDistancesTree.sumOfDistancesInTree(6, new int[][]{
                {0,1},
                {0,2},
                {2,3},
                {2,4},
                {2,5}
        });
        System.out.println(res);
    }
    int[][] map;
    int[] res;
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        map = new int[n][n];
        res = new int[n];
        for (int[] edge: edges){
            map[edge[0]][edge[1]] = 1;
            map[edge[1]][edge[0]] = 1;
        }
        for (int i = 0; i < n; i++) {
            helper(i);
        }
        return res;
    }

    public void helper(int start){
        List<Integer> visit = new ArrayList<>();
        List<Integer> added = new ArrayList<>();
        List<Integer> next = new ArrayList<>();
        visit.add(start);
        added.add(start);
        int val = 1;
        int index;
        do {
            for (Integer node : added) {
                index = 0;
                for (int nextNode: map[node]) {
                    if (nextNode == 1 && !visit.contains(index)) {
                        next.add(index);
                        res[start] += val;
                    }
                    index++;
                }
            }
            val++;
            visit.addAll(next);
            added = next.stream().collect(Collectors.toList());
            next.clear();
        }while (!added.isEmpty());
    }

    /**
     * https://leetcode-cn.com/problems/sum-of-distances-in-tree/solution/bi-guan-fang-jian-dan-yi-dong-tu-cun-chu-shu-bian-/
     * @param N
     * @param edges
     * @return
     */
    // N个节点
    private int N;
    // size[i]存储以node[i]为顶点的子树的大小，其中node[0]为整棵树的根
    private int[] size;
    // total表示node[0]到其他所有节点的距离之和
    private int total;
    // 邻接表，graph[i]中存放node[i]的所有邻居节点
    private List<List<Integer>> graph;
    private int[] ans;

    // 借用了图的概念，但整体按树的方式进行递归
    public int[] sumOfDistancesInTreeV1(int N, int[][] edges) {
        this.N = N;
        ans = new int[N];
        size = new int[N];
        total = 0;

        graph = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }
        for(int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        dfs1(0, -1, 1);
        dfs2(0, -1);
        return ans;
    }

    // 这里取node[0]为根，求得以node[i]为顶点的子树大小
    // 同时，求得node[0]到其他所有节点的距离之和total
    private void dfs1(int node, int rootNode, int depth) {
        for(int neighbor : graph.get(node)) {
            if(neighbor != rootNode) {
                total += depth;
                dfs1(neighbor, node, depth + 1);
                //各个子树的大小累加
                size[node] += size[neighbor];
            }
        }
        // 算上本节点自身
        size[node]++;
    }

    // 从根节点node[0]开始，一层一层地算node[i]到所有其他节点的距离之和
    private void dfs2(int node, int rootNode) {
        if (node == 0) {
            ans[node] = total;
        } else {
            // node所有子孙节点的数目
            int tmp1 = size[node] - 1;
            // 除node子树和node父节点之外的其他节点
            int tmp2 = N - size[node] - 1;
            ans[node] = ans[rootNode] - tmp1 + tmp2;
        }

        // 继续dfs算下一层
        for (int neighbor : graph.get(node)) {
            if (neighbor != rootNode) {
                dfs2(neighbor, node);
            }
        }
    }


}
