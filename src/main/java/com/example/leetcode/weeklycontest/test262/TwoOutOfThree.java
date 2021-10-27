package com.example.leetcode.weeklycontest.test262;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given three integer arrays nums1, nums2, and nums3, return a distinct array containing all the values that are present in at least two out of the three arrays. You may return the values in any order.
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,1,3,2], nums2 = [2,3], nums3 = [3]
 * Output: [3,2]
 * Explanation: The values that are present in at least two arrays are:
 * - 3, in all three arrays.
 * - 2, in nums1 and nums2.
 * Example 2:
 *
 * Input: nums1 = [3,1], nums2 = [2,3], nums3 = [1,2]
 * Output: [2,3,1]
 * Explanation: The values that are present in at least two arrays are:
 * - 2, in nums2 and nums3.
 * - 3, in nums1 and nums2.
 * - 1, in nums1 and nums3.
 * Example 3:
 *
 * Input: nums1 = [1,2,2], nums2 = [4,3,3], nums3 = [5]
 * Output: []
 * Explanation: No value is present in at least two arrays.
 */
public class TwoOutOfThree {
    public static void main(String[] args) {

    }

    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        List<Integer> res = new ArrayList<>();
        int[] count = new int[101];
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        Arrays.sort(nums3);
        int pre = nums1[0];
        count[pre]++;
        for (int i = 1; i < nums1.length; i++) {
            if(nums1[i] != pre)
                count[nums1[i]]++;
            pre = nums1[i];
        }

        pre = nums2[0];
        count[pre]++;
        for (int i = 1; i < nums2.length; i++) {
            if(nums2[i] != pre)
                count[nums2[i]]++;
            pre = nums2[i];
        }

        pre = nums3[0];
        count[pre]++;
        for (int i = 1; i < nums3.length; i++) {
            if(nums3[i] != pre)
                count[nums3[i]]++;
            pre = nums3[i];
        }
        for (int i = 0; i < 101; i++) {
            System.out.println(count[i]);
            if(count[i] > 1)
                res.add(i);
        }
        return res;
    }
}
