package com.example.leetcode.challenge.test2023.mars.week4;

import java.util.HashMap;
import java.util.HashSet;

/**
 * You are given a directed graph of n nodes numbered from 0 to n - 1, where each node has at most one outgoing edge.
 *
 * The graph is represented with a given 0-indexed array edges of size n, indicating that there is a directed edge from node i to node edges[i]. If there is no outgoing edge from node i, then edges[i] == -1.
 *
 * Return the length of the longest cycle in the graph. If no cycle exists, return -1.
 *
 * A cycle is a path that starts and ends at the same node.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: edges = [3,3,4,2,3]
 * Output: 3
 * Explanation: The longest cycle in the graph is the cycle: 2 -> 4 -> 3 -> 2.
 * The length of this cycle is 3, so 3 is returned.
 * Example 2:
 *
 *
 * Input: edges = [2,-1,3,1]
 * Output: -1
 * Explanation: There are no cycles in this graph.
 */
public class LongestCycleInGraph {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode.cn/problems/longest-cycle-in-a-graph/solution/by-lsy-31-l74u/
     * @param edges
     * @return
     */
    public int longestCycle(int[] edges) {
        int maxRing = -1;
        HashSet<Integer> set = new HashSet<>();

        for(int i = 0; i < edges.length; i++){
            //没有出边或包含重复环，直接跳过
            if(edges[i] == -1 || set.contains(i)){
                continue;
            }
            int node = i, count = 0;
            //记录每个点在此次遍历中出现顺序，便于之后求环的长度
            HashMap<Integer, Integer> map = new HashMap<>();
            //当找到环 ｜｜ 走到死胡同 ｜｜ 找到重复环时退出遍历
            while(!map.containsKey(node) && node != -1 && !set.contains(node)){
                map.put(node, count);
                node = edges[node];
                count++;
            }
            //找到环的情况下
            if(node != -1 && !set.contains(node)){
                int curLen = map.size() - map.get(node);
                maxRing = Math.max(maxRing, curLen);
                //将环中所有点添加到HashSet
                while(!set.contains(node)){
                    set.add(node);
                    node = edges[node];
                }
            }
        }
        return maxRing;
    }

    public int longestCycleV1(int[] edges) {
        int length = edges.length;

        int res = -1;
        for (int i = 0; i < length; i++) {
            res = Math.max(res , helper(i, edges));
        }
        return res;
    }

    public int helper(int start ,int[] edges) {
        int fast = start;
        int slow = start;
        int count = 0;
        do {
            if(edges[fast] == -1 || edges[edges[fast]] == -1 || edges[slow] == -1)
                return -1;
            fast = edges[edges[fast]];
            slow = edges[slow];
            count++;
        } while (fast != slow);
        return count;
    }
}
