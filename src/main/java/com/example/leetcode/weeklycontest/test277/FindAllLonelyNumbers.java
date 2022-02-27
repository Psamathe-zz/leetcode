package com.example.leetcode.weeklycontest.test277;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * You are given an integer array nums. A number x is lonely when it appears only once, and no adjacent numbers (i.e. x + 1 and x - 1) appear in the array.
 *
 * Return all lonely numbers in nums. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [10,6,5,8]
 * Output: [10,8]
 * Explanation:
 * - 10 is a lonely number since it appears exactly once and 9 and 11 does not appear in nums.
 * - 8 is a lonely number since it appears exactly once and 7 and 9 does not appear in nums.
 * - 5 is not a lonely number since 6 appears in nums and vice versa.
 * Hence, the lonely numbers in nums are [10, 8].
 * Note that [8, 10] may also be returned.
 * Example 2:
 *
 * Input: nums = [1,3,5,3]
 * Output: [1,5]
 * Explanation:
 * - 1 is a lonely number since it appears exactly once and 0 and 2 does not appear in nums.
 * - 5 is a lonely number since it appears exactly once and 4 and 6 does not appear in nums.
 * - 3 is not a lonely number since it appears twice.
 * Hence, the lonely numbers in nums are [1, 5].
 * Note that [5, 1] may also be returned.
 *
 */
public class FindAllLonelyNumbers {
    public static void main(String[] args) {

    }

    public List<Integer> findLonely(int[] nums) {
        List<Integer> res = new ArrayList<>();
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int val : nums) {
            map.compute(val, (k,v) -> {
                if(v == null)
                    return 1;
                else
                    return v + 1;
            });
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() > 1 || map.containsKey(entry.getKey() - 1) || map.containsKey(entry.getKey() + 1)) {
                continue;
            }
            res.add(entry.getKey());
        }

        return res;
    }
}
