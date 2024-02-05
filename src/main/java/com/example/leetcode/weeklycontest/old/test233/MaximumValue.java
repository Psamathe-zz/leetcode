package com.example.leetcode.weeklycontest.old.test233;


/**
 * You are given three positive integers n, index and maxSum. You want to construct an array nums (0-indexed) that satisfies the following conditions:
 *
 * nums.length == n
 * nums[i] is a positive integer where 0 <= i < n.
 * abs(nums[i] - nums[i+1]) <= 1 where 0 <= i < n-1.
 * The sum of all the elements of nums does not exceed maxSum.
 * nums[index] is maximized.
 * Return nums[index] of the constructed array.
 *
 * Note that abs(x) equals x if x >= 0, and -x otherwise.
 *
 * Example 1:
 *
 * Input: n = 4, index = 2,  maxSum = 6
 * Output: 2
 * Explanation: The arrays [1,1,2,1] and [1,2,2,1] satisfy all the conditions. There are no other valid arrays with a larger value at the given index.
 * Example 2:
 *
 * Input: n = 6, index = 1,  maxSum = 10
 * Output: 3
 * 4
 * 3
 * 782939544
 *
 */
public class MaximumValue {
    public static void main(String[] args) {
        MaximumValue maximumValue = new MaximumValue();
        maximumValue.maxValue(3,2,18);
    }

    public int maxValue(int n, int index, int maxSum) {
        int left = index;
        int right = index;
        if(maxSum < n)
            return 0;
        int max = 1;
        maxSum -= n;
        while (maxSum > 0){
            maxSum -= (right - left + 1);
            if(maxSum >= 0) {
                if(left == 0 && right == n-1) {
                    max += maxSum / n + 1;
                    maxSum = maxSum % n;
                } else {
                    max++;
                }
            }
            if(left > 0)
                left--;
            if(right < n - 1)
                right++;
        }
        return max;
    }
}
