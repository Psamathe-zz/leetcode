package com.example.leetcode.easy;

import java.util.Arrays;

/**
 * We define a harmounious array as an array where the difference between its maximum value and its minimum value is exactly 1.
 *
 * Now, given an integer array, you need to find the length of its longest harmonious subsequence among all its possible subsequences.
 *
 * Example 1:
 *
 * Input: [1,3,2,2,5,2,3,7]
 * Output: 5
 * Explanation: The longest harmonious subsequence is [3,2,2,2,3].
 *
 *
 * Note: The length of the input array will not exceed 20,000.
 */
public class LongestHarmoniousSubsequence {
    public static void main(String[] args) {
        int[] nums = new int[]{1,3,2,2,5,2,3,7};
        LongestHarmoniousSubsequence longestHarmoniousSubsequence = new LongestHarmoniousSubsequence();
        int result = longestHarmoniousSubsequence.findLHS(nums);
        System.out.println(result);
    }

    public int findLHS(int[] nums) {
        int result = Integer.MIN_VALUE;
        Arrays.sort(nums);
        int length = nums.length;
        int pre;
        int current;
        int preCount;
        int currentCount;
        if(length>=1) {
            current = nums[0];
            currentCount = 1;
            preCount = 0;
            pre = 0;
        }
        else
            return 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == current) {
                currentCount++;
            } else {
                if(preCount == 0)
                    result = 0;
                else if(pre + 1 == current && result < preCount + currentCount){
                    result = preCount + currentCount;
                }
                pre = current;
                preCount = currentCount;
                current = nums[i];
                currentCount = 1;
            }
        }
        if(preCount == 0)
            result = 0;
        else if(pre + 1 == current && result < preCount + currentCount){
            result = preCount + currentCount;
        }
        return result;
    }

    public int findLHSV1(int[] nums) {
        int len = nums.length;
        int r = 0;
        Arrays.sort(nums);
        int start = 0;
        int newStart = 0;
        for (int i = 1; i < len; ++i) {
            if (nums[i] - nums[start] > 1) {
                start = newStart;
            }

            if (nums[i] != nums[i - 1]) {
                newStart = i;
            }

            if (nums[i] - nums[start] == 1) {
                r = r > i - start + 1 ? r : i - start + 1;
            }
        }
        return r;
    }

}
