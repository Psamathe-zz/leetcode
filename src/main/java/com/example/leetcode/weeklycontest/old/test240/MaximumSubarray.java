package com.example.leetcode.weeklycontest.old.test240;


import java.util.Stack;

/**
 * The min-product of an array is equal to the minimum value in the array multiplied by the array's sum.
 *
 * For example, the array [3,2,5] (minimum value is 2) has a min-product of 2 * (3+2+5) = 2 * 10 = 20.
 * Given an array of integers nums, return the maximum min-product of any non-empty subarray of nums. Since the answer may be large, return it modulo 109 + 7.
 *
 * Note that the min-product should be maximized before performing the modulo operation. Testcases are generated such that the maximum min-product without modulo will fit in a 64-bit signed integer.
 *
 * A subarray is a contiguous part of an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,2]
 * Output: 14
 * Explanation: The maximum min-product is achieved with the subarray [2,3,2] (minimum value is 2).
 * 2 * (2+3+2) = 2 * 7 = 14.
 * Example 2:
 *
 * Input: nums = [2,3,3,1,2]
 * Output: 18
 * Explanation: The maximum min-product is achieved with the subarray [3,3] (minimum value is 3).
 * 3 * (3+3) = 3 * 6 = 18.
 * Example 3:
 *
 * Input: nums = [3,1,5,6,4,2]
 * Output: 60
 * Explanation: The maximum min-product is achieved with the subarray [5,6,4] (minimum value is 4).
 * 4 * (5+6+4) = 4 * 15 = 60.
 */
public class MaximumSubarray {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/maximum-subarray-min-product/solution/javadan-diao-zhan-qian-zhui-he-by-lhp155-sb98/
     * @param nums
     * @return
     */
    public int maxSumMinProduct(int[] nums) {
        // 先计算前缀和
        long[] prefixNums = new long[nums.length];
        prefixNums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefixNums[i] = prefixNums[i - 1] + nums[i];
        }

        Stack<Integer> upStack = new Stack<>();
        long max = 0;
        // 数组元素 下标 人单调递增栈
        for (int i = 0; i < nums.length; i++) {
            if (upStack.isEmpty() || nums[upStack.peek()] <= nums[i]) {
                upStack.push(i);
            } else {
                // 每次出栈计算乘积
                while (!upStack.isEmpty() && nums[upStack.peek()] > nums[i]) {
                    int minIndex = upStack.pop();
                    int start = upStack.isEmpty() ? 0 : upStack.peek() + 1;
                    int end = i - 1;
                    max = Math.max(max, calcu(prefixNums, start, end, nums[minIndex]));
                }
                upStack.push(i);
            }
        }
        // 栈中剩余元素依次出栈，并计算乘积
        while (!upStack.isEmpty()) {
            int minIndex = upStack.pop();
            int end = nums.length - 1;
            int start = upStack.isEmpty() ? 0 : upStack.peek() + 1;
            max = Math.max(max, calcu(prefixNums, start, end, nums[minIndex]));
        }
        return (int) (max % 1000000007);
    }

    // 求乘积
    private long calcu(long[] prefixNums, int start, int end, int min) {
        long sum = start == 0 ?
                prefixNums[end] :
                prefixNums[end] - prefixNums[start - 1];
        return sum * min;
    }

}
