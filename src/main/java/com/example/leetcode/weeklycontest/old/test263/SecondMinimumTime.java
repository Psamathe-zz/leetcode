package com.example.leetcode.weeklycontest.old.test263;

import java.util.*;

/**
 * A city is represented as a bi-directional connected graph with n vertices where each vertex is labeled from 1 to n (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself. The time taken to traverse any edge is time minutes.
 *
 * Each vertex has a traffic signal which changes its color from green to red and vice versa every change minutes. All signals change at the same time. You can enter a vertex at any time, but can leave a vertex only when the signal is green. You cannot wait at a vertex if the signal is green.
 *
 * The second minimum value is defined as the smallest value strictly larger than the minimum value.
 *
 * For example the second minimum value of [2, 3, 4] is 3, and the second minimum value of [2, 2, 4] is 4.
 * Given n, edges, time, and change, return the second minimum time it will take to go from vertex 1 to vertex n.
 *
 * Notes:
 *
 * You can go through any vertex any number of times, including 1 and n.
 * You can assume that when the journey starts, all signals have just turned green.
 *
 *
 * Example 1:
 *
 *
 * Input: n = 5, edges = [[1,2],[1,3],[1,4],[3,4],[4,5]], time = 3, change = 5
 * Output: 13
 * Explanation:
 * The figure on the left shows the given graph.
 * The blue path in the figure on the right is the minimum time path.
 * The time taken is:
 * - Start at 1, time elapsed=0
 * - 1 -> 4: 3 minutes, time elapsed=3
 * - 4 -> 5: 3 minutes, time elapsed=6
 * Hence the minimum time needed is 6 minutes.
 *
 * The red path shows the path to get the second minimum time.
 * - Start at 1, time elapsed=0
 * - 1 -> 3: 3 minutes, time elapsed=3
 * - 3 -> 4: 3 minutes, time elapsed=6
 * - Wait at 4 for 4 minutes, time elapsed=10
 * - 4 -> 5: 3 minutes, time elapsed=13
 * Hence the second minimum time is 13 minutes.
 * Example 2:
 *
 *
 * Input: n = 2, edges = [[1,2]], time = 3, change = 2
 * Output: 11
 * Explanation:
 * The minimum time path is 1 -> 2 with time = 3 minutes.
 * The second minimum time path is 1 -> 2 -> 1 -> 2 with time = 11 minutes.
 *
 */
public class SecondMinimumTime {
    public static void main(String[] args) {

    }

    public int secondMinimum(int n, int[][] edges, int time, int change) {
        int flag = 0; // 记录最短路径出现的次数
        int step = 0; // 记录最短路径对应的步长
        Map<Integer, List<Integer>> map = new HashMap();
        for(int[] arr : edges){
            map.putIfAbsent(arr[0], new ArrayList<>());
            map.putIfAbsent(arr[1], new ArrayList<>());
            map.get(arr[0]).add(arr[1]);
            map.get(arr[1]).add(arr[0]);
        }
        Queue<Set<Integer>> queue = new ArrayDeque<>();
        Set<Integer> set = new HashSet();
        int[] arr = new int[n];  // 记录每个节点访问的次数
        set.add(1);
        queue.offer(set);
        int distance = 0;  // 记录当前走的距离
        while(!queue.isEmpty()){
            ++distance;
            set = new HashSet();
            for(int cur : queue.poll()){
                for(int num : map.get(cur)){
                    if(num == n && distance > step){
                        // 判断是否移动到终点，是否比上次步长大
                        ++flag;
                        step = distance;
                    }
                    // 找到第二小的步长，返回
                    if(flag == 2) return getTime(step, time, change);
                    arr[num - 1] += 1; // 访问的节点次数加一
                    if(arr[num - 1] <= 2) // 访问次数大于2的节点不再访问
                        set.add(num);
                }
            }
            queue.offer(set);
        }
        return -1;
    }

    public int getTime(int step, int time, int change){
        // 计算走step步的耗时
        int t = 0;
        for(int i = 1; i <= step; i++){
            //奇数，不能走
            if ( (t / change) % 2 == 1)
                t += change - (t % change);
            t += time;
        }
        return t;
    }
}
