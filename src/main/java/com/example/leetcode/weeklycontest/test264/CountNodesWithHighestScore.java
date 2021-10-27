package com.example.leetcode.weeklycontest.test264;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * There is a binary tree rooted at 0 consisting of n nodes. The nodes are labeled from 0 to n - 1. You are given a 0-indexed integer array parents representing the tree, where parents[i] is the parent of node i. Since node 0 is the root, parents[0] == -1.
 *
 * Each node has a score. To find the score of a node, consider if the node and the edges connected to it were removed. The tree would become one or more non-empty subtrees. The size of a subtree is the number of the nodes in it. The score of the node is the product of the sizes of all those subtrees.
 *
 * Return the number of nodes that have the highest score.
 *
 *
 *
 * Example 1:
 *
 * example-1
 * Input: parents = [-1,2,0,2,0]
 * Output: 3
 * Explanation:
 * - The score of node 0 is: 3 * 1 = 3
 * - The score of node 1 is: 4 = 4
 * - The score of node 2 is: 1 * 1 * 2 = 2
 * - The score of node 3 is: 4 = 4
 * - The score of node 4 is: 4 = 4
 * The highest score is 4, and three nodes (node 1, node 3, and node 4) have the highest score.
 * Example 2:
 *
 * example-2
 * Input: parents = [-1,2,0]
 * Output: 2
 * Explanation:
 * - The score of node 0 is: 2 = 2
 * - The score of node 1 is: 2 = 2
 * - The score of node 2 is: 1 * 1 = 1
 * The highest score is 2, and two nodes (node 0 and node 1) have the highest score.
 */
public class CountNodesWithHighestScore {
    public static void main(String[] args) {
        CountNodesWithHighestScore countNodesWithHighestScore = new CountNodesWithHighestScore();
        countNodesWithHighestScore.countHighestScoreNodes(new int[]{-1,2,0,2,0});
    }

    public int countHighestScoreNodes(int[] parents) {
        Map<Integer, List<Integer>> map = new HashMap();
        int[] count = new int[parents.length];
        for(int i = 1; i < parents.length; i++){
            List<Integer> list = new ArrayList(map.getOrDefault( parents[i], new ArrayList()));
            list.add(i);
            map.put(parents[i], list);
        }
        DFS(0, map, count);
        long scoreMax = Integer.MIN_VALUE;
        int nodes = 0;
        for(int i = 0; i < parents.length; i++){
            long score = 1;
            if(parents[i] == -1){
                List<Integer> list = new ArrayList( map.getOrDefault( i, new ArrayList() ) );
                for(int num : list)
                    score *= count[num];
            }else{
                score = count[0] - count[i];
                List<Integer> list = new ArrayList( map.getOrDefault( i, new ArrayList() ) );
                for(int num : list)
                    score *= count[num];
            }
            if(scoreMax < score){
                scoreMax = score;
                nodes = 1;
            }else if(score == scoreMax) ++nodes;
        }
        return nodes;
    }

    public void DFS(int index, Map<Integer, List<Integer>> map, int[] count){
        List<Integer> list = new ArrayList( map.getOrDefault( index, new ArrayList() ) );
        if(list.size() == 0){
            count[index] = 1;
            return;
        }
        for(int num : list){
            DFS(num, map, count);
            count[index] += count[num];
        }
        ++count[index];
    }
}
