package com.example.leetcode.biweeklycontest.old.contest64;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * There is a long table with a line of plates and candles arranged on top of it. You are given a 0-indexed string s consisting of characters '*' and '|' only, where a '*' represents a plate and a '|' represents a candle.
 *
 * You are also given a 0-indexed 2D integer array queries where queries[i] = [lefti, righti] denotes the substring s[lefti...righti] (inclusive). For each query, you need to find the number of plates between candles that are in the substring. A plate is considered between candles if there is at least one candle to its left and at least one candle to its right in the substring.
 *
 * For example, s = "||**||**|*", and a query [3, 8] denotes the substring "*||**|". The number of plates between candles in this substring is 2, as each of the two plates has at least one candle in the substring to its left and right.
 * Return an integer array answer where answer[i] is the answer to the ith query.
 *
 *
 *
 * Example 1:
 *
 * ex-1
 * Input: s = "**|**|***|", queries = [[2,5],[5,9]]
 * Output: [2,3]
 * Explanation:
 * - queries[0] has two plates between candles.
 * - queries[1] has three plates between candles.
 * Example 2:
 *
 * ex-2
 * Input: s = "***|**|*****|**||**|*", queries = [[1,17],[4,5],[14,17],[5,11],[15,16]]
 * Output: [9,0,0,0,0]
 * Explanation:
 * - queries[0] has nine plates between candles.
 * - The other queries have zero plates between candles.
 */
public class PlatesBetweenCandles {
    public static void main(String[] args) {
        PlatesBetweenCandles platesBetweenCandles = new PlatesBetweenCandles();
        platesBetweenCandles.platesBetweenCandles("**|**|***|", new int[][]{
                {2,5},
                {5,9},
        });
    }

    public int[] platesBetweenCandles(String s, int[][] queries) {
        TreeMap<Integer , Integer> map = new TreeMap<>();
        int index = 0;
        int n = s.length();
        for(int i = 0 ; i < n ; i++){
            char c = s.charAt(i);
            if(c == '|')
                map.put(i , index++);
        }
        index = 0;
        n = queries.length;
        int[] res = new int[n];
        while(index < n){
            int[] nums = queries[index];
            int l = nums[0] , r = nums[1];
            Integer preAfter = map.ceilingKey(l) , afterPre = map.floorKey(r);
            int num = 0;
            if(afterPre != null && afterPre >= l){
                num = afterPre - preAfter + 1 - (map.get(afterPre) - map.get(preAfter) + 1);
            }
            res[index++] = num;
        }
        return res;
    }
}
