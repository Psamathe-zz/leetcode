package com.example.leetcode.challenge.test2021.november;

/**
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 *
 * Given two integers x and y, return the Hamming distance between them.
 *
 *
 *
 * Example 1:
 *
 * Input: x = 1, y = 4
 * Output: 2
 * Explanation:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 * The above arrows point to positions where the corresponding bits are different.
 * Example 2:
 *
 * Input: x = 3, y = 1
 * Output: 1
 */
public class HammingDistance {
    public static void main(String[] args) {

    }

    public int hammingDistance(int x, int y) {
        int result = 0;
        int diff = x^y;
        result += diff & 1;
        while((diff = diff >> 1) > 0){
            result += diff & 1;
        }
        return result;
    }
}
