package com.example.leetcode.challenge.August.week1;

/**
 * Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
 *
 * Example 1:
 *
 * Input: 16
 * Output: true
 * Example 2:
 *
 * Input: 5
 * Output: false
 * Follow up: Could you solve it without loops/recursion?
 */
public class PowerOfFour {
    public static void main(String[] args) {

    }

    /**
     * https://www.cnblogs.com/grandyang/p/5403783.html
     * @param num
     * @return
     */
    public boolean isPowerOfFour(int num) {
        while (num != 0 && num % 4 == 0)
            num /= 4;
        return num == 1;
    }

}
