package com.example.leetcode.challenge.test2023.mars.week1;

/**
 * You are given an integer array nums and two integers minK and maxK.
 *
 * A fixed-bound subarray of nums is a subarray that satisfies the following conditions:
 *
 * The minimum value in the subarray is equal to minK.
 * The maximum value in the subarray is equal to maxK.
 * Return the number of fixed-bound subarrays.
 *
 * A subarray is a contiguous part of an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,5,2,7,5], minK = 1, maxK = 5
 * Output: 2
 * Explanation: The fixed-bound subarrays are [1,3,5] and [1,3,5,2].
 * Example 2:
 *
 * Input: nums = [1,1,1,1], minK = 1, maxK = 1
 * Output: 10
 * Explanation: Every subarray of nums is a fixed-bound subarray. There are 10 possible subarrays.
 */
public class CountSubarraysWithFixedBounds {
    public static void main(String[] args) {

    }

    /**
     * https://www.cnblogs.com/Dylan-Java-NYC/p/16796157.html
     * @param nums
     * @param minK
     * @param maxK
     * @return
     */
    public long countSubarrays(int[] nums, int minK, int maxK) {
        int n = nums.length;
        int startInd = 0;
        int lastMinInd = -1;
        int lastMaxInd = -1;
        long res = 0;
        for(int i = 0; i < n; i++){
            if(nums[i] < minK || nums[i] > maxK){
                lastMinInd = lastMaxInd = -1;
                startInd = i + 1;
            }

            if(nums[i] == minK){
                lastMinInd = i;
            }

            if(nums[i] == maxK){
                lastMaxInd = i;
            }

            res += Math.max(0L, Math.min(lastMinInd, lastMaxInd) - startInd + 1);
        }

        return res;
    }
}
