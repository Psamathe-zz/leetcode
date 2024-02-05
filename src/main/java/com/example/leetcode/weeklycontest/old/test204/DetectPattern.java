package com.example.leetcode.weeklycontest.old.test204;

/**
 * Given an array of positive integers arr,  find a pattern of length m that is repeated k or more times.
 *
 * A pattern is a subarray (consecutive sub-sequence) that consists of one or more values, repeated multiple times consecutively without overlapping. A pattern is defined by its length and the number of repetitions.
 *
 * Return true if there exists a pattern of length m that is repeated k or more times, otherwise return false.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2,4,4,4,4], m = 1, k = 3
 * Output: true
 * Explanation: The pattern (4) of length 1 is repeated 4 consecutive times. Notice that pattern can be repeated k or more times but not less.
 * Example 2:
 *
 * Input: arr = [1,2,1,2,1,1,1,3], m = 2, k = 2
 * Output: true
 * Explanation: The pattern (1,2) of length 2 is repeated 2 consecutive times. Another valid pattern (2,1) is also repeated 2 times.
 * Example 3:
 *
 * Input: arr = [1,2,1,2,1,3], m = 2, k = 3
 * Output: false
 * Explanation: The pattern (1,2) is of length 2 but is repeated only 2 times. There is no pattern of length 2 that is repeated 3 or more times.
 * Example 4:
 *
 * Input: arr = [1,2,3,1,2], m = 2, k = 2
 * Output: false
 * Explanation: Notice that the pattern (1,2) exists twice but not consecutively, so it doesn't count.
 * Example 5:
 *
 * Input: arr = [2,2,2,2], m = 2, k = 3
 * Output: false
 * Explanation: The only pattern of length 2 is (2,2) however it's repeated only twice. Notice that we do not count overlapping repetitions.
 *
 *
 * Constraints:
 *
 * 2 <= arr.length <= 100
 * 1 <= arr[i] <= 100
 * 1 <= m <= 100
 * 2 <= k <= 100
 * [3,6,6,6,5,1,5,2,2,3,1,5,2,6,1,5,1,2,6,3,3,5,3,6,3,4]
 * 6
 * 2
 */
public class DetectPattern {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,4,4,4,4};
        int m  = 1;
        int k = 3;
        DetectPattern detectPattern = new DetectPattern();
        detectPattern.containsPattern(arr,m,k);
    }

    public boolean containsPattern(int[] arr, int m, int k) {
        int length = arr.length;
        if(length < m * k)
            return false;
        boolean isPattern;
        for (int i = 0; i <=  length - m * k ; i++) {
            isPattern = true;
            for (int j = i; j < i + m*k; j++) {
                if(arr[j] != arr[(j-i)%m + i]){
                    isPattern = false;
                    break;
                }
            }
            if(isPattern){
                return true;
            }
        }
        return false;
    }
}
