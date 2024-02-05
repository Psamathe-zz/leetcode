package com.example.leetcode.weeklycontest.old.test195;

import java.util.Arrays;

/**
 * Given an array of integers nums and an integer target.
 *
 * Return the number of non-empty subsequences of nums such that the sum of the minimum and maximum element on it is less or equal than target.
 *
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,5,6,7], target = 9
 * Output: 4
 * Explanation: There are 4 subsequences that satisfy the condition.
 * [3] -> Min value + max value <= target (3 + 3 <= 9)
 * [3,5] -> (3 + 5 <= 9)
 * [3,5,6] -> (3 + 6 <= 9)
 * [3,6] -> (3 + 6 <= 9)
 * Example 2:
 *
 * Input: nums = [3,3,6,8], target = 10
 * Output: 6
 * Explanation: There are 6 subsequences that satisfy the condition. (nums can have repeated numbers).
 * [3] , [3] , [3,3], [3,6] , [3,6] , [3,3,6]
 * Example 3:
 *
 * Input: nums = [2,3,3,4,6,7], target = 12
 * Output: 61
 * Explanation: There are 63 non-empty subsequences, two of them don't satisfy the condition ([6,7], [7]).
 * Number of valid subsequences (63 - 2 = 61).
 * Example 4:
 *
 * Input: nums = [5,2,4,1,7,6,8], target = 16
 * Output: 127
 * Explanation: All non-empty subset satisfy the condition (2^7 - 1) = 127
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 * 1 <= target <= 10^6
 * [14,4,6,6,20,8,5,6,8,12,6,10,14,9,17,16,9,7,14,11,14,15,13,11,10,18,13,17,17,14,17,7,9,5,10,13,8,5,18,20,7,5,5,15,19,14]
 * 22
 * [9,25,9,28,24,12,17,8,28,7,21,25,10,2,16,19,12,13,15,28,14,12,24,9,6,7,2,15,19,13,30,30,23,19,11,3,17,2,14,20,22,30,12,1,11,2,2,20,20,27,15,9,10,4,12,30,13,5,2,11,29,5,3,13,22,5,16,19,7,19,11,16,11,25,29,21,29,3,2,9,20,15,9]
 * 32
 */
public class NumberOfSubsequences {
    public static void main(String[] args) {
        int[] nums = new int[]{9,25,9,28,24,12,17,8,28,7,21,25,10,2,16,19,12,13,15,28,14,12,24,9,6,7,2,15,19,13,30,30,23,19,11,3,17,2,14,20,22,30,12,1,11,2,2,20,20,27,15,9,10,4,12,30,13,5,2,11,29,5,3,13,22,5,16,19,7,19,11,16,11,25,29,21,29,3,2,9,20,15,9};
        int target = 32;
        NumberOfSubsequences numberOfSubsequences = new NumberOfSubsequences();
        int result = numberOfSubsequences.numSubseq(nums,target);
        System.out.println(result);
    }
    int module = 1000000007;
    int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);

        int length = nums.length;
        long count = 0;
        int right = length - 1;

        for (int i = 0; i < length; i++) {
            if(nums[i] * 2 > target)
                break;
            while (nums[right] + nums[i] > target && right > i){
                right--;
            }
            count += myPow(2,right - i);
            count = count % module;
        }
        return (int)count;
    }

    long myPow(long base, int exp)
    {
        long res = 1;
        while (exp > 0) {
            if (exp % 2 == 1)
                res = res * base % module;
            base = (base * base) % module;
            exp >>= 1;
        }
        return res;
    }
}
