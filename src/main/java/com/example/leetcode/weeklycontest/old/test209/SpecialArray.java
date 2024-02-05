package com.example.leetcode.weeklycontest.old.test209;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 *You are given an array nums of non-negative integers. nums is considered special if there exists a number x such that there are exactly x numbers in nums that are greater than or equal to x.
 *
 * Notice that x does not have to be an element in nums.
 *
 * Return x if the array is special, otherwise, return -1. It can be proven that if nums is special, the value for x is unique.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,5]
 * Output: 2
 * Explanation: There are 2 values (3 and 5) that are greater than or equal to 2.
 * Example 2:
 *
 * Input: nums = [0,0]
 * Output: -1
 * Explanation: No numbers fit the criteria for x.
 * If x = 0, there should be 0 numbers >= x, but there are 2.
 * If x = 1, there should be 1 number >= x, but there are 0.
 * If x = 2, there should be 2 numbers >= x, but there are 0.
 * x cannot be greater since there are only 2 numbers in nums.
 * Example 3:
 *
 * Input: nums = [0,4,3,0,4]
 * Output: 3
 * Explanation: There are 3 values that are greater than or equal to 3.
 * 0 0 4 4 5
 *
 *
 * Example 4:
 *
 * Input: nums = [3,6,7,7,0]
 * Output: -1
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 */
public class SpecialArray {

    public static void main(String[] args) {
        int[] nums = new int[]{3,5};
        SpecialArray specialArray = new SpecialArray();
        int res = specialArray.specialArray(nums);
        System.out.println(res);
    }

    public int specialArray(int[] nums) {
        int length = nums.length;
        List<Integer> list = Arrays.stream(nums).boxed().sorted().collect(Collectors.toList());
        Queue<Integer> queue = new LinkedList<>(list);
        int val;
        int countPoll = 0;
        int last = 0;
        while (!queue.isEmpty()){
            val = queue.peek();
            for (int i = last + 1; i <= val; i++) {
                if(i == length - countPoll){
                    return i;
                }
            }
            while (!queue.isEmpty() && queue.peek() == val){
                queue.poll();
                countPoll++;
            }
            last = val;
        }
        return -1;
    }

    /**
     * faster solution
     * https://leetcode.com/submissions/detail/404460277/
     * @param nums
     * @return
     */
    public int specialArrayV1(int[] nums) {
        for (int x = 0; x <= 100; x++) {
            int count = 0;
            for (int i : nums) {
                if (i >= x) {
                    count++;
                }
            }
            if (count == x) {
                return x;
            }
        }
        return -1;
    }

    /**
     * less memory
     * @param nums
     * @return
     */
    public int specialArrayV2(int[] nums) {
        for (int i = 0; i <= 1000; i++) {
            int count = 0;
            for (int j : nums)
                if (j >= i)
                    count++;
            if (count == i)
                return i;
        }
        return -1;
    }
}
