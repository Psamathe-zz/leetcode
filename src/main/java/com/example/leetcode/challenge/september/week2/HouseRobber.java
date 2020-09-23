package com.example.leetcode.challenge.september.week2;

/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 *              Total amount you can rob = 1 + 3 = 4.
 * Example 2:
 *
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 *              Total amount you can rob = 2 + 9 + 1 = 12.
 *[2,1,1,2]
 *
 * Constraints:
 *
 * 0 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 */
public class HouseRobber {
    public static void main(String[] args) {
        int[] nums = new int[]{2,1,1,2};
        HouseRobber houseRobber = new HouseRobber();
        int res = houseRobber.rob(nums);
        System.out.println(res);
    }

    public int rob(int[] nums) {
        int length = nums.length;
        if(length == 0)
            return 0;
        int[] rob = new int[length];
        int[] notRob = new int[length];

        for (int i = 0; i < length; i++) {
            if(i==0){
                rob[0] = nums[0];
                notRob[0] = 0;
            } else {
                rob[i] = notRob[i - 1] + nums[i];
                notRob[i] =  Math.max(rob[i - 1],notRob[i - 1]);
            }
        }
        return Math.max(rob[length-1],notRob[length-1]);
    }


    /**
     * faster solution
     * @param nums
     * @return
     */
    public int robV1(int[] nums) {
        int prevToPrev = 0, prev = 0, maxSoFar = 0;
        for (int n : nums) {
            maxSoFar = Math.max(prev, prevToPrev + n);
            prevToPrev = prev;
            prev = maxSoFar;
        }
        return maxSoFar;
    }
    /**
     * less memory
     * @param nums
     * @return
     */
    public int robV2(int[] nums) {
        int n=nums.length;
        int dp[]=new int[n];
        if(n==0)
        {
            return 0;
        }
        if(n==1)
        {
            return nums[0];
        }
        dp[0]=nums[0];
        dp[1]=Math.max(nums[0],nums[1]);
        for(int i=2;i<n;i++)
        {
            dp[i]=Math.max(dp[i-1],nums[i]+dp[i-2]);
        }
        return dp[n-1];

    }
}
