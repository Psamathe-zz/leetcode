package com.example.leetcode.challenge.test2021.september.week3;

/**
 * Given an integer array arr, return the length of a maximum size turbulent subarray of arr.
 *
 * A subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.
 *
 * More formally, a subarray [arr[i], arr[i + 1], ..., arr[j]] of arr is said to be turbulent if and only if:
 *
 * For i <= k < j:
 * arr[k] > arr[k + 1] when k is odd, and
 * arr[k] < arr[k + 1] when k is even.
 * Or, for i <= k < j:
 * arr[k] > arr[k + 1] when k is even, and
 * arr[k] < arr[k + 1] when k is odd.
 *
 *
 * Example 1:
 *
 * Input: arr = [9,4,2,10,7,8,8,1,9]
 * Output: 5
 * Explanation: arr[1] > arr[2] < arr[3] > arr[4] < arr[5]
 * Example 2:
 *
 * Input: arr = [4,8,12,16]
 * Output: 2
 * Example 3:
 *
 * Input: arr = [100]
 * Output: 1
 */
public class LongestTurbulentSubarray {
    public static void main(String[] args) {

    }

    
    public int maxTurbulenceSize(int[] arr) {
        int n = arr.length;
        int ret = 1;
        int left = 0, right = 0;

        while (right < n - 1) {
            if (left == right) {
                if (arr[left] == arr[left + 1]) {
                    left++;
                }
                right++;
            } else {
                if (arr[right - 1] < arr[right] && arr[right] > arr[right + 1]) {
                    right++;
                } else if (arr[right - 1] > arr[right] && arr[right] < arr[right + 1]) {
                    right++;
                } else {
                    left = right;
                }
            }
            ret = Math.max(ret, right - left + 1);
        }
        return ret;
    }
}
