package com.example.leetcode.weeklycontest.test322;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given a positive integer n representing n cities numbered from 1 to n. You are also given a 2D array roads where roads[i] = [ai, bi, distancei] indicates that there is a bidirectional road between cities ai and bi with a distance equal to distancei. The cities graph is not necessarily connected.
 *
 * The score of a path between two cities is defined as the minimum distance of a road in this path.
 *
 * Return the minimum possible score of a path between cities 1 and n.
 *
 * Note:
 *
 * A path is a sequence of roads between two cities.
 * It is allowed for a path to contain the same road multiple times, and you can visit cities 1 and n multiple times along the path.
 * The test cases are generated such that there is at least one path between 1 and n.
 *
 *
 * Example 1:
 *
 *
 * Input: n = 4, roads = [[1,2,9],[2,3,6],[2,4,5],[1,4,7]]
 * Output: 5
 * Explanation: The path from city 1 to 4 with the minimum score is: 1 -> 2 -> 4. The score of this path is min(9,5) = 5.
 * It can be shown that no other path has less score.
 * Example 2:
 *
 *
 * Input: n = 4, roads = [[1,2,2],[1,3,4],[3,4,7]]
 * Output: 2
 * Explanation: The path from city 1 to 4 with the minimum score is: 1 -> 2 -> 1 -> 3 -> 4. The score of this path is min(2,2,4,7) = 2.
 *
 */
public class MinimumScorePathBetweenTwoCities {

    public static void main(String[] args) {

    }

    /**
     * https://leetcode.cn/problems/minimum-score-of-a-path-between-two-cities/solution/java61hao-miao-bing-cha-ji-by-zhy-sc0rj/
     *
     */
    int[] parent;
    public int minScore(int n, int[][] roads) {
        int min=Integer.MAX_VALUE;
        List<int[]>[] e=new List[n+1];
        for(int i=1;i<=n;i++){
            e[i]=new ArrayList<>();
        }
        for(int[] r:roads){
            e[r[0]].add(new int[]{r[1],r[2]});
            e[r[1]].add(new int[]{r[0],r[2]});
        }
        parent=new int[n+1];
        for(int i=1;i<=n;i++){
            parent[i]=i;
        }
        for(int[] r:roads){
            union(r[0],r[1]);
        }
        for(int i=1;i<=n;i++){
            if(find(i)==1&&e[i].size()>0){
                e[i].sort((a,b)->a[1]-b[1]);
                min=Math.min(min,e[i].get(0)[1]);
            }
        }
        return min;
    }
    public int find(int x){
        while(x!=parent[x]){
            x=parent[x];
            parent[x]=parent[parent[x]];
        }
        return x;
    }
    public void union(int x,int y){
        int rootX=find(x),rootY=find(y);
        if(rootX<=rootY){
            parent[rootY]=rootX;
        }else{
            parent[rootX]=rootY;
        }
    }

}
