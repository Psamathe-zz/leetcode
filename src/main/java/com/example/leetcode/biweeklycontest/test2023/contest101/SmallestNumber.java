package com.example.leetcode.biweeklycontest.test2023.contest101;

import java.util.Arrays;

/**
 * Given two arrays of unique digits nums1 and nums2, return the smallest number that contains at least one digit from each array.
 *
 *
 * Example 1:
 *
 * Input: nums1 = [4,1,3], nums2 = [5,7]
 * Output: 15
 * Explanation: The number 15 contains the digit 1 from nums1 and the digit 5 from nums2. It can be proven that 15 is the smallest number we can have.
 * Example 2:
 *
 * Input: nums1 = [3,5,2,6], nums2 = [3,1,7]
 * Output: 3
 * Explanation: The number 3 contains the digit 3 which exists in both arrays.
 */
public class SmallestNumber {
    public static void main(String[] args) {
        SmallestNumber smallestNumber = new SmallestNumber();
        smallestNumber.minNumber(new int[]{4,1,3}, new int[]{5,7});
    }

    public int minNumber(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int length1 = nums1.length;
        int length2 = nums2.length;

        int index1 = 0;
        int index2 = 0;
        while (index1 < length1 && index2 < length2) {
            if(nums1[index1] == nums2[index2]) {
                return nums1[index1];
            } else if(nums1[index1] < nums2[index2]) {
                index1++;
            } else if(nums2[index2] < nums1[index1]) {
                index2++;
            }
        }
        if(nums1[0] < nums2[0]) {
            return Integer.parseInt(String.valueOf(nums1[0])+ nums2[0]);
        } else {
            return Integer.parseInt(String.valueOf(nums2[0])+ nums1[0]);
        }

    }
}
