package com.example.leetcode.challenge.test2024.mars.week3;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [0,1]
 * Output: 2
 * Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.
 * Example 2:
 *
 * Input: nums = [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 */
public class ContiguousArray {
    public static void main(String[] args) {

    }

    public int findMaxLength(int[] nums) {
        int result = 0;
        Map<Integer,Integer> map = new HashMap<>();
        int sum = 0;
        map.put(0,-1);
        for(int i = 0; i < nums.length;i++){
            if(nums[i] == 0)
                sum -= 1;
            else
                sum += 1;
            if(map.containsKey(sum)){
                result = Math.max(result,i-map.get(sum));
            } else
                map.put(sum,i);
        }
        return result;
    }
}
