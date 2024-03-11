package com.example.leetcode.challenge.test2024.mars.week3;

import java.util.HashSet;
import java.util.Set;

/**
 * Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must be unique and you may return the result in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2]
 * Example 2:
 *
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [9,4]
 * Explanation: [4,9] is also accepted.
 *
 */
public class IntersectionOfTwoArrays {
    public static void main(String[] args) {

    }

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for(int value : nums1){
            set.add(value);
        }
        for(int value : nums2){
            if(set.contains(value)){
                set2.add(value);
            }
        }
        return set2.stream().mapToInt(e -> e).toArray();
    }
}
