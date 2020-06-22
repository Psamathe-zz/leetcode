package com.example.leetcode.hard;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * You may assume nums1 and nums2 cannot be both empty.
 *
 * Example 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * The median is 2.0
 * Example 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * The median is (2 + 3)/2 = 2.5
 */
public class MedianTwoSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1,3};
        int[] nums2 = new int[]{2};
        MedianTwoSortedArrays medianTwoSortedArrays = new MedianTwoSortedArrays();
        double result = medianTwoSortedArrays.findMedianSortedArrays(nums1,nums2);
        System.out.println(result);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        List<Integer> list1 = Arrays.stream(nums1).boxed().collect(Collectors.toList());
        List<Integer> list2 = Arrays.stream(nums2).boxed().collect(Collectors.toList());
        list1.addAll(list2);
        list1 = list1.stream().sorted().collect(Collectors.toList());
        if(list1.size() % 2 == 0){
            return (double)(list1.get(list1.size() / 2 - 1) + list1.get(list1.size() / 2) )/ 2;
        } else {
            return (double)list1.get(list1.size() / 2 ) ;
        }
    }
    public double findMedianSortedArraysV0(int[] nums1, int[] nums2) {

        double result  = 0;
        int[] arrayInt = new int[65535*2];
        HashSet<Integer> integerHashSet = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            integerHashSet.add(nums1[i]);
            arrayInt[65535+nums1[i]] = 1;
        }
        for (int i = 0; i < nums2.length; i++) {
            integerHashSet.add(nums2[i]);
            arrayInt[65535+nums2[i]] = 1;
        }

        int index = 0;
        int half = integerHashSet.size()/2;
        if(integerHashSet.size()%2 == 0){
            for (int i = 0; i < arrayInt.length; i++) {
                if(arrayInt[i] == 1){
                    if(index+1 == half || index == half){
                        result += i-65535;
                        if(index == half)
                            break;
                    }
                    index++;
                }
            }
            return result/2;
        } else {
            for (int i = 0; i < arrayInt.length; i++) {
                if(arrayInt[i] == 1){
                    if(index == half){
                        result = i -65535;
                        break;
                    }
                    index++;
                }
            }
            return result;
        }
    }
}
