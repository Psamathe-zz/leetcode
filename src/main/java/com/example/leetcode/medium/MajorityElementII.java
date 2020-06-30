package com.example.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 *
 * Note: The algorithm should run in linear time and in O(1) space.
 *
 * Example 1:
 *
 * Input: [3,2,3]
 * Output: [3]
 * Example 2:
 *
 * Input: [1,1,1,3,3,2,2,2]
 * Output: [1,2]
 */
public class MajorityElementII {
    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,3,3,2,2,2};
        MajorityElementII majorityElementII = new MajorityElementII();
        majorityElementII.majorityElementV1(nums);
    }

    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if(nums.length == 0)
            return result;
        Arrays.sort(nums);
        int start = 0;
        int current = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if(current != nums[i]){
                if((i-start) * 3 > nums.length){
                    result.add(current);
                }
                current = nums[i];
                start = i;
            }
        }
        if((nums.length-start) * 3 > nums.length){
            result.add(current);
        }
        return result;
    }

    public List<Integer> majorityElementV1(int[] nums) {
        if(nums.length <= 2)
            return Arrays.stream(nums).boxed().distinct().collect(Collectors.toList());

        int num1 ;
        int count1;

        int num2;
        int count2;
        if(nums[0] != nums[1]){
            num1 = nums[0];
            count1 = 1;

            num2 = nums[1];
            count2 = 1;
        } else {
            num1 = nums[0];
            count1 = 2;

            num2 = 0;
            count2 = 0;
        }


        for (int i = 2; i < nums.length; i++) {
            if (nums[i] == num1) {
                count1++;
            }else if (nums[i] == num2) {
                count2++;
            }else if (count1 == 0) {
                num1 = nums[i];
                count1++;
            }else if (count2 == 0) {
                num2 = nums[i];
                count2++;
            }else {
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;

        for (int num : nums) {
            if (num == num1) {
                count1++;
            }

            else if (num == num2) {
                count2++;
            }
        }

        List<Integer> result = new ArrayList<>();

        if (count1 != 0 && count1 * 3 > nums.length) {
            result.add(num1);
        }

        if (count2 != 0 && count2 * 3 > nums.length) {
            result.add(num2);
        }

        return result;
    }
}
