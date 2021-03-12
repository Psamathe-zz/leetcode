package com.example.leetcode.challenge.test2021.march.week1;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You have a set of integers s, which originally contains all the numbers from 1 to n. Unfortunately, due to some error, one of the numbers in s got duplicated to another number in the set, which results in repetition of one number and loss of another number.
 *
 * You are given an integer array nums representing the data status of this set after the error.
 *
 * Find the number that occurs twice and the number that is missing and return them in the form of an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,2,4]
 * Output: [2,3]
 * Example 2:
 *
 * Input: nums = [1,1]
 * Output: [1,2]
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 104
 * 1 <= nums[i] <= 104
 */
public class SetMismatch {
    public static void main(String[] args) {
        SetMismatch setMismatch = new SetMismatch();
        setMismatch.findErrorNums(new int[]{1,2,2,4});
    }

    public int[] findErrorNums(int[] nums) {
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        int length = nums.length;
        int[] count = new int[length];
        Arrays.sort(nums);
        for (int i = 0; i < length; i++) {
            count[nums[i] - 1]++;
        }
        for (int i = 0; i < length; i++) {
            if(count[i] > 1)
                list.add(i + 1);
            if(count[i] == 0)
                list2.add(i + 1);
        }
        list.addAll(list2);
        return list.stream().mapToInt(e->e).toArray();
    }
}
