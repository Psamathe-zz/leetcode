package com.example.leetcode.challenge.test2023.mars.week2;

/**
 * Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
 *
 * Return the kth positive integer that is missing from this array.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [2,3,4,7,11], k = 5
 * Output: 9
 * Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.
 * Example 2:
 *
 * Input: arr = [1,2,3,4], k = 2
 * Output: 6
 * Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
 *
 */
public class KthMissingPositiveNumber {
    public static void main(String[] args) {

    }

    public int findKthPositive(int[] arr, int k) {
        int index = 0;
        int cur = 1;
        while (index < arr.length) {
            if(cur == arr[index]) {
                index++;
            } else {
                k--;
                if(k == 0)
                    return cur;
            }
            cur++;
        }
        return cur + k - 1;

    }
}
