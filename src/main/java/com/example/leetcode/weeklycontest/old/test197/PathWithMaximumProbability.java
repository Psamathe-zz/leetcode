package com.example.leetcode.weeklycontest.old.test197;

import java.util.*;

/**
 * You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of success of traversing that edge succProb[i].
 *
 * Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success probability.
 *
 * If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
 * Output: 0.25000
 * Explanation: There are two paths from start to end, one having a probability of success = 0.2 and the other has 0.5 * 0.5 = 0.25.
 * Example 2:
 *
 *
 *
 * Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
 * Output: 0.30000
 * Example 3:
 *
 *
 *
 * Input: n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
 * Output: 0.00000
 * Explanation: There is no path between 0 and 2.
 *
 * 5
 * [[1,4],[2,4],[0,4],[0,3],[0,2],[2,3]]
 * [0.37,0.17,0.93,0.23,0.39,0.04]
 * 3
 * 4
 *
 * Constraints:
 *
 * 2 <= n <= 10^4
 * 0 <= start, end < n
 * start != end
 * 0 <= a, b < n
 * a != b
 * 0 <= succProb.length == edges.length <= 2*10^4
 * 0 <= succProb[i] <= 1
 * There is at most one edge between every two nodes.
 */
public class PathWithMaximumProbability {
    public static void main(String[] args) {
        int n = 5;
        int[][] edges = new int[][]{
                {1,4},
                {2,4},
                {0,4},
                {0,3},
                {0,2},
                {2,3},
        };
        double[] succProb = new double[]{0.37,0.17,0.93,0.23,0.39,0.04};
        int start = 3;
        int end = 4;
        PathWithMaximumProbability pathWithMaximumProbability = new PathWithMaximumProbability();
        double res = pathWithMaximumProbability.maxProbability(n,edges,succProb,start,end);
        System.out.println(res);
    }

    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        List<Node> list;
        Map<Integer,List<Node>> grid = new HashMap<>();
        for (int i = 0; i < edges.length; ++i) {
            int from = edges[i][0], to = edges[i][1];
            if(!grid.containsKey(from)){
                list = new ArrayList<>();
            } else {
                list = grid.get(from);
            }
            list.add(new Node(to, succProb[i]));
            grid.put(from,list);

            if(!grid.containsKey(to)){
                list = new ArrayList<>();
            } else {
                list = grid.get(to);
            }
            list.add(new Node(from, succProb[i]));
            grid.put(to,list);
        }

        double[] distance = new double[n];
        distance[start] = 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int from = queue.poll();
            if(grid.containsKey(from)) {
                for (int i = 0; i < grid.get(from).size(); ++i) {
                    int to = grid.get(from).get(i).to;
                    double probability = grid.get(from).get(i).probability;
                    if (distance[to] < distance[from] * probability) {
                        queue.add(to);
                        distance[to] = distance[from] * probability;
                    }
                }
            }
        }

        return distance[end];
    }

    public class Node{
        int to;
        double probability;

        public Node(int to, double probability) {
            this.to = to;
            this.probability = probability;
        }
    }
    /**
     * Dijkstra
     * @param n
     * @param edges
     * @param succProb
     * @param start
     * @param end
     * @return
     */
    public double maxProbabilityDijkstra(int n, int[][] edges, double[] succProb, int start, int end) {
        double[][] map = new double[n][n];
        int edgeCount = edges.length;
        boolean[] visited = new boolean[n];
        double[] distance = new double[n];
        for (int i = 0; i < edgeCount; i++) {
            map[edges[i][0]][edges[i][1]] = succProb[i];
            map[edges[i][1]][edges[i][0]] = succProb[i];
        }
        for(int i=0; i<n; i++){
            visited[i] = false;
            distance[i] = map[start][i];
        }

        visited[start] = true;
        distance[start] = 0;
        int k = -1;
        int last = -1;
        double max = 0;
        for(int i=1; i<n; i++){
            max = 0;

            //寻找最短路径
            for(int j=0; j<n; j++){
                if(visited[j] == false && distance[j] > max){
                    k = j;
                    max = distance[j];
                }
            }
            visited[k] = true;
            if(last != k)
                last = k;
            else
                break;
            if(k == end)
                break;
            //更新Matrix点值
            for(int j=0; j<n; j++){
                double len = map[k][j] == 0 ? 0: max * map[k][j];
                if(visited[j] == false && len > distance[j]){
                    distance[j] = len;
                }
            }
        }
        return distance[end];
    }
    /**
     * Floyd 超时
     * @param n
     * @param edges
     * @param succProb
     * @param start
     * @param end
     * @return
     */
    
    public double maxProbabilityFloyd(int n, int[][] edges, double[] succProb, int start, int end) {
        double[][] map = new double[n][n];
        int edgeCount = edges.length;
        for (int i = 0; i < edgeCount; i++) {
            map[edges[i][0]][edges[i][1]] = succProb[i];
            map[edges[i][1]][edges[i][0]] = succProb[i];
        }

        for (int x = 0; x < n; x++) {//进行Floyd算法，从0到n-1所有可能进行遍历
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] < map[i][x] * map[x][j]) {
                        map[i][j] = map[i][x] * map[x][j];
                    }
                }
            }
        }

        return map[start][end];
    }
}
