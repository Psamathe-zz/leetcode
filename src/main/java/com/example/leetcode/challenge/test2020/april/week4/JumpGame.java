package com.example.leetcode.challenge.test2020.april.week4;


import java.util.LinkedList;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Determine if you are able to reach the last index.
 *
 * Example 1:
 *
 * Input: [2,3,3,1,0,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum
 *              jump length is 0, which makes it impossible to reach the last index.
 */
public class JumpGame {


    public static void main(String[] args) {
        int[] nums = new int[]{2,3,1,1,1,4};
        JumpGame jumpGame = new JumpGame();
        boolean result = jumpGame.canJump(nums);
        System.out.println(result);
    }


    public boolean canJump(int[] nums){
        int last = nums.length - 1;
        for (int i = nums.length -2; i>=0;i--){
            if(nums[i] >= last - i){
                last = i;
            }
        }
        if(last <= 0)
            return true;
        else
            return false;
    }

    public boolean canJumpV2(int[] nums) {
        int reach = 0;
        int i;
        for ( i = 0; i < nums.length && i <= reach; i++) {
            reach = Math.max(reach, i + nums[i]);
        }
        return (i == nums.length);
    }


    /**
     * less memory
     * @param nums
     * @return
     */
    public boolean canJumpV4(int[] nums) {

        if (nums.length == 0) {
            return false;
        }

        boolean[] visit = new boolean[nums.length];
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (queue.size() > 0) {
            int cur = queue.remove();
            if (cur + nums[cur] >= nums.length - 1) {
                return true;
            }
            for (int i = 1; i <= nums[cur]; i++) {
                if (!visit[cur + i]) {
                    queue.add(cur + i);
                    visit[cur + i] = true;
                }
            }
        }

        return false;
    }


    public boolean canJumpVOld(int[] nums) {
        boolean isChange;
        boolean[] reachable = new boolean[nums.length];
        reachable[nums.length - 1] = true;
        do {
            isChange = false;
            for (int i = 0; i < reachable.length; i++) {
                if (reachable[i]) {
                    for (int j = 0; j < i; j++) {
                        if (reachable[j] == false && nums[j] >= i - j) {
                            reachable[j] = true;
                            isChange = true;
                            if(j==0)
                                return reachable[0];
                        }
                    }
                }
            }
        } while (isChange == true);


        return reachable[0];
    }

}
