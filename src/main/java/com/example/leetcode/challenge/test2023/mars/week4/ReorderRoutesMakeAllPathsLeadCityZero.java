package com.example.leetcode.challenge.test2023.mars.week4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel between two different cities (this network form a tree). Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.
 *
 * Roads are represented by connections where connections[i] = [ai, bi] represents a road from city ai to city bi.
 *
 * This year, there will be a big event in the capital (city 0), and many people want to travel to this city.
 *
 * Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.
 *
 * It's guaranteed that each city can reach city 0 after reorder.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
 * Output: 3
 * Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
 * Example 2:
 *
 *
 * Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
 * Output: 2
 * Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
 * Example 3:
 *
 * Input: n = 3, connections = [[1,0],[2,0]]
 * Output: 0
 *
 */
public class ReorderRoutesMakeAllPathsLeadCityZero {
    public static void main(String[] args) {

    }

    int result;
    boolean[] visited;
    public int minReorder(int n, int[][] connections) {
        result = 0;
        visited = new boolean[n];
        Map<Integer, List<Integer[]>> map = new HashMap<>();
        for(int[] connection : connections){
            if (map.get(connection[0]) == null) {
                List<Integer[]> li = new ArrayList<>();
                Integer[] con = new Integer[2];
                con[0] = connection[0];
                con[1] = connection[1];
                li.add(con);
                map.put(connection[0], li);
            }else {
                List<Integer[]> li = map.get(connection[0]);
                Integer[] con = new Integer[2];
                con[0] = connection[0];
                con[1] = connection[1];
                li.add(con);
            }

            if (map.get(connection[1]) == null) {
                List<Integer[]> li = new ArrayList<>();
                Integer[] con = new Integer[2];
                con[0] = connection[0];
                con[1] = connection[1];
                li.add(con);
                map.put(connection[1], li);
            }
            else {
                List<Integer[]> li2 = map.get(connection[1]);
                Integer[] con2 = new Integer[2];
                con2[0] = connection[0];
                con2[1] = connection[1];
                li2.add(con2);
            }

        }
        help(map,0);
        return result;
    }

    public void help(Map<Integer, List<Integer[]>> mapTo,int start){
        if(visited[start])
            return;
        visited[start] = true;
        List<Integer[]> list  = mapTo.get(start);
        for(Integer[] road : list){
            if(road[0].equals(start) && !visited[road[1]]){
                result++;
                help(mapTo,road[1]);
            } else if(road[1].equals(start) && !visited[road[0]]){
                help(mapTo,road[0]);
            }
        }
    }
}
