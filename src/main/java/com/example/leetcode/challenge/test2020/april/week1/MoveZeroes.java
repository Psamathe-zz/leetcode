package com.example.leetcode.challenge.test2020.april.week1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 *
 * Example:
 *
 * Input: [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * Note:
 *
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 */
public class MoveZeroes {
    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        int[] nums1 = new int[]{2,1};
        MoveZeroes moveZeroes = new MoveZeroes();
        moveZeroes.moveZeroes(nums1);
        long end = System.currentTimeMillis();
        System.out.println(end - start);


        for(int var: nums1){
            System.out.println(var);
        }
    }


    public void moveZeroes(int[] nums) {
        List<Integer> resultList = Arrays.stream(nums).boxed().sorted((c1, c2) -> {
            if(c1.equals(c2)){
                return 0;
            } else if(c1.equals(Integer.valueOf(0))){
                return 1;
            } else if(c2.equals(Integer.valueOf(0))){
                return -1;
            } else
                return 1;
        }).collect(Collectors.toList());

        int index = 0;
        for(Integer value : resultList){
            nums[index] = value;
            index++;
        }
    }

    public void moveZeroesV2(int[] nums) {
        if (nums == null
                || nums.length == 0) {
            return;
        }
        int n = nums.length;
        int low = 0;
        int high = 0;
        while (low < n && high < n) {
            while (low < n && nums[low] != 0)
                low++;

            high = low;
            while (high < n && nums[high] == 0)
                high++;

            if (low < n && high < n) {
                int tmp = nums[low];
                nums[low] = nums[high];
                nums[high] = tmp;
                high = low + 1;
            }
        }
    }

    public void moveZeroesV3_fast(int[] nums) {
        int nonzeroNumIndex = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != 0) {
                nums[nonzeroNumIndex] = nums[i];
                nonzeroNumIndex++;
            }
        }

        for(int i = nonzeroNumIndex; i < nums.length; i++) {
            nums[i] = 0;
        }
    }


    public void moveZeroesV4_memory(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) nums[j++] = nums[i];
        }

        for (int i = j; j < nums.length; j++) {
            nums[j] = 0;
        }

    }


}
