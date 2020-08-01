package com.example.leetcode.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Given an array of integers arr, write a function that returns true if and only if the number of occurrences of each value in the array is unique.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2,2,1,1,3]
 * Output: true
 * Explanation: The value 1 has 3 occurrences, 2 has 2 and 3 has 1. No two values have the same number of occurrences.
 * Example 2:
 *
 * Input: arr = [1,2]
 * Output: false
 * Example 3:
 *
 * Input: arr = [-3,0,1,-3,1,1,1,-3,10,0]
 * Output: true
 */
public class UniqueNumberOccurrences {
    public static void main(String[] args) {

    }

    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int val : arr){
            map.put(val,map.getOrDefault(val,0) + 1);
        }

        return  map.values().stream().collect(Collectors.toSet()).size() == map.entrySet().size();
    }
}
