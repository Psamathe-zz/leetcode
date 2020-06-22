package com.example.leetcode.challenge.June;

import java.util.Arrays;

/**
 * Given a non-empty array of integers, every element appears three times except for one, which appears exactly once. Find that single one.
 *
 * Note:
 *
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 *
 * Example 1:
 *
 * Input: [2,2,3,2]
 * Output: 3
 * Example 2:
 *
 * Input: [0,1,0,1,0,1,99]
 * Output: 99
 */
public class SingleNumberII {
    public static void main(String[] args) {
        int[] nums = new int[]{2,2,3,2};
        SingleNumberII singleNumberII = new SingleNumberII();
        int result = singleNumberII.singleNumber(nums);
        System.out.println(result);
    }

    public int singleNumber(int[] nums) {
        int one = 0, two = 0, three = 0;
        for (int i = 0; i < nums.length; ++i) {
            two |= one & nums[i];
            one ^= nums[i];
            three = one & two;
            one &= ~three;
            two &= ~three;
        }
        return one;
    }

    public int singleNumberV1(int[] nums) {

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i = i + 3) {

            if (i < nums.length - 2 && nums[i] == nums[i + 1] && nums[i] == nums[i + 2]) {

                continue;
            } else {
                return nums[i];
            }

        }

        return nums[nums.length - 1];
    }
}
