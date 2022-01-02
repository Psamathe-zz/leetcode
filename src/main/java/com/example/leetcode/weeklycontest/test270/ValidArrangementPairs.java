package com.example.leetcode.weeklycontest.test270;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are given a 0-indexed 2D integer array pairs where pairs[i] = [starti, endi]. An arrangement of pairs is valid if for every index i where 1 <= i < pairs.length, we have endi-1 == starti.
 *
 * Return any valid arrangement of pairs.
 *
 * Note: The inputs will be generated such that there exists a valid arrangement of pairs.
 *
 *
 *
 * Example 1:
 *
 * Input: pairs = [[5,1],[4,5],[11,9],[9,4]]
 * Output: [[11,9],[9,4],[4,5],[5,1]]
 * Explanation:
 * This is a valid arrangement since endi-1 always equals starti.
 * end0 = 9 == 9 = start1
 * end1 = 4 == 4 = start2
 * end2 = 5 == 5 = start3
 * Example 2:
 *
 * Input: pairs = [[1,3],[3,2],[2,1]]
 * Output: [[1,3],[3,2],[2,1]]
 * Explanation:
 * This is a valid arrangement since endi-1 always equals starti.
 * end0 = 3 == 3 = start1
 * end1 = 2 == 2 = start2
 * The arrangements [[2,1],[1,3],[3,2]] and [[3,2],[2,1],[1,3]] are also valid.
 * Example 3:
 *
 * Input: pairs = [[1,2],[1,3],[2,1]]
 * Output: [[1,2],[2,1],[1,3]]
 * Explanation:
 * This is a valid arrangement since endi-1 always equals starti.
 * end0 = 2 == 2 = start1
 * end1 = 1 == 1 = start2
 */
public class ValidArrangementPairs {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/valid-arrangement-of-pairs/solution/java-by-ctysss-63qf/
     */
    Map<Integer, List<Integer>> g;
    List<Integer> ret;
    public int[][] validArrangement(int[][] pairs) {
        int len = pairs.length;
        g = new HashMap<>();
        Map<Integer,Integer> in = new HashMap<>();
        Map<Integer,Integer> out = new HashMap<>();
        for(int[] p : pairs){
            g.putIfAbsent(p[0] , new ArrayList<>());
            g.get(p[0]).add(p[1]);
            in.put(p[1] , in.getOrDefault(p[1], 0) + 1);
            out.put(p[0] , out.getOrDefault(p[0] , 0) + 1);
        }
        int start = -1;
        for(int key : out.keySet()){
            if(out.get(key) - in.getOrDefault(key , 0) == 1){
                start = key;
                break;
            }
        }
        if(start == -1){
            start = pairs[0][0];
        }
        ret = new ArrayList<>();

        dfs(start);

        int[][] ans = new int[len][2];
        for(int i = len ; i > 0 ; i--){
            ans[len-i][0] = ret.get(i);
            ans[len-i][1] = ret.get(i-1);
        }
        return ans;
    }
    void dfs(int start){
        List<Integer> list = g.getOrDefault(start , new ArrayList<>());
        while(list.size() > 0){
            int x = list.remove(list.size()-1);
            dfs(x);
        }
        ret.add(start);
    }

}
