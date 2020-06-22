package com.example.leetcode.easy;

import java.util.Arrays;

/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 *
 * Note:
 *
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
 * Example:
 *
 * Input:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * Output: [1,2,2,3,5,6]
 */
public class MergeSortedArray {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2,3,0,0,0};
        int[] nums2 = new int[]{2,5,6};
        int m = 3;
        int n = 3;
        MergeSortedArray mergeSortedArray = new MergeSortedArray();
        mergeSortedArray.merge(nums1,m,nums2,n);
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for(int i = 0; i < n; i++){
            nums1[m + i] = nums2[i];
        }
        Arrays.sort(nums1);
    }


    /**
     * faster solution
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void mergeV1(int[] nums1, int m, int[] nums2, int n) {
        int[] tmp = new int[m+n];
        int i=0, j=0, k=0;
        while (i<m && j<n)
        {
            if(nums1[i] < nums2[j])
                tmp[k++] = nums1[i++];
            else
                tmp[k++] = nums2[j++];
        }
        while (i<m)
            tmp[k++] = nums1[i++];
        while (j<n)
            tmp[k++] = nums2[j++];
        for(int ii=0; ii<nums1.length; ii++) {
            nums1[ii] = tmp[ii];
        }
    }
    /**
     * less momory
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void mergeV3(int[] nums1, int m, int[] nums2, int n) {
        int nums1EndIndex = m - 1;
        int nums2EndIndex = n - 1;
        int mergedEndIndex = nums1.length - 1;

        while (nums2EndIndex >= 0) {
            if (nums1EndIndex >= 0 && nums1[nums1EndIndex] > nums2[nums2EndIndex]) {
                nums1[mergedEndIndex--] = nums1[nums1EndIndex--];
            } else {
                nums1[mergedEndIndex--] = nums2[nums2EndIndex--];
            }
        }
    }
}
