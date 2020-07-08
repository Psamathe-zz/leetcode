package com.example.leetcode.easy;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array of distinct integers arr, find all pairs of elements with the minimum absolute difference of any two elements.
 *
 * Return a list of pairs in ascending order(with respect to pairs), each pair [a, b] follows
 *
 * a, b are from arr
 * a < b
 * b - a equals to the minimum absolute difference of any two elements in arr
 *
 *
 * Example 1:
 *
 * Input: arr = [4,2,1,3]
 * Output: [[1,2],[2,3],[3,4]]
 * Explanation: The minimum absolute difference is 1. List all pairs with difference equal to 1 in ascending order.
 * Example 2:
 *
 * Input: arr = [1,3,6,10,15]
 * Output: [[1,3]]
 * Example 3:
 *
 * Input: arr = [3,8,-10,23,19,-4,-14,27]
 * Output: [[-14,-10],[19,23],[23,27]]
 *
 *
 * Constraints:
 *
 * 2 <= arr.length <= 10^5
 * -10^6 <= arr[i] <= 10^6
 */
public class MinimumAbsoluteDifference {
    public static void main(String[] args) {
        int[] arr = new int[]{4,2,1,3};
        MinimumAbsoluteDifference minimumAbsoluteDifference = new MinimumAbsoluteDifference();
        minimumAbsoluteDifference.minimumAbsDifference(arr);
    }

    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> res = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        int diff;
        List<Integer> list;
        for (int i = 0; i < arr.length - 1; i++) {
            diff = arr[i + 1] - arr[i];
            if(diff < min){
                res.clear();
                min = diff;
            }
            if(diff <= min){
                res.add(Arrays.asList(arr[i],arr[i+1]));
            }
        }
        return res;
    }
}
