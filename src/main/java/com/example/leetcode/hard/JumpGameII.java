package com.example.leetcode.hard;

import java.util.Arrays;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * Example:
 *
 * Input: [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2.
 *     Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Note:
 *
 * You can assume that you can always reach the last index.
 * [1,2,3]
 * [2,3,0,1,4]
 */
public class JumpGameII {
    public static void main(String[] args) {
        int[] nums = new int[]{2,3,0,1,4};
        JumpGameII jumpGameII = new JumpGameII();
        int res = jumpGameII.jump(nums);
        System.out.println(res);
    }

    /**
     * https://blog.csdn.net/princexiexiaofeng/article/details/79610978
     * 贪心
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int n = nums.length;
        // Exceptional Case:
        if(n == 0){
            return 0;
        }
        int preMaxEnd = 0, curMaxEnd = 0;
        int ans = 0;
        for(int i = 0; i <= n - 2; i++){
            curMaxEnd = Math.max(curMaxEnd, i + nums[i]);
            if(i == preMaxEnd){
                ans++;
                preMaxEnd = curMaxEnd;
            }
        }
        return ans;

    }


    public int jumpV1(int[] nums) {
        int n = nums.length;
        if(n <= 1){
            return 0;
        }
        int[] dp = new int[nums.length];
        // dp
        for(int i = 0; i < n - 1; i++){
            for(int j = 1; j <= nums[i]; j++){
                if(i + j <= n - 1){
                    dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
                }
            }
        }
        return dp[n - 1];

    }

}
