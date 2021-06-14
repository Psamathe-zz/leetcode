package com.example.leetcode.challenge.test2021.june.week2;


import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * You are given a 0-indexed integer array nums and an integer k.
 *
 * You are initially standing at index 0. In one move, you can jump at most k steps forward without going outside the boundaries of the array. That is, you can jump from index i to any index in the range [i + 1, min(n - 1, i + k)] inclusive.
 *
 * You want to reach the last index of the array (index n - 1). Your score is the sum of all nums[j] for each index j you visited in the array.
 *
 * Return the maximum score you can get.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,-1,-2,4,-7,3], k = 2
 * Output: 7
 * Explanation: You can choose your jumps forming the subsequence [1,-1,4,3] (underlined above). The sum is 7.
 * Example 2:
 *
 * Input: nums = [10,-5,-2,4,0,3], k = 3
 * Output: 17
 * Explanation: You can choose your jumps forming the subsequence [10,4,3] (underlined above). The sum is 17.
 * Example 3:
 *
 * Input: nums = [1,-5,-20,4,-1,3,-6,-3], k = 2
 * Output: 0
 */
public class JumpGameVI {
    public static void main(String[] args) {
        JumpGameVI jumpGameVI = new JumpGameVI();
        int res = jumpGameVI.maxResult(new int[]{1,-5,-20,4,-1,3,-6,-3}, 2);
        System.out.println(res);
    }

    public int maxResult(int[] nums, int k) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        Deque<Integer> windowIndices = new LinkedList<>();
        for (int i = 1; i < nums.length; i++) {
            // 如果新的索引i所对应的元素dp[i - 1]大于队尾rear所对应的数组元素dp[rear]，就循环弹出队尾，直到新的元素i - 1能够成为队尾
            // 因为dp[rear] < dp[i - 1]且rear < i - 1，只要窗口继续向右移，dp[rear]就一定会被dp[i - 1]压在下面，不会成为窗口最大元素
            while (!windowIndices.isEmpty() && dp[i - 1] >= dp[windowIndices.peekLast()]) {
                windowIndices.pollLast();
            }
            windowIndices.offerLast(i - 1);
            if (windowIndices.peekFirst() < i - k) {
                windowIndices.pollFirst();
            }
            dp[i] = dp[windowIndices.peekFirst()] + nums[i];
        }
        return dp[nums.length - 1];
    }
}
