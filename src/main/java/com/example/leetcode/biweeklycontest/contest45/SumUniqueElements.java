package com.example.leetcode.biweeklycontest.contest45;


import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * You are given an integer array nums. The unique elements of an array are the elements that appear exactly once in the array.
 *
 * Return the sum of all the unique elements of nums.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,2]
 * Output: 4
 * Explanation: The unique elements are [1,3], and the sum is 4.
 * Example 2:
 *
 * Input: nums = [1,1,1,1,1]
 * Output: 0
 * Explanation: There are no unique elements, and the sum is 0.
 * Example 3:
 *
 * Input: nums = [1,2,3,4,5]
 * Output: 15
 * Explanation: The unique elements are [1,2,3,4,5], and the sum is 15.
 *
 */
public class SumUniqueElements {
    public static void main(String[] args) {

    }

    public int sumOfUnique(int[] nums) {
        Set<Integer> setUnique = new HashSet<>();
        Set<Integer> setDup = new HashSet<>();

        for (int val : nums){
            if(!setUnique.contains(val)){
                setUnique.add(val);
            } else {
                setDup.add(val);
            }
        }

        return setUnique.stream().mapToInt(e -> e).sum() - setDup.stream().mapToInt(e -> e).sum() ;
    }
}
