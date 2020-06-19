package com.example.leetcode.easy;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Given an integer array, find three numbers whose product is maximum and output the maximum product.
 *
 * Example 1:
 *
 * Input: [1,2,3]
 * Output: 6
 *
 *
 * Example 2:
 *
 * Input: [1,2,3,4]
 * Output: 24
 *
 *
 * Note:
 *
 * The length of the given array will be in range [3,104] and all elements are in the range [-1000, 1000].
 * Multiplication of any three numbers in the input won't exceed the range of 32-bit signed integer.
 */
public class MaximumProductOfThreeNumbers {
    public static void main(String[] args) {

    }

    public int maximumProductVOld(int[] nums) {
        int result = Integer.MIN_VALUE;
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                for (int k = j + 1; k < length; k++) {
                    if(result < nums[i] * nums[j] * nums[k])
                        result = nums[i] * nums[j] * nums[k];
                }
            }
        }
        return result;
    }

    public int maximumProduct(int[] nums) {
        int length = nums.length;
        Arrays.sort(nums);
        int option1 = nums[length - 1] * nums[length - 2] * nums[length - 3];
        int option2 = nums[length - 1] * nums[0] * nums[1];

        return option1 > option2 ? option1:option2;
    }
}
