package com.example.leetcode.medium;

import java.util.Arrays;

/**
 * Given an array nums of integers, a move consists of choosing any element and decreasing it by 1.
 *
 * An array A is a zigzag array if either:
 *
 * Every even-indexed element is greater than adjacent elements, ie. A[0] > A[1] < A[2] > A[3] < A[4] > ...
 *
 * OR, every odd-indexed element is greater than adjacent elements, ie. A[0] < A[1] > A[2] < A[3] > A[4] < ...
 * Return the minimum number of moves to transform the given array nums into a zigzag array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: 2
 * Explanation: We can decrease 2 to 0 or 3 to 1.
 * Example 2:
 *
 * Input: nums = [9,6,1,6,2]
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 */
public class DecreaseElementsToMakeArrayZigzag {
    public static void main(String[] args) {
        int[] num1 = new int[]{19,9,6,1,6,2};
        DecreaseElementsToMakeArrayZigzag decreaseElementsToMakeArrayZigzag = new DecreaseElementsToMakeArrayZigzag();
        int result = decreaseElementsToMakeArrayZigzag.movesToMakeZigzag(num1);
        System.out.println(result);
    }
    public int movesToMakeZigzag(int[] nums) {
        int temp1[] = new int[nums.length];
        int temp2[] = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            temp1[i] = nums[i];
            temp2[i] = nums[i];
        }
        int ans1 = 0;
        int ans2 = 0;
        for (int i = 1; i < temp1.length; i+=2) { //两边小 中间大
            if (temp1[i - 1] >= temp1[i]) {
                ans1 += temp1[i - 1] - temp1[i] + 1;
                temp1[i - 1] = temp1[i] - 1;
            }

            if (i + 1 < temp1.length && temp1[i + 1] >= temp1[i]) {
                ans1 += temp1[i + 1] - temp1[i] + 1;
                temp1[i + 1] = temp1[i] - 1;
            }
        }

        for (int i = 1; i < temp2.length; i+=2) { //两边大，中间小
            if (temp2[i - 1] <= temp2[i]) {
                ans2 += temp2[i] - temp2[i - 1] + 1;
                temp2[i] = temp2[i - 1] - 1;
            }
            if (i + 1 < temp2.length && temp2[i + 1] <= temp2[i]) {
                ans2 += temp2[i] - temp2[i + 1] + 1;
                temp2[i] = temp2[i + 1] - 1;
            }
        }
        return ans1 <= ans2 ? ans1 : ans2;
    }
    public int movesToMakeZigzagV2(int[] nums) {

        int[] temp = new int[nums.length + 2];
        temp[0] = Integer.MAX_VALUE;
        temp[nums.length+1] = Integer.MAX_VALUE;
        for(int i = 1; i<=nums.length;i++){
            temp[i] = nums[i-1];
        }

        int[]  res= new int[2];
        for(int i = 1; i< temp.length -1;i++){
            res[i%2] += Math.max(0,temp[i] - Math.min(temp[i-1],temp[i+1]) + 1);
        }
        return Arrays.stream(res).min().orElse(0);
    }
}
