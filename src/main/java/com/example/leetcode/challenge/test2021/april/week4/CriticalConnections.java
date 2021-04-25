package com.example.leetcode.challenge.test2021.april.week4;


import java.util.*;

/**
 * There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network where connections[i] = [a, b] represents a connection between servers a and b. Any server can reach any other server directly or indirectly through the network.
 *
 * A critical connection is a connection that, if removed, will make some server unable to reach some other server.
 *
 * Return all critical connections in the network in any order.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
 * Output: [[1,3]]
 * Explanation: [[3,1]] is also accepted.
 */
public class CriticalConnections {
    public static void main(String[] args) {

    }
    int[] min;
    List<List<Integer>> res = new ArrayList<>();
    Map<Integer, Set<Integer>> map = new HashMap<>();
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        min = new int[n];
        Arrays.fill(min,-1);
        for (List<Integer> connection : connections){
            map.compute(connection.get(0),(k,v) -> {
                if(v == null)
                    v = new HashSet<>();
                v.add(connection.get(1));
                return v;
            });
            map.compute(connection.get(1),(k,v) -> {
                if(v == null)
                    v = new HashSet<>();
                v.add(connection.get(0));
                return v;
            });
        }

        helper(0,-1,0);
        return res;

    }

    public int helper(int cur, int par,int lvl ){
        min[cur] = lvl + 1;
        for ( Integer child : map.get(cur)){
            if(child == par)
                continue;
            else if(min[child] == -1){
                min[cur] = Math.min(min[cur],helper(child, cur, lvl + 1));
            } else {
                min[cur] = Math.min(min[cur],min[child]);
            }
        }
        System.out.println(min[cur]);
        if(min[cur] == lvl + 1 && cur != 0) {
            List<Integer> con = new ArrayList<>();
            con.add(cur);
            con.add(par);
            res.add(con);
        }
        return min[cur];
    }
    /**
     * https://leetcode.jp/leetcode-1192-critical-connections-in-a-network-%E8%A7%A3%E9%A2%98%E6%80%9D%E8%B7%AF%E5%88%86%E6%9E%90/
     */
    List<List<Integer>> res1 = new ArrayList<>(); // 返回结果
    int[] deepArray; // 深度数组
    ArrayList<Integer>[] map1; // 结构图
    public List<List<Integer>> criticalConnectionsV0(int n, List<List<Integer>> connections) {
        deepArray = new int[n]; // 初始化深度数组
        Arrays.fill(deepArray, -1); // 所有节点初始深度为-1
        map1 = new ArrayList[n]; // 初始化结构图map[i]代表节点i可以连通哪些节点
        for(int i=0;i<n;i++){
            map1[i] = new ArrayList<>();
        }
        // 构建路径图
        for(List<Integer> connection : connections){
            map1[connection.get(0)].add(connection.get(1));
            map1[connection.get(1)].add(connection.get(0));
        }
        dfs(0, 0, 0); // 开始dfs
        return res;
    }
    // current为当前节点
    // previous为前节点
    // deep为当前深度
    // 返回值为当前节点所有dfs路径终点的最小深度
    private int dfs(int current, int previous, int deep){
        deepArray[current] = deep; // 将当前深度存入深度数组
        int result = Integer.MAX_VALUE; // 返回值
        for(int i : map1[current]){ // 遍历当前节点能走的所有节点
            if(i == previous){ // 不能走回头路
                continue;
            }
            int endDeep; // dfs终点深度
            if(deepArray[i]==-1){ // 深度为-1的点没走过，可以dfs
                endDeep = dfs(i, current, deep+1);
                // 如果深度大于当前深度，说明当前点不在闭环上
                // 当前点与下一节点i之间的连线为答案之一
                if(endDeep > deep){
                    List<Integer> list = new ArrayList<>();
                    list.add(current);
                    list.add(i);
                    res.add(list);
                }
            }else{
                // i节点深度不为-1，说明已经走过，i节点为dfs终点
                endDeep = deepArray[i];
            }
            // 更新最小深度
            result = Math.min(result, endDeep);
        }
        return result; // 返回最小深度
    }
}
