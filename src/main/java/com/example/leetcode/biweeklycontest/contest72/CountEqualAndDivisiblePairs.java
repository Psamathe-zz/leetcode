package com.example.leetcode.biweeklycontest.contest72;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a 0-indexed integer array nums of length n and an integer k, return the number of pairs (i, j) where 0 <= i < j < n, such that nums[i] == nums[j] and (i * j) is divisible by k.
 *
 *
 * Example 1:
 *
 * Input: nums = [3,1,2,2,2,1,3], k = 2
 * Output: 4
 * Explanation:
 * There are 4 pairs that meet all the requirements:
 * - nums[0] == nums[6], and 0 * 6 == 0, which is divisible by 2.
 * - nums[2] == nums[3], and 2 * 3 == 6, which is divisible by 2.
 * - nums[2] == nums[4], and 2 * 4 == 8, which is divisible by 2.
 * - nums[3] == nums[4], and 3 * 4 == 12, which is divisible by 2.
 * Example 2:
 *
 * Input: nums = [1,2,3,4], k = 1
 * Output: 0
 * Explanation: Since no value in nums is repeated, there are no pairs (i,j) that meet all the requirements.
 */
public class CountEqualAndDivisiblePairs {
    public static void main(String[] args) {

    }

    public int countPairs(int[] nums, int k) {
        int res = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int finalI = i;
            map.compute(nums[i], (key, v) -> {
               if(v == null) {
                   List<Integer> list = new ArrayList<>();
                   list.add(finalI);
                   return list;
               } else {
                   v.add(finalI);
                   return v;
               }
            });
        }
        for (List<Integer> list : map.values()) {
            int size = list.size();
            for (int i = 0; i < size - 1; i++) {
                for (int j = i + 1; j < size; j++) {
                    if(list.get(i) * list.get(j) % k == 0)
                        res++;
                }
            }
        }

        return res;
    }
}
