package com.example.leetcode.medium;

import java.util.Arrays;

/**
 * You are given a circular array nums of positive and negative integers.
 * If a number k at an index is positive, then move forward k steps. Conversely, if it's negative (-k), move backward k steps.
 * Since the array is circular, you may assume that the last element's next element is the first element, and the first element's previous element is the last element.
 *
 * Determine if there is a loop (or a cycle) in nums. A cycle must start and end at the same index and the cycle's length > 1.
 * Furthermore, movements in a cycle must all follow a single direction. In other words, a cycle must not consist of both forward and backward movements.
 *
 *
 *
 * Example 1:
 *
 * Input: [2,-1,1,2,2]
 * Output: true
 * Explanation: There is a cycle, from index 0 -> 2 -> 3 -> 0. The cycle's length is 3.
 * Example 2:
 *
 * Input: [-1,2]
 * Output: false
 * Explanation: The movement from index 1 -> 1 -> 1 ... is not a cycle, because the cycle's length is 1. By definition the cycle's length must be greater than 1.
 * Example 3:
 *
 * Input: [-2,1,-1,-2,-2]
 * Output: false
 * Explanation: The movement from index 1 -> 2 -> 1 -> ... is not a cycle, because movement from index 1 -> 2 is a forward movement, but movement from index 2 -> 1 is a backward movement. All movements in a cycle must follow a single direction.
 *
 *
 * Note:
 *
 * -1000 ≤ nums[i] ≤ 1000
 * nums[i] ≠ 0
 * 1 ≤ nums.length ≤ 5000
 *
 *
 * Follow up:
 *
 * Could you solve it in O(n) time complexity and O(1) extra space complexity?
 * [1,2,3,4,5]
 * [-2,1,-1,-2,-2]
 * [2,-1,2,-1,3]
 * [-1,-1,-3]
 * [-1,-1,-1]
 * [-2,-3,-9]
 */
public class CircularArrayLoop {
    public static void main(String[] args) {
        int[] nums = new int[]{-2,-3,-9};
        CircularArrayLoop circularArrayLoop = new CircularArrayLoop();
        boolean result = circularArrayLoop.circularArrayLoop(nums);
        System.out.println(result);
    }

    /**
     * http://bookshadow.com/weblog/2016/11/09/leetcode-circular-array-loop/
     * 递归
     */
    int[] tempNums;
    public boolean circularArrayLoop(int[] nums) {
        tempNums = Arrays.copyOf(nums,nums.length);
        for (int i = 0; i < nums.length; i++) {
            dfs(i,0);
        }
        for (int i = 0; i <tempNums.length ; i++) {
            if(tempNums[i] != 0)
                return true;
        }
        return false;
    }

    public int dfs(int index,int step){
        System.out.println(step);
        if(step < tempNums.length){
            int next = index + tempNums[index] + tempNums.length;
            while (next < 0) {
                next += tempNums.length;
            }
            next = next % tempNums.length;
            if(next == index || tempNums[index] * tempNums[next] <= 0 || dfs(next, step + 1) == 0){
                tempNums[index] = 0;
            }
        }
        return tempNums[index];
    }

    /**
     * 快慢指针
     * https://blog.csdn.net/u012737193/article/details/79050869
     * @param nums
     * @return
     */
    public boolean circularArrayLoopV2(int[] nums) {
        if (nums == null || nums.length <= 1) return false;

        int len = nums.length;
        for (int i = 0 ; i < len; i++) {
            if (nums[i] == 0) {
                continue;
            }
            int slow = i;
            int fast = getIndex(i, nums);
            while (nums[fast] * nums[i] > 0 && nums[getIndex(fast, nums)] * nums[i] > 0) {
                if (slow == fast) {
                    if (slow == getIndex(slow, nums)) {
                        break;
                    }
                    return true;
                }
                slow = getIndex(slow, nums);
                fast = getIndex(getIndex(fast, nums), nums);
            }

            // fast = i;
            int currVal = nums[i];
            while (currVal * nums[slow] > 0) {
                int nextIdx = getIndex(slow, nums);
                nums[slow] = 0;
                slow = nextIdx;
            }
        }

        return false;
    }

    private int getIndex(int idx, int[] nums) {
        int n = nums.length;
        return idx + nums[idx] >= 0 ? (idx + nums[idx]) % n : n + ((idx + nums[idx]) % n);
    }

    public boolean circularArrayLoopOld(int[] nums) {
        boolean result = false;
        int next;
        int step = 0;
        int[] dp = new int[nums.length];
        int[] stepForDp = new int[nums.length];
        while (step < nums.length && !result) {
            step++;
            for (int i = 0; i < nums.length && !result; i++) {
                int oldDp = dp[i];
                if(step==1)
                    next = i + nums[i];
                else {
                    if(nums[i] * nums[oldDp] < 0)
                        continue;
                    next = oldDp + nums[oldDp];
                }
                if (next < 0)
                    next = nums.length - next;
                if (next >= nums.length)
                    next = next % nums.length;
                dp[i] = next;
                if(dp[i] != oldDp)
                    stepForDp[i] = step;
                if(dp[i] == i && stepForDp[i] > 1)
                    result = true;
            }
        }
        return result;
    }
}
