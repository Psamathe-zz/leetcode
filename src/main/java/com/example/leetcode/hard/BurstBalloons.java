package com.example.leetcode.hard;

import java.util.Arrays;

/**
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums.
 * You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins.
 * Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
 *
 * Find the maximum coins you can collect by bursting the balloons wisely.
 *
 * Note:
 *
 * You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * Example:
 *
 * Input: [3,1,5,8]
 * Output: 167
 * Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *              coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 *              1,3,1,5,8,1
 *              https://juejin.im/post/5cd4c1cbf265da039b088963
 */
public class BurstBalloons {
    public static void main(String[] args) {
        int[] nums = new int[]{3,1,5,8};
        BurstBalloons burstBalloons = new BurstBalloons();
        int result = burstBalloons.maxCoins(nums);
        System.out.println(result);
    }

    public int maxCoinsVold(int[] nums) {
        int[] temp = new int[nums.length + 2];
        boolean[] visited = new boolean[nums.length + 2];
        temp[0] = 1;
        visited[0] = true;
        temp[nums.length + 1] = 1;
        visited[nums.length + 1] = true;
        for (int i = 0; i < nums.length; i++) {
            temp[i + 1] = nums[i];
        }
        int result = help(temp,visited);
        return result;
    }

    public int help(int[] numsTemp, boolean[] visited){

        int max = 0;

        for(int i = 1; i <= numsTemp.length - 2; i++){
            if(!visited[i]) {
                int indexPre = i - 1;
                while ( indexPre > 0 && visited[indexPre]) {
                    indexPre--;
                }
                int indexPost = i + 1;
                while ( indexPost < numsTemp.length && visited[indexPost] ) {
                    indexPost++;
                }
                indexPre = indexPre >= 0 ? indexPre : 0 ;
                indexPost = indexPost < numsTemp.length ? indexPost : numsTemp.length - 1 ;
                int value = numsTemp[i] * numsTemp[indexPre] * numsTemp[indexPost];
                boolean[] visitedTemp = Arrays.copyOf(visited,visited.length);
                visitedTemp[i] = true;
                int next = help(numsTemp,visitedTemp);
                if(value + next > max) {
                    max = value + next;
                }
            }
        }
        return max;
    }

    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        return helper(nums, 0, nums.length - 1);
    }

    private int helper(int[] nums, int l, int r) {
        if (l > r) {
            return 0;
        }

        int max = nums[l];
        for (int i = l; i <= r; ++i) {
            int cur = helper(nums, l, i - 1)
                    + get(nums, l - 1) * nums[i] * get(nums, r + 1)
                    + helper(nums, i + 1, r);

            max = Math.max(max, cur);
        }

        return max;
    }

    private int get(int[] nums, int i) {
        if (i < 0 || i >= nums.length) {
            return 1;
        }

        return nums[i];
    }

    public int maxCoinsV1 (int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] newNums = new int[nums.length + 2];

        newNums[0] = newNums[nums.length + 1] = 1;
        for (int i = 0; i < nums.length; ++i) {
            newNums[i + 1] = nums[i];
        }

        int n = newNums.length;

        int[][] dp = new int[n][n];

        for (int i = 2; i < n; ++i) {
            for (int l = 0; l < n - i; ++l) {
                int r = l + i;
                for (int j = l + 1; j < r; ++j) {
                    dp[l][r] = Math.max(dp[l][r],
                            newNums[l] * newNums[j] * newNums[r]
                                    + dp[l][j] + dp[j][r]);
                }
            }
        }

        return dp[0][n - 1];
    }


}
