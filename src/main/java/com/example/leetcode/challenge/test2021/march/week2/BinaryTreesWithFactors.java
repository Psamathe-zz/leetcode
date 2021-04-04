package com.example.leetcode.challenge.test2021.march.week2;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of unique integers, arr, where each integer arr[i] is strictly greater than 1.
 *
 * We make a binary tree using these integers, and each number may be used for any number of times. Each non-leaf node's value should be equal to the product of the values of its children.
 *
 * Return the number of binary trees we can make. The answer may be too large so return the answer modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [2,4]
 * Output: 3
 * Explanation: We can make these trees: [2], [4], [4, 2, 2]
 * Example 2:
 *
 * Input: arr = [2,4,5,10]
 * Output: 7
 * Explanation: We can make these trees: [2], [4], [5], [10], [4, 2, 2], [10, 2, 5], [10, 5, 2].
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 1000
 * 2 <= arr[i] <= 109
 */
public class BinaryTreesWithFactors {
    public static void main(String[] args) {

    }

    /**
     * http://shangdixinxi.com/detail-1609772.html
     * @param arr
     * @return
     */
    public int numFactoredBinaryTrees(int[] arr) {
        long sum = 0;
        Map<Integer, Long> map = new HashMap<>();
        Arrays.sort(arr);

        for (int i = 0; i < arr.length; i++) {
            long cnt = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] % arr[j] == 0 && map.containsKey(arr[i] / arr[j])) {
                    cnt += map.get(arr[j]) * map.get(arr[i] / arr[j]) % 1000000007;
                }
            }
            map.put(arr[i], cnt);
            sum = (sum + cnt) % 1000000007;
        }

        return (int) sum;
    }
}
