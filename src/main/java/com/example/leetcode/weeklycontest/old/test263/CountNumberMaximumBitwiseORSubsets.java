package com.example.leetcode.weeklycontest.old.test263;

import java.util.*;

/**
 * Given an integer array nums, find the maximum possible bitwise OR of a subset of nums and return the number of different non-empty subsets with the maximum bitwise OR.
 *
 * An array a is a subset of an array b if a can be obtained from b by deleting some (possibly zero) elements of b. Two subsets are considered different if the indices of the elements chosen are different.
 *
 * The bitwise OR of an array a is equal to a[0] OR a[1] OR ... OR a[a.length - 1] (0-indexed).
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,1]
 * Output: 2
 * Explanation: The maximum possible bitwise OR of a subset is 3. There are 2 subsets with a bitwise OR of 3:
 * - [3]
 * - [3,1]
 * Example 2:
 *
 * Input: nums = [2,2,2]
 * Output: 7
 * Explanation: All non-empty subsets of [2,2,2] have a bitwise OR of 2. There are 23 - 1 = 7 total subsets.
 * Example 3:
 *
 * Input: nums = [3,2,1,5]
 * Output: 6
 * Explanation: The maximum possible bitwise OR of a subset is 7. There are 6 subsets with a bitwise OR of 7:
 * - [3,5]
 * - [3,1,5]
 * - [3,2,5]
 * - [3,2,1,5]
 * - [2,5]
 * - [2,1,5]
 */
public class CountNumberMaximumBitwiseORSubsets {
    public static void main(String[] args) {
        CountNumberMaximumBitwiseORSubsets countNumberMaximumBitwiseORSubsets = new CountNumberMaximumBitwiseORSubsets();
        countNumberMaximumBitwiseORSubsets.countMaxOrSubsets(new int[]{3,2,1,5});
    }

    public int countMaxOrSubsets(int[] nums) {
        int n = nums.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int mask = 0; mask < 1 << n; mask++) {
            int t = 0;
            for (int i = 0; i < n; i++) {
                if (((mask >> i) & 1) == 1) {
                    t |= nums[i];
                }
            }
            map.put(t, map.getOrDefault(t, 0) + 1);
        }
        return map.lastEntry().getValue();
    }

    public int countMaxOrSubsetsV1(int[] nums) {
        int length = nums.length;
        int sum = 1;
        int cur;
        int index;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            cur = nums[i];
            sum |= cur;
            index = 1;
            while (cur > 0){
                if((cur & 1) == 1){
                    int finalI = i;
                    map.compute(index, (k, v) -> {
                       if(v == null){
                           v = new ArrayList<>();
                       }
                       v.add(finalI);
                       return v;
                    });
                }
                cur = cur >> 1;
                index++;
            }
        }

        int res = 0;
        return res;
    }
}
