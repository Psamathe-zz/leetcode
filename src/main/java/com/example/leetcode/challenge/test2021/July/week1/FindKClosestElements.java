package com.example.leetcode.challenge.test2021.July.week1;


import java.util.*;

/**
 * Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.
 *
 * An integer a is closer to x than an integer b if:
 *
 * |a - x| < |b - x|, or
 * |a - x| == |b - x| and a < b
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2,3,4,5], k = 4, x = 3
 * Output: [1,2,3,4]
 * Example 2:
 *
 * Input: arr = [1,2,3,4,5], k = 4, x = -1
 * Output: [1,2,3,4]
 */
public class FindKClosestElements {
    public static void main(String[] args) {
        FindKClosestElements findKClosestElements = new FindKClosestElements();
        findKClosestElements.findClosestElements(new int[]{1,2,3,4,5}, 4, -1);
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        Queue<Integer> res = new LinkedList<>();
        for (int val : arr){
            if(res.size() < k || Math.abs(val - x) < Math.abs( res.peek() - x)) {
                res.offer(val);
                if (res.size() > k)
                    res.poll();
            } else if(Math.abs(val - x) > Math.abs( res.peek() - x) && res.size() == k ) {
                break;
            }
        }
        return new ArrayList(res);
    }

    /**
     * faster solution
     * @param arr
     * @param k
     * @param x
     * @return
     */
    public List<Integer> findClosestElementsV1(int[] arr, int k, int x) {
        // Initialize binary search bounds
        int left = 0;
        int right = arr.length - k;

        // Binary search against the criteria described
        while (left < right) {
            int mid = (left + right) / 2;
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // Create output in correct format
        List<Integer> result = new ArrayList<Integer>();
        for (int i = left; i < left + k; i++) {
            result.add(arr[i]);
        }

        return result;
    }
}
