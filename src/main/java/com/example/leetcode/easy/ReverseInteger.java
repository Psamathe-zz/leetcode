package com.example.leetcode.easy;

import java.util.Stack;

/**
 * Given a 32-bit signed integer, reverse digits of an integer.
 *
 * Example 1:
 *
 * Input: 123
 * Output: 321
 * Example 2:
 *
 * Input: -123
 * Output: -321
 * Example 3:
 *
 * Input: 120
 * Output: 21
 * Note:
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 */
public class ReverseInteger {
    public static void main(String[] args) {
        int x = -2147483648;
        ReverseInteger reverseInteger = new ReverseInteger();
        int result = reverseInteger.reverse(x);
        System.out.println(result);
    }
    public int reverse(int x) {
        int  maxDigit = Integer.MAX_VALUE % 10;
        int sign = 1;
        if(x < 0){
            sign = -1;
            maxDigit ++;
        }

        int result = 0;
        while (x * sign > 0){
            if(result * sign > Integer.MAX_VALUE / 10)
                return 0;
            else if (result * sign  == Integer.MAX_VALUE / 10 && x  > maxDigit)
                return 0;
            result = result * 10 + x  * sign % 10 * sign;
            x = x/10;
        }
        return result;
    }

    /**
     * less memory
     * @param x
     * @return
     */
    public int reverseV1(int x) {
        long result = 0;
        while (x != 0) {
            result = result*10 + x%10;
            x /= 10;
        }
        if (result <= Integer.MAX_VALUE && result >= Integer.MIN_VALUE) {
            return (int) result;
        }
        return 0;
    }
}
