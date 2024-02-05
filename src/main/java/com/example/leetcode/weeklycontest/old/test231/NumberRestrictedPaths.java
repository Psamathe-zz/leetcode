package com.example.leetcode.weeklycontest.old.test231;


import java.util.*;

/**
 * There is an undirected weighted connected graph. You are given a positive integer n which denotes that the graph has n nodes labeled from 1 to n, and an array edges where each edges[i] = [ui, vi, weighti] denotes that there is an edge between nodes ui and vi with weight equal to weighti.
 *
 * A path from node start to node end is a sequence of nodes [z0, z1, z2, ..., zk] such that z0 = start and zk = end and there is an edge between zi and zi+1 where 0 <= i <= k-1.
 *
 * The distance of a path is the sum of the weights on the edges of the path. Let distanceToLastNode(x) denote the shortest distance of a path between node n and node x. A restricted path is a path that also satisfies that distanceToLastNode(zi) > distanceToLastNode(zi+1) where 0 <= i <= k-1.
 *
 * Return the number of restricted paths from node 1 to node n. Since that number may be too large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 5, edges = [[1,2,3],[1,3,3],[2,3,1],[1,4,2],[5,2,2],[3,5,1],[5,4,10]]
 * Output: 3
 * Explanation: Each circle contains the node number in black and its distanceToLastNode value in blue. The three restricted paths are:
 * 1) 1 --> 2 --> 5
 * 2) 1 --> 2 --> 3 --> 5
 * 3) 1 --> 3 --> 5
 * Example 2:
 *
 *
 * Input: n = 7, edges = [[1,3,1],[4,1,2],[7,3,4],[2,5,3],[5,6,1],[6,7,2],[7,5,3],[2,6,4]]
 * Output: 1
 * Explanation: Each circle contains the node number in black and its distanceToLastNode value in blue. The only restricted path is 1 --> 3 --> 7.
 *
 */
public class NumberRestrictedPaths {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/number-of-restricted-paths-from-first-to-last-node/solution/dui-you-hua-dijkstra-ji-yi-hua-sou-suo-b-tif8/
     * @param n
     * @param edges
     * @return
     */
    public int countRestrictedPaths(int n, int[][] edges) {
        int cnt = 0;
        Map<Integer, List<int[]>> map = new HashMap<>();
        // 初始化邻接表
        for (int[] t : edges) {
            int x = t[0];
            int y = t[1];
            map.computeIfAbsent(x, k -> new ArrayList<>()).add(new int[]{y, t[2]});
            map.computeIfAbsent(y, k -> new ArrayList<>()).add(new int[]{x, t[2]});
        }

        // 保存到n点的 最短距离 和 受限路径数
        int[] distance = findShortPath(map,n,n);
        Long[] mem = new Long[n + 1];

        cnt = (int)findLimitedPathCount(map,1,n,distance,mem);
        return cnt;
    }

    private long findLimitedPathCount(Map<Integer, List<int[]>> map, int i, int n, int[] dis, Long[] mem) {
        if(mem[i]!=null)return mem[i];
        if(i==n)return 1;
        long cnt = 0;
        List<int[]> list = map.getOrDefault(i, Collections.emptyList());
        for (int[] arr:list){
            int next = arr[0];
            if(dis[next]<dis[i]){
                cnt += findLimitedPathCount(map,next,n,dis,mem);
                cnt %= MOD;
            }
        }
        mem[i] = cnt;
        return cnt;
    }


    public int[] findShortPath( Map<Integer, List<int[]>> map, int n, int start) {
        // 初始化dis数组和vis数组
        int[] distance = new int[n + 1];
        Arrays.fill(distance, 0x3f3f3f3f);
        boolean[] vis = new boolean[n + 1];

        // 初始化，索引0和起点的distance为0
        distance[start] = 0;
        distance[0] = 0;

        // 堆优化，将距离作为排序标准。单独用传入距离是因为PriorityQueue的上浮规则决定
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        // 把起点放进去
        queue.offer(new int[]{start,0});

        while (!queue.isEmpty()) {
            // 当队列不空，拿出一个源出来
            Integer poll = queue.poll()[0];
            if(vis[poll]) continue;
            // 标记访问
            vis[poll] = true;
            // 遍历它的相邻节点
            List<int[]> list = map.getOrDefault(poll, Collections.emptyList());
            for (int[] arr : list) {
                int next = arr[0];
                if (vis[next]) continue;
                // 更新到这个相邻节点的最短距离，与 poll出来的节点增加的距离 比较
                distance[next] = Math.min(distance[next], distance[poll] + arr[1]);
                //堆中新增节点，这里需要手动传入 next节点堆距离值。否则如果next在队列中，将永远无法上浮。
                queue.offer(new int[]{next,distance[next]});
            }
        }
        return distance;
    }

    final int MOD = 1000000007;
}
