package com.example.leetcode.weeklycontest.test188;

import java.util.*;

/**
 * Given an undirected tree consisting of n vertices numbered from 0 to n-1, which has some apples in their vertices. You spend 1 second to walk over one edge of the tree. Return the minimum time in seconds you have to spend in order to collect all apples in the tree starting at vertex 0 and coming back to this vertex.
 *
 * The edges of the undirected tree are given in the array edges, where edges[i] = [fromi, toi] means that exists an edge connecting the vertices fromi and toi. Additionally, there is a boolean array hasApple, where hasApple[i] = true means that vertex i has an apple, otherwise, it does not have any apple.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,true,true,false]
 * Output: 8
 * Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.
 * Example 2:
 *
 *
 *
 * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,false,true,false]
 * Output: 6
 * Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.
 * Example 3:
 *
 * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,false,false,false,false,false]
 * Output: 0
 *
 *
 * 4
 * [[0,1],[1,2],[0,3]]
 * [true,true,false,true]
 *
 * 5
 * [[0,1],[0,2],[1,3],[0,4]]
 * [false,false,false,true,false]
 *
 */
public class MinimumTimeCollectAllApples {
    public static void main(String[] args) {
        int n = 7;
        int[][] edges = new int[][]{{0,1},{0,2},{1,4},{1,5},{2,3},{2,6}};
        List<Boolean> hasApple = new ArrayList<>(Arrays.asList(false,false,true,false,false,true,false));
        MinimumTimeCollectAllApples minimumTimeCollectAllApples = new MinimumTimeCollectAllApples();
        int result = minimumTimeCollectAllApples.minTime(n,edges,hasApple);
        System.out.println(result);
    }
    boolean[] visited;
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        Map<Integer,List<Integer>> mymap = new HashMap<>();
        visited = new boolean[n];
        for(int[] edge:edges){
            List<Integer> list = mymap.getOrDefault(edge[0],new ArrayList<>());
            list.add(edge[1]);
            mymap.put(edge[0],list);

            list = mymap.getOrDefault(edge[1],new ArrayList<>());
            list.add(edge[0]);
            mymap.put(edge[1],list);
        }


        return dfs(0,mymap,visited,n,hasApple);
    }

    public int dfs(int current,Map<Integer,List<Integer>> mymap,boolean[] visited,int n, List<Boolean> hasApple){
        visited[current] = true;
        int result = 0;
        for(Integer value : mymap.get(current)){
            if( !visited[value]){
                int cost = dfs(value,mymap,visited,n,hasApple);
                if( cost > 0 || hasApple.get(value)){
                    result = result + 2 + cost;
                }
            }
        }
        return result;
    }


    /**
     * faster solution
     * @param n
     * @param edges
     * @param hasApple
     * @return
     */
    public int minTimeV2(int n, int[][] edges, List<Boolean> hasApple) {
        ArrayList<Integer> children[] = new ArrayList[n];
        boolean [] has = new boolean[n];
        for(int i = 0; i < n; i++)
            has[i] = hasApple.get(i);
        int m = edges.length;
        int cnt = n;
        for(int i = m - 1; i >= 0; i--)
        {
            int [] edge = edges[i];
            int parent = edge[0], child = edge[1];
            if(has[child] == true) has[parent] = true;
            else cnt--;
        }
        return 2 * cnt - 2;
    }
}
