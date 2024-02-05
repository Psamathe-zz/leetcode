package com.example.leetcode.challenge.test2023.january.week3;

/**
 * Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.
 *
 * A subarray is a contiguous part of an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,5,0,-2,-3,1], k = 5
 * Output: 7
 * Explanation: There are 7 subarrays with a sum divisible by k = 5:
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 * Example 2:
 *
 * Input: nums = [5], k = 9
 * Output: 0
 */
public class SubarraySumsDivisibleK {
    public static void main(String[] args) {

    }
    public int subarraysDivByK(int[] nums, int k) {
        int length = nums.length;
        int[] dp = new int[length];
        int sum;
        int count;
        int res = 0;
        for (int i = length - 1; i >= 0; i--) {
            sum = 0;
            count = 0;
            for (int j = i; j < length; j++) {
                if(sum % k == 0 && dp[j] != 0){
                    count = 1 + dp[j];
                    break;
                }
                sum += nums[j];
                if(sum % k == 0)
                    count++;
            }
            dp[i] = count;
            res += dp[i];
        }
        return res;
    }

    /**
     * faster solution
     * @param A
     * @param K
     * @return
     */
    public int subarraysDivByKV1(int[] A, int K) {
        int[] map = new int[K];
        map[0] = 1;
        int count = 0, sum = 0;
        for(int a : A) {
            sum = (sum + a) % K;
            while(sum < 0)
                sum += K;  // Because -1 % 5 = -1, but we need the positive mod 4
            count += map[sum];
            map[sum]++;
        }
        return count;
    }

}
