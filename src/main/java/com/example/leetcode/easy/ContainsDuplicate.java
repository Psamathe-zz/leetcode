package com.example.leetcode.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Given an array of integers, find if the array contains any duplicates.
 *
 * Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.
 *
 * Example 1:
 *
 * Input: [1,2,3,1]
 * Output: true
 * Example 2:
 *
 * Input: [1,2,3,4]
 * Output: false
 * Example 3:
 *
 * Input: [1,1,1,3,3,4,3,2,4,2]
 * Output: true
 */
public class ContainsDuplicate {

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,3,3,4,3,2,4,2};
        ContainsDuplicate containsDuplicate = new ContainsDuplicate();
        boolean result = containsDuplicate.containsDuplicate(nums);
        System.out.println(result);
    }

    public boolean containsDuplicate(int[] nums) {
        return Arrays.stream(nums).boxed().collect(Collectors.toSet()).size()!=nums.length;
    }


    /**
     * faster solution
     * @param nums
     * @return
     */
    public boolean containsDuplicateV2(int[] nums) {

        Arrays.sort(nums);

        for(int i=0; i<nums.length-1; ++i){

            if(nums[i] == nums[i+1])

                return true;
        }

        return false;
    }


    /**
     * less memory
     * @param nums
     * @return
     */

    public boolean containsDuplicateV3(int[] nums) {
        Set<Integer> set = new HashSet<>();
        Arrays.stream(nums).forEach(set::add);
        return set.size() != nums.length;
    }
}
