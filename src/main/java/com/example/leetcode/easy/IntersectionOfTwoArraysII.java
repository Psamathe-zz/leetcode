package com.example.leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Given two arrays, write a function to compute their intersection.
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 * Example 2:
 *
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [4,9]
 * Note:
 *
 * Each element in the result should appear as many times as it shows in both arrays.
 * The result can be in any order.
 * Follow up:
 *
 * What if the given array is already sorted? How would you optimize your algorithm?
 * What if nums1's size is small compared to nums2's size? Which algorithm is better?
 * What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 */
public class IntersectionOfTwoArraysII {
    public static void main(String[] args) {

    }

    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> result = new ArrayList<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int index1 = 0;
        int index2 = 0;
        int length1 = nums1.length;
        int length2 = nums2.length;

        while (index1 < length1 && index2 < length2){
            if(nums1[index1] == nums2[index2]){
                result.add(nums1[index1]);
                index1++;
                index2++;
            } else if(nums1[index1] < nums2[index2]){
                index1++;
            } else {
                index2++;
            }
        }

        return result.stream().mapToInt(i->i).toArray();
    }


    /**
     * fasters solution
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersectV1(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        int[] map = new int[1000];
        HashMap<Integer, Integer> mapL = new HashMap<>();
        for (int num : nums1) {
            if (num >= 0 && num < 1000)
                map[num]++;
            else
            if (!mapL.containsKey(num))
                mapL.put(num, 1);
            else
                mapL.replace(num, mapL.get(num) + 1);
        }

        int i = 0;
        for (int num : nums2) {
            if (num >= 0 && num < 1000) {
                if (map[num] > 0) {
                    res[i++] = num;
                    map[num]--;
                }
            }
            else {
                if (mapL.containsKey(num)) {
                    int v = mapL.get(num);
                    if (v > 0) {
                        res[i++] = num;
                        mapL.put(num, v - 1);
                    }
                }
            }
        }
        return Arrays.copyOfRange(res, 0, i);
    }
}
