package com.example.leetcode.challenge.test2021.november;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes of unique values from 1 to n.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3
 * Output: 5
 * Example 2:
 *
 * Input: n = 1
 * Output: 1
 */
public class UniqueBinarySearchTrees {
    public static void main(String[] args) {
        UniqueBinarySearchTrees uniqueBinarySearchTrees = new UniqueBinarySearchTrees();
        int res = uniqueBinarySearchTrees.numTrees(1);
        System.out.println(res);
    }

    Map<Integer, Integer> map = new HashMap<>();
    public int numTrees(int n) {
        if(n == 0)
            return 1;
        if(map.containsKey(n))
            return map.get(n);
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += numTrees(i) * numTrees(n - i - 1);
        }

        map.put(n, res);
        return res;
    }
}
