package com.example.leetcode.challenge.test2021.june.week1;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 *
 * You must write an algorithm that runs in O(n) time.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 * Example 2:
 *
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 */
public class LongestConsecutiveSequence {
    public static void main(String[] args) {

    }


    public int longestConsecutive(int[] nums) {
        if(nums.length == 0)
            return 0;
        Arrays.sort(nums);
        int res = 0;
        int pre = nums[0];
        int length = nums.length;
        int temp = 1;
        for (int i = 0; i < length; i++) {
            if(nums[i] == pre + 1){
                temp++;
                pre = nums[i];
            } else if(nums[i] > pre + 1) {
                res = Math.max(res, temp);
                temp = 1;
                pre = nums[i];
            }
        }
        res = Math.max(res, temp);
        return res;
    }
}
