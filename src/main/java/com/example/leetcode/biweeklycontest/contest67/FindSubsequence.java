package com.example.leetcode.biweeklycontest.contest67;

import java.util.*;
import java.util.stream.Collectors;

/**
 * You are given an integer array nums and an integer k. You want to find a subsequence of nums of length k that has the largest sum.
 *
 * Return any such subsequence as an integer array of length k.
 *
 * A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,1,3,3], k = 2
 * Output: [3,3]
 * Explanation:
 * The subsequence has the largest sum of 3 + 3 = 6.
 * Example 2:
 *
 * Input: nums = [-1,-2,3,4], k = 3
 * Output: [-1,3,4]
 * Explanation:
 * The subsequence has the largest sum of -1 + 3 + 4 = 6.
 * Example 3:
 *
 * Input: nums = [3,4,3,3], k = 2
 * Output: [3,4]
 * Explanation:
 * The subsequence has the largest sum of 3 + 4 = 7.
 * Another possible subsequence is [4, 3].
 */
public class FindSubsequence {
    public static void main(String[] args) {

    }

    public int[] maxSubsequence(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(i, nums[i]);
        }
        return  map.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).limit(k).sorted(Comparator.comparingInt(Map.Entry::getKey)).map(Map.Entry::getValue).mapToInt(e -> e).toArray();

    }
}
