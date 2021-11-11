package com.example.leetcode.weeklycontest.test266;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * There is an undirected graph with n nodes numbered from 0 to n - 1 (inclusive). You are given a 0-indexed integer array values where values[i] is the value of the ith node. You are also given a 0-indexed 2D integer array edges, where each edges[j] = [uj, vj, timej] indicates that there is an undirected edge between the nodes uj and vj, and it takes timej seconds to travel between the two nodes. Finally, you are given an integer maxTime.
 *
 * A valid path in the graph is any path that starts at node 0, ends at node 0, and takes at most maxTime seconds to complete. You may visit the same node multiple times. The quality of a valid path is the sum of the values of the unique nodes visited in the path (each node's value is added at most once to the sum).
 *
 * Return the maximum quality of a valid path.
 *
 * Note: There are at most four edges connected to each node.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: values = [0,32,10,43], edges = [[0,1,10],[1,2,15],[0,3,10]], maxTime = 49
 * Output: 75
 * Explanation:
 * One possible path is 0 -> 1 -> 0 -> 3 -> 0. The total time taken is 10 + 10 + 10 + 10 = 40 <= 49.
 * The nodes visited are 0, 1, and 3, giving a maximal path quality of 0 + 32 + 43 = 75.
 * Example 2:
 *
 *
 * Input: values = [5,10,15,20], edges = [[0,1,10],[1,2,10],[0,3,10]], maxTime = 30
 * Output: 25
 * Explanation:
 * One possible path is 0 -> 3 -> 0. The total time taken is 10 + 10 = 20 <= 30.
 * The nodes visited are 0 and 3, giving a maximal path quality of 5 + 20 = 25.
 * Example 3:
 *
 *
 * Input: values = [1,2,3,4], edges = [[0,1,10],[1,2,11],[2,3,12],[1,3,13]], maxTime = 50
 * Output: 7
 * Explanation:
 * One possible path is 0 -> 1 -> 3 -> 1 -> 0. The total time taken is 10 + 13 + 13 + 10 = 46 <= 50.
 * The nodes visited are 0, 1, and 3, giving a maximal path quality of 1 + 2 + 4 = 7.
 * Example 4:
 *
 *
 *
 * Input: values = [0,1,2], edges = [[1,2,10]], maxTime = 10
 * Output: 0
 * Explanation:
 * The only path is 0. The total time taken is 0.
 * The only node visited is 0, giving a maximal path quality of 0.
 *
 */
public class MaximumPathQuality {
    public static void main(String[] args) {

    }


    int [] values;
    int maxTime;
    Map<Integer, List<int []>> adjvex = new HashMap<>();
    boolean [] visited;
    int res = 0;

    public void backtrace(int x, int past_time, int cur_money)
    {
        if (x == 0)
        {
            res = Math.max(res, cur_money);
        }

        for (int [] y_cost: adjvex.getOrDefault(x, new ArrayList<>()))
        {
            int y = y_cost[0],    cost = y_cost[1];
            if (past_time + cost <= maxTime)
            {
                if (visited[y] == false)
                {
                    visited[y] = true;
                    backtrace(y, past_time + cost, cur_money + values[y]);
                    visited[y] = false;
                }
                else
                {
                    backtrace(y, past_time + cost, cur_money);
                }
            }
        }
    }

    public int maximalPathQuality(int[] values, int[][] edges, int maxTime)
    {
        this.values = values;
        this.maxTime = maxTime;
        int N = values.length;
        this.visited = new boolean [N];

        for (int [] edge: edges)
        {
            int x = edge[0],    y = edge[1],    cost = edge[2];
            adjvex.putIfAbsent(x, new ArrayList<>());
            adjvex.putIfAbsent(y, new ArrayList<>());
            adjvex.get(x).add(new int [] {y, cost});
            adjvex.get(y).add(new int [] {x, cost});
        }

        visited[0] = true;
        backtrace(0, 0, values[0]);
        return res;
    }
}
