package com.example.leetcode.easy;

/**
 * Given a binary array, find the maximum number of consecutive 1s in this array.
 *
 * Example 1:
 * Input: [1,1,0,1,1,1]
 * Output: 3
 * Explanation: The first two digits or the last three digits are consecutive 1s.
 *     The maximum number of consecutive 1s is 3.
 * Note:
 *
 * The input array will only contain 0 and 1.
 * The length of input array is a positive integer and will not exceed 10,000
 */
public class MaxConsecutiveOnes {
    public static void main(String[] args) {
        int[] nums = new int[]{1,1,0,1,1,1};
        MaxConsecutiveOnes maxConsecutiveOnes = new MaxConsecutiveOnes();
        maxConsecutiveOnes.findMaxConsecutiveOnes(nums);
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int count = 0;
        int max = Integer.MIN_VALUE;
        for (int value: nums) {
            if(value == 0){
                if(count > max)
                    max = count;
                count = 0;
            } else{
                count++;
            }
        }
        if(count > max)
            max = count;
        return max;
    }
}
