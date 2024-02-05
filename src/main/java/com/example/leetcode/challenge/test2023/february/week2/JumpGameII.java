package com.example.leetcode.challenge.test2023.february.week2;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].
 *
 * Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at nums[i], you can jump to any nums[i + j] where:
 *
 * 0 <= j <= nums[i] and
 * i + j < n
 * Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 */
public class JumpGameII {
    public static void main(String[] args) {

    }

    Map<Integer, Integer> map;
    public int jump(int[] nums) {
        if(nums.length == 1)
            return 0;
        map = new HashMap<>();
        return helper(nums,0);
    }

    public int helper(int[] nums,int cur){
        if(nums[cur] + cur  >= nums.length - 1){
            return 1;
        }
        if(map.containsKey(cur))
            return map.get(cur);
        int min = 2000;
        for (int i = 1; i <= nums[cur]; i++) {
            if(cur + i >= nums.length)
                break;
            if(nums[cur + i] == 0)
                continue;
            min = Math.min(min, helper(nums, cur + i));
        }
        map.put(cur, min + 1);
        return min + 1;
    }
}
