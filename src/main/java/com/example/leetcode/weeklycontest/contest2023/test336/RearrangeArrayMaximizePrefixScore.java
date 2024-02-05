package com.example.leetcode.weeklycontest.contest2023.test336;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * You are given a 0-indexed integer array nums. You can rearrange the elements of nums to any order (including the given order).
 *
 * Let prefix be the array containing the prefix sums of nums after rearranging it. In other words, prefix[i] is the sum of the elements from 0 to i in nums after rearranging it. The score of nums is the number of positive integers in the array prefix.
 *
 * Return the maximum score you can achieve.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,-1,0,1,-3,3,-3]
 * Output: 6
 * Explanation: We can rearrange the array into nums = [2,3,1,-1,-3,0,-3].
 * prefix = [2,5,6,5,2,2,-1], so the score is 6.
 * It can be shown that 6 is the maximum score we can obtain.
 * Example 2:
 *
 * Input: nums = [-2,-3,0]
 * Output: 0
 * Explanation: Any rearrangement of the array will result in a score of 0.
 */
public class RearrangeArrayMaximizePrefixScore {
    public static void main(String[] args) {

    }

    public int maxScore(int[] nums) {
        List<Integer> integers = Arrays.stream(nums).boxed().sorted(Comparator.reverseOrder()).toList();
        long sum = 0;
        int res = 0;
        for(Integer val : integers) {
            sum += val;
            if(sum <= 0)
                break;
            res++;
        }
        return res;

    }
}
