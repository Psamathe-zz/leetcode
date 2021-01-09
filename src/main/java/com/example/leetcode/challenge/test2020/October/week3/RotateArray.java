package com.example.leetcode.challenge.test2020.October.week3;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 *
 * Follow up:
 *
 * Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 * Could you do it in-place with O(1) extra space?
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * Example 2:
 *
 * Input: nums = [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 104
 * -231 <= nums[i] <= 231 - 1
 * 0 <= k <= 105
 */
public class RotateArray {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6,7};
        int k = 3;
        RotateArray rotateArray = new RotateArray();
        rotateArray.rotate(nums,k);
        System.out.println(k);
    }

    public void rotate(int[] nums, int k) {
        if(nums.length == 0)
            return;
        Deque<Integer> deque = new LinkedList<>(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        while (k>0){
            deque.addFirst(deque.pollLast());
            k--;
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = deque.pollFirst();
        }
    }

    /**
     * less memory
     * @param nums
     * @param k
     */
    public void rotateV1(int[] nums, int k) {
        for (int i=0; i<k; i++)
        {
            int n = nums.length;
            int temp = nums[n-1];
            for(int j=n-1 ; j>0; j--)
            {
                nums[j] = nums[j-1];

            }
            nums[0] = temp;
        }

    }

}
