package com.example.leetcode.weeklycontest.old.test333;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given two 2D integer arrays nums1 and nums2.
 *
 * nums1[i] = [idi, vali] indicate that the number with the id idi has a value equal to vali.
 * nums2[i] = [idi, vali] indicate that the number with the id idi has a value equal to vali.
 * Each array contains unique ids and is sorted in ascending order by id.
 *
 * Merge the two arrays into one array that is sorted in ascending order by id, respecting the following conditions:
 *
 * Only ids that appear in at least one of the two arrays should be included in the resulting array.
 * Each id should be included only once and its value should be the sum of the values of this id in the two arrays. If the id does not exist in one of the two arrays then its value in that array is considered to be 0.
 * Return the resulting array. The returned array must be sorted in ascending order by id.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [[1,2],[2,3],[4,5]], nums2 = [[1,4],[3,2],[4,1]]
 * Output: [[1,6],[2,3],[3,2],[4,6]]
 * Explanation: The resulting array contains the following:
 * - id = 1, the value of this id is 2 + 4 = 6.
 * - id = 2, the value of this id is 3.
 * - id = 3, the value of this id is 2.
 * - id = 4, the value of this id is 5 + 1 = 6.
 * Example 2:
 *
 * Input: nums1 = [[2,4],[3,6],[5,5]], nums2 = [[1,3],[4,3]]
 * Output: [[1,3],[2,4],[3,6],[4,3],[5,5]]
 * Explanation: There are no common ids, so we just include each id with its value in the resulting list.
 *
 */
public class MergeTwoArraysSummingValues {
    public static void main(String[] args) {

    }

    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;

        List<int[]> list = new ArrayList<>();
        int index1 = 0;
        int index2 = 0;
        while (index1 < length1 || index2 < length2) {
            if(index1 == length1){
                list.add(new int[]{nums2[index2][0], nums2[index2][1]});
                index2++;
            } else if(index2 == length2) {
                list.add(new int[]{nums1[index1][0], nums1[index1][1]});
                index1++;
            } else if(nums1[index1][0] > nums2[index2][0] ) {
                list.add(new int[]{nums2[index2][0], nums2[index2][1]});
                index2++;
            } else if(nums1[index1][0] < nums2[index2][0]) {
                list.add(new int[]{nums1[index1][0], nums1[index1][1]});
                index1++;
            } else {
                list.add(new int[]{nums1[index1][0], nums1[index1][1] + nums2[index2][1]});
                index1++;
                index2++;
            }
        }
        int[][] res = new int[list.size()][];
        int j = 0;
        while (j < list.size())
            res[j] = list.get(j++);
        return res;

    }
}
