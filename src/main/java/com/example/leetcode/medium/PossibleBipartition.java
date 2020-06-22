package com.example.leetcode.medium;

import java.util.*;

/**
 * Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.
 *
 * Each person may dislike some other people, and they should not go into the same group.
 *
 * Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.
 *
 * Return true if and only if it is possible to split everyone into two groups in this way.
 *
 *
 *
 * Example 1:
 *
 * Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
 * Output: true
 * Explanation: group1 [1,4], group2 [2,3]
 * Example 2:
 *
 * Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
 * Output: false
 * Example 3:
 *
 * Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
 * Output: false
 *
 * 4 2 9 5 6 10
 * 7 8 1 3
 */
public class PossibleBipartition {

    public static void main(String[] args) {
        int N = 3;
        int[][] dislikes = new int[][] {{1,2},{1,3},{2,3}};
        PossibleBipartition possibleBipartition = new PossibleBipartition();
        boolean result = possibleBipartition.possibleBipartition(N,dislikes);
        System.out.println(result);
    }
    public boolean possibleBipartition(int N, int[][] dislikes) {
        Set<Integer> group0 = new HashSet<>();
        Set<Integer> group1 = new HashSet<>();
        List<Relation> relations = new ArrayList<>();
        List<Relation> temp = new ArrayList<>();
        for(int[] dislike : dislikes){
            if(dislike[0] > N || dislike[1] > N)
                return false;
            relations.add(new Relation(dislike[0],dislike[1]));
        }
        if(relations.size()>0){
            group0.add(relations.get(0).dislike0);
            group1.add(relations.get(0).dislike1);
        }
        while (relations.size() > 0){
            for(Relation relation:relations){
                if((group0.contains(relation.dislike0) && group0.contains(relation.dislike1)) || (group1.contains(relation.dislike0) && group1.contains(relation.dislike1)))
                    return false;
                else if(group0.contains(relation.dislike0) )
                    group1.add(relation.dislike1);
                else if(group0.contains(relation.dislike1) )
                    group1.add(relation.dislike0);
                else if(group1.contains(relation.dislike0) )
                    group0.add(relation.dislike1);
                else if(group1.contains(relation.dislike1) )
                    group0.add(relation.dislike0);
                else
                    temp.add(relation);
            }
            if(relations.size() == temp.size()){
                group0.add(relations.get(0).dislike0);
                group1.add(relations.get(0).dislike1);
            }
            relations.clear();
            relations.addAll(temp);
            temp.clear();
        }
        return true;
    }

    public class Relation{
        int dislike0;
        int dislike1;

        public Relation(int dislike0, int dislike1) {
            this.dislike0 = dislike0;
            this.dislike1 = dislike1;
        }
    }


    /**
     * faster solution
     * @param N
     * @param dislikes
     * @return
     *
     *  * Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
     *  * Output: true
     *  * Explanation: group1 [1,4], group2 [2,3]
     *  * Example 2:
     *  Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
     *  * Output: false
     *  * Example 3:
     */
    public boolean possibleBipartitionV2(int N, int[][] dislikes) {
        int[] another = new int[N+1];
        for (int i = 0; i <= N; i++) {
            another[i] = i; // initial
        }

        for (int[] dislike : dislikes) {
            int a = dislike[0];
            int b = dislike[1];

            if (another[a] == a && another[b] == b) {
                another[a] = b;
                another[b] = a;
            } else if (another[a] == a && another[b] != b) {
                another[a] = another[another[b]];
            } else if (another[b] ==b && another[a] != a) {
                another[b] = another[another[a]];
            } else if (another[b] == another[a]) {
                return false;
            }
        }
        return true;
    }


    /**
     * faster solution
     * @param N
     * @param dislikes
     * @return
     */
    public boolean possibleBipartitionV3(int N, int[][] dislikes) {
        Map <Integer, List <Integer>> graph = new HashMap<>();
        for (int[] ed: dislikes){
            List <Integer> neis = graph.getOrDefault(ed[0], null);
            if (neis == null) neis = new ArrayList <> ();
            neis.add(ed[1]);
            graph.put(ed[0], neis);
            // symmetric
            neis = graph.getOrDefault(ed[1], null);
            if (neis == null) neis = new ArrayList <> ();
            neis.add(ed[0]);
            graph.put(ed[1], neis);
        }
        int[] colors = new int[N+1];
        Arrays.fill(colors, 0);
        for (int i = 1; i <= N; i++){
            if (colors[i] == 0){
                boolean res = dfs(graph, colors, i, 1);
                if (!res) return false;
            }
        }
        return true;
    }

    public boolean dfs(Map <Integer, List<Integer>> graph, int[] colors, int node, int color){
        if (colors[node] != 0) {
            // this checks if the assigned color = actual color, if theres a conflict, then
            // we can't bipartite
            return colors[node] == color;
        }
        colors[node] = color;
        List <Integer> neis = graph.getOrDefault(node, null);
        if (neis == null){
            // System.out.println("here");
            return true;}
        for (int i = 0; i < neis.size(); i++){
            // this assigns what the color should be for its neighbor
            boolean res = dfs(graph, colors, neis.get(i), -color);
            if (!res) return false;
        }
        return true;
    }
}
