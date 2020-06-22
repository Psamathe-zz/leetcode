package com.example.leetcode.hard;

import java.util.Arrays;

/**
 * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
 *
 * Return 0 if the array contains less than 2 elements.
 *
 * Example 1:
 *
 * Input: [3,6,9,1]
 * Output: 3
 * Explanation: The sorted form of the array is [1,3,6,9], either
 *              (3,6) or (6,9) has the maximum difference 3.
 * Example 2:
 *
 * Input: [10]
 * Output: 0
 * Explanation: The array contains less than 2 elements, therefore return 0.
 */
public class MaximumGap {

    public static void main(String[] args) {
        int[] nums = new int[]{10};
        MaximumGap maximumGap = new MaximumGap();
        int result = maximumGap.maximumGap(nums);
        System.out.println(result);
    }

    public int maximumGap(int[] nums) {
        int result = 0;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 1; i++){
            if (nums[i + 1] - nums[i] > result) {
                result = nums[i + 1] - nums[i];
            }
        }
        return result;
    }


    /**
     * less memory
     * @param nums
     * @return
     */
    public int maximumGapV3(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        if (min == max) return 0;
        int n = nums.length;
        int gap = (int) Math.ceil((double)(max - min) / (n - 1));
        int bucketLen = (max - min) / gap + 1;
        int[] bucketsMin = new int[bucketLen];
        int[] bucketsMax = new int[bucketLen];
        Arrays.fill(bucketsMin, Integer.MAX_VALUE);
        Arrays.fill(bucketsMax, Integer.MIN_VALUE);
        for (int num : nums) {
            int index = (num - min) / gap;
            bucketsMin[index] = Math.min(bucketsMin[index], num);
            bucketsMax[index] = Math.max(bucketsMax[index], num);
        }
        int prev = 0;
        int res = 0;
        for (int i = 1; i < bucketLen; i++) {
            if (bucketsMin[i] == Integer.MAX_VALUE) continue;
            res = Math.max(res, bucketsMin[i] - bucketsMax[prev]);
            prev = i;
        }
        return res;
    }
}
