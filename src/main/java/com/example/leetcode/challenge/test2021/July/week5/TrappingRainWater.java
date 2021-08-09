package com.example.leetcode.challenge.test2021.July.week5;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
 * Example 2:
 *
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 *
 */
public class TrappingRainWater {
    public static void main(String[] args) {
        TrappingRainWater trappingRainWater = new TrappingRainWater();
        trappingRainWater.trap(new int[]{4,2,0,3,2,5});
    }

    public int trap(int[] height) {
        int length = height.length;
        int[] left =  new int[length];
        int[] right =  new int[length];
        int res= 0;
        int max = 0;
        for (int i = 0; i < length; i++) {
            max = Math.max(max, height[i]);
            left[i] = max;
        }
        max = 0;
        for (int i = length - 1; i >= 0; i--) {
            max = Math.max(max, height[i]);
            right[i] = max;
        }
        for (int i = 0; i < length; i++) {
            res += Math.min(left[i], right[i]) - height[i];
        }

        return res;
    }
}
