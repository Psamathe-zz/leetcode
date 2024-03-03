package com.example.leetcode.biweeklycontest.old.contest71;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given a 0-indexed integer array nums and an integer pivot. Rearrange nums such that the following conditions are satisfied:
 *
 * Every element less than pivot appears before every element greater than pivot.
 * Every element equal to pivot appears in between the elements less than and greater than pivot.
 * The relative order of the elements less than pivot and the elements greater than pivot is maintained.
 * More formally, consider every pi, pj where pi is the new position of the ith element and pj is the new position of the jth element. For elements less than pivot, if i < j and nums[i] < pivot and nums[j] < pivot, then pi < pj. Similarly for elements greater than pivot, if i < j and nums[i] > pivot and nums[j] > pivot, then pi < pj.
 * Return nums after the rearrangement.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [9,12,5,10,14,3,10], pivot = 10
 * Output: [9,5,3,10,10,12,14]
 * Explanation:
 * The elements 9, 5, and 3 are less than the pivot so they are on the left side of the array.
 * The elements 12 and 14 are greater than the pivot so they are on the right side of the array.
 * The relative ordering of the elements less than and greater than pivot is also maintained. [9, 5, 3] and [12, 14] are the respective orderings.
 * Example 2:
 *
 * Input: nums = [-3,4,3,2], pivot = 2
 * Output: [-3,2,4,3]
 * Explanation:
 * The element -3 is less than the pivot so it is on the left side of the array.
 * The elements 4 and 3 are greater than the pivot so they are on the right side of the array.
 * The relative ordering of the elements less than and greater than pivot is also maintained. [-3] and [4, 3] are the respective orderings.
 */
public class PartitionArrayAccordingToGivenPivot {
    public static void main(String[] args) {

    }

    public int[] pivotArray(int[] nums, int pivot) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        for (int val : nums) {
            if(val < pivot)
                list1.add(val);
            else if(val == pivot)
                list2.add(val);
            else
                list3.add(val);
        }
        list1.addAll(list2);
        list1.addAll(list3);
        return list1.stream().mapToInt(e -> e).toArray();
    }
}
