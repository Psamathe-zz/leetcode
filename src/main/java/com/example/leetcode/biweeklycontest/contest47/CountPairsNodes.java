package com.example.leetcode.biweeklycontest.contest47;


import java.util.*;

/**
 * You are given an undirected graph represented by an integer n, which is the number of nodes, and edges, where edges[i] = [ui, vi] which indicates that there is an undirected edge between ui and vi. You are also given an integer array queries.
 *
 * The answer to the jth query is the number of pairs of nodes (a, b) that satisfy the following conditions:
 *
 * a < b
 * cnt is strictly greater than queries[j], where cnt is the number of edges incident to a or b.
 * Return an array answers such that answers.length == queries.length and answers[j] is the answer of the jth query.
 *
 * Note that there can be repeated edges.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 4, edges = [[1,2],[2,4],[1,3],[2,3],[2,1]], queries = [2,3]
 * Output: [6,5]
 * Explanation: The number of edges incident to at least one of each pair is shown above.
 * Example 2:
 *
 * Input: n = 5, edges = [[1,5],[1,5],[3,4],[2,5],[1,3],[5,1],[2,3],[2,5]], queries = [1,2,3,4,5]
 * Output: [10,10,9,8,6]
 */
public class CountPairsNodes {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/count-pairs-of-nodes/solution/du-bian-li-bian-by-simon123-t-5qk7/
     */
    int P = 100000;
    public int[] countPairs(int n, int[][] edges, int[] queries) {
        int[] io = new int[n+1];
        int[] arr = new int[n+1];
        int m = queries.length;
        int[] ans = new int[m];
        Map<Integer,Integer> map = new HashMap<>();
        for(int[] e:edges){
            int u = Math.min(e[0],e[1]);
            int v = Math.max(e[0],e[1]);
            io[u]++;
            io[v]++;
            int tmp = u*P + v;
            map.put(tmp,map.getOrDefault(tmp,0)+1);
        }
        for(int i=1;i<=n;i++){
            arr[i] = io[i];
        }
        Arrays.sort(arr);
        for(int i=0;i<m;i++){
            int l=1,r=n;
            while(l<r){
                while(l<r && arr[l]+arr[r]<=queries[i])l++;
                if(l<r){
                    ans[i] += r-l;
                }
                r--;
            }
            if(ans[i] == 0)continue;
            Set<Integer> visit = new HashSet<>();
            for(int[] e:edges){
                int u = Math.min(e[0],e[1]);
                int v = Math.max(e[0],e[1]);
                int ttt = u*P+v;
                if(visit.contains(ttt))continue;
                visit.add(ttt);
                int tmp = io[u]+io[v]-map.get(ttt);
                if(io[u]+io[v]>queries[i] && tmp<=queries[i]){
                    ans[i]--;
                }
            }
        }
        return ans;
    }


}
