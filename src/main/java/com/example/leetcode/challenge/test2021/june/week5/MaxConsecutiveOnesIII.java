package com.example.leetcode.challenge.test2021.june.week5;


/**
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
 * Output: 6
 * Explanation: [1,1,1,0,0,1,1,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 * Example 2:
 *
 * Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
 * Output: 10
 * Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 *
 *
 * Constraints:
 */
public class MaxConsecutiveOnesIII {
    public static void main(String[] args) {
        MaxConsecutiveOnesIII maxConsecutiveOnesIII = new MaxConsecutiveOnesIII();
        int res = maxConsecutiveOnesIII.longestOnes(new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1}, 3);
        System.out.println(res);
    }


    public int longestOnes(int[] nums, int k) {
        int length = nums.length;
        int res = 0;
        int left = 0;
        int right = 0;
        int count0 = 0;
        while (right < length){
            if(nums[right] == 0) {
                count0++;
                if(count0 > k){
                    res = Math.max(res,right - left);
                    while (nums[left] == 1){
                        left++;
                    }
                    left++;
                    count0--;
                }
            }
            right++;
        }
        if(count0 > k) {
            res = Math.max(res, right - 1 - left);
        } else
            res = Math.max(res, right - left);
        return res;
    }


    /**
     * easier solution
     * @param nums
     * @param k
     * @return
     */
    public int longestOnesV1(int[] nums, int k) {
        int start = 0;
        int end;

        for (end = 0 ; end < nums.length ; end++) {

            if (nums[end] == 0) {
                k--;
            }

            if (k<0) {

                if (nums[start] == 0) {
                    k++;
                }
                start++;
            }
        }

        return end - start;
    }
}
