package com.example.leetcode.challenge.test2022.february;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Given an array nums of size n, return the majority element.
 *
 * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,2,3]
 * Output: 3
 * Example 2:
 *
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 *
 */
public class MajorityElement {
    public static void main(String[] args) {

    }

    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int value : nums){
            int times = map.getOrDefault(value,0) + 1;
            if(times > nums.length / 2)
                return value;
            else
                map.put(value,times);
        }
        return -1;
    }

    /**
     * faster solution
     * @param nums
     * @return
     */
    public int majorityElementV2(int[] nums) {
        int count = 0;
        int cand = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                cand = nums[i];
            }
            count += (nums[i] == cand) ? 1 : -1;
        }
        return cand;
    }
}
