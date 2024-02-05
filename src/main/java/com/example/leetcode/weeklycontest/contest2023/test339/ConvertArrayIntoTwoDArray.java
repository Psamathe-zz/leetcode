package com.example.leetcode.weeklycontest.contest2023.test339;

import java.util.*;

/**
 * You are given an integer array nums. You need to create a 2D array from nums satisfying the following conditions:
 *
 * The 2D array should contain only the elements of the array nums.
 * Each row in the 2D array contains distinct integers.
 * The number of rows in the 2D array should be minimal.
 * Return the resulting array. If there are multiple answers, return any of them.
 *
 * Note that the 2D array can have a different number of elements on each row.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,4,1,2,3,1]
 * Output: [[1,3,4,2],[1,3],[1]]
 * Explanation: We can create a 2D array that contains the following rows:
 * - 1,3,4,2
 * - 1,3
 * - 1
 * All elements of nums were used, and each row of the 2D array contains distinct integers, so it is a valid answer.
 * It can be shown that we cannot have less than 3 rows in a valid array.
 * Example 2:
 *
 * Input: nums = [1,2,3,4]
 * Output: [[4,3,2,1]]
 * Explanation: All elements of the array are distinct, so we can keep all of them in the first row of the 2D array.
 */
public class ConvertArrayIntoTwoDArray {
    public static void main(String[] args) {

    }

    public List<List<Integer>> findMatrix(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        TreeMap<Integer, Integer> count = new TreeMap<>();
        for(int val : nums) {
            count.compute(val, (k,v) -> {
                if(v == null)
                    return 1;
                else
                    return v + 1;
            });
        }
        for(Map.Entry<Integer, Integer> entry: count.entrySet()) {
            int diff = entry.getValue() - res.size();
            if(diff > 0) {
                for (int i = 0; i < diff; i++) {
                    List<Integer> list = new ArrayList<>();
                    res.add(list);
                }
            }
            for (int i = 0; i < entry.getValue(); i++) {
                res.get(i).add(entry.getKey());
            }
        }
        return res;
    }
}
