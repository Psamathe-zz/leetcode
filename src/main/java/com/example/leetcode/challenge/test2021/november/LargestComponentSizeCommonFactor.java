package com.example.leetcode.challenge.test2021.november;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given an integer array of unique positive integers nums. Consider the following graph:
 *
 * There are nums.length nodes, labeled nums[0] to nums[nums.length - 1],
 * There is an undirected edge between nums[i] and nums[j] if nums[i] and nums[j] share a common factor greater than 1.
 * Return the size of the largest connected component in the graph.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: nums = [4,6,15,35]
 * Output: 4
 * Example 2:
 *
 *
 * Input: nums = [20,50,9,63]
 * Output: 2
 * Example 3:
 *
 *
 * Input: nums = [2,3,6,7,4,12,21,39]
 * Output: 8
 */
public class LargestComponentSizeCommonFactor {
    public static void main(String[] args) {

    }

    int[] root;
    public int largestComponentSize(int[] A) {
        int n = 0, mx = 0, res = 0;
        Map<Integer,Integer> map = new HashMap<>();
        for (int num : A) mx = Math.max(mx, num);
        root = new int[mx + 1];
        for (int i = 1; i <= mx; ++i) root[i] = i;
        for (int num : A) {
            for (int d = (int)Math.sqrt(num); d >= 2; --d) {
                if (num % d == 0) {
                    root[find(num)] = root[find(d)];
                    root[find(num)] = root[find(num / d)];
                }
            }
        }
        for (int num : A) {
            int index = find(num);
            map.put(index,map.getOrDefault(index,0)+1);
            res = Math.max(res,map.get(index) );
        }
        return res;
    }

    int find( int x) {
        return root[x] == x ? x : (root[x] = find(root[x]));
    }
}
