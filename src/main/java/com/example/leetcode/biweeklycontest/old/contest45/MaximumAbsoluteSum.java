package com.example.leetcode.biweeklycontest.old.contest45;


/**
 * You are given an integer array nums. The absolute sum of a subarray [numsl, numsl+1, ..., numsr-1, numsr] is abs(numsl + numsl+1 + ... + numsr-1 + numsr).
 *
 * Return the maximum absolute sum of any (possibly empty) subarray of nums.
 *
 * Note that abs(x) is defined as follows:
 *
 * If x is a negative integer, then abs(x) = -x.
 * If x is a non-negative integer, then abs(x) = x.
 *
 *
 * Example 1:
 *
 * Input: nums = [1,-3,2,3,-4]
 * Output: 5
 * Explanation: The subarray [2,3] has absolute sum = abs(2+3) = abs(5) = 5.
 * Example 2:
 *
 * Input: nums = [2,-5,1,-4,3,-2]
 * Output: 8
 * Explanation: The subarray [-5,1,-4] has absolute sum = abs(-5+1-4) = abs(-8) = 8.
 */
public class MaximumAbsoluteSum {
    public static void main(String[] args) {
        int[] nums = new int[]{2,-5,1,-4,3,-2};
        MaximumAbsoluteSum maximumAbsoluteSum = new MaximumAbsoluteSum();
        int res = maximumAbsoluteSum.maxAbsoluteSum(nums);
        System.out.println(res);
    }

    public int maxAbsoluteSum(int[] nums) {
        int length = nums.length;
        int maxP = 0;
        int minN = 0;

        int sumP = 0;
        int sumN = 0;
        for (int i = 0; i < length; i++) {
            if(nums[i] > 0) {
                sumP += nums[i];
                maxP = Math.max(maxP,sumP);
                if(-sumN < nums[i])
                    sumN = 0;
                else
                    sumN += nums[i];
            }
            if(nums[i] < 0){
                sumN += nums[i];
                minN = Math.min(minN, sumN);
                if(-nums[i] > sumP)
                    sumP = 0;
                else
                    sumP += nums[i];
            }
        }

        return Math.max(-minN, maxP);
    }
}
