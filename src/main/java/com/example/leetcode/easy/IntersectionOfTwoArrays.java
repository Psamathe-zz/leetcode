package com.example.leetcode.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given two arrays, write a function to compute their intersection.
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2]
 * Example 2:
 *
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [9,4]
 * Note:
 *
 * Each element in the result must be unique.
 * The result can be in any order.
 */
public class IntersectionOfTwoArrays {
    public static void main(String[] args) {

    }

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for(int value : nums1){
            set.add(value);
        }
        for(int value : nums2){
            if(set.contains(value)){
                set2.add(value);
            }
        }
        return set2.stream().mapToInt(e -> e).toArray();
    }

    /**
     * faster solution
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersectionV1(int[] nums1, int[] nums2) {

        int j=0;
        int freq[] = new int[1000];
        for(int i=0; i<nums1.length; i++) {
            freq[nums1[i]]++;
        }

        int res[] = new int[nums1.length];
        for(int i=0; i<nums2.length; i++) {
            if(freq[nums2[i]] > 0) {
                res[j++] = nums2[i];
                freq[nums2[i]] = 0;
            }
        }
        return Arrays.copyOf(res, j);
    }
}
