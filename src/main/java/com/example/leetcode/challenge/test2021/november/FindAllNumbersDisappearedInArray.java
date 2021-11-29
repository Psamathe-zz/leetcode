package com.example.leetcode.challenge.test2021.november;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array nums of n integers where nums[i] is in the range [1, n], return an array of all the integers in the range [1, n] that do not appear in nums.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,3,2,7,8,2,3,1]
 * Output: [5,6]
 * Example 2:
 *
 * Input: nums = [1,1]
 * Output: [2]
 */
public class FindAllNumbersDisappearedInArray {
    public static void main(String[] args) {

    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int length = nums.length;
        boolean[] exist = new boolean[length];
        for (int i = 0; i < length; i++) {
            exist[nums[i] - 1] = true;
        }
        for (int i = 0; i < length; i++) {
            if(!exist[i])
                res.add(i+1);
        }

        return res;
    }
}
