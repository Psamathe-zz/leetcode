package com.example.leetcode.easy;

import java.util.Arrays;

/**
 * Given an array of 2n integers, your task is to group these integers into n pairs of integer,
 * say (a1, b1), (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.
 *
 * Example 1:
 * Input: [1,4,3,2]
 *
 * Output: 4
 * Explanation: n is 2, and the maximum sum of pairs is 4 = min(1, 2) + min(3, 4).
 */
public class ArrayPartitionI {
    public static void main(String[] args) {
        int[] nums = new int[]{1,4,3,2};
        ArrayPartitionI arrayPartitionI = new ArrayPartitionI();
        int result = arrayPartitionI.arrayPairSum(nums);
        System.out.println(result);
    }

    public int arrayPairSum(int[] nums) {
        int result = 0;
        Arrays.sort(nums);
        for(int i =0; i < nums.length;){
            result += nums[i];
            i += 2;
        }
        return result;
    }


    /**
     * faster solution
     * @param nums
     * @return
     */
    public int arrayPairSumV2(int[] nums) {
        int[] counts = new int[20001];
        for (int num : nums) {
            counts[num + 10000]++;
        }
        int sum = 0;
        boolean flag = true;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0) {
                while (counts[i]-- > 0) {
                    if (flag) {
                        sum += i - 10000;
                        flag = false;
                    } else {
                        flag = true;
                    }
                }
            }
        }
        return sum;
    }
}
