package com.example.leetcode.medium;

import java.util.Arrays;

/**
 * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice.
 * Find the two elements that appear only once.
 *
 * Example:
 *
 * Input:  [1,2,1,3,2,5]
 * Output: [3,5]
 * Note:
 *
 * The order of the result is not important. So in the above example, [5, 3] is also correct.
 * Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
 */
public class SingleNumberIII {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,7,1,3,2,5,7};
        SingleNumberIII singleNumberIII = new SingleNumberIII();
        singleNumberIII.singleNumber(nums);
    }
    public int[] singleNumber(int[] nums) {
        if(nums.length <= 2)
            return nums;
        int[] result  = new int[2];
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum ^= nums[i];
        }
        /**
         * 3, 5 最高一位不同是4
         */
        sum = Integer.highestOneBit(sum);
        for (int i = 0; i < nums.length; i++) {
            int value = sum & nums[i];
            if((value) == 0){
               result[0] ^= nums[i];
            } else {
                result[1] ^= nums[i];
            }
        }

        return result;
    }


    /**
     * faster solution
     * @param nums
     * @return
     */
    public int[] singleNumberV1(int[] nums) {
        int bitmask=0;
        for(int num:nums){
            bitmask=bitmask^num;
        }

        int diff=bitmask & (-bitmask);
        int x=0;
        for(int num:nums){
            if((num & diff)>0){
                x=x^num;
            }
        }

        return new int[]{x,bitmask^x};

    }

}
