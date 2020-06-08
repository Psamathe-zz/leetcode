package com.example.leetcode.easy;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 *
 * Example 1:
 *
 * Input: [3,0,1]
 * Output: 2
 * Example 2:
 *
 * Input: [9,6,4,2,3,5,7,0,1]
 * Output: 8
 * Note:
 * Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
 */
public class MissingNumber {
    public static void main(String[] args) {
        int[] nums = new int[]{9,6,4,2,3,5,7,0,1};
        MissingNumber missingNumber = new MissingNumber();
        int result = missingNumber.missingNumber(nums);
        System.out.println(result);
    }
    public int missingNumber(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        int length = nums.length;
        return length*(length+1)/2 - sum;
    }

}
