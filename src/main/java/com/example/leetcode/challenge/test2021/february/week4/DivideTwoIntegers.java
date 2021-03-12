package com.example.leetcode.challenge.test2021.february.week4;


/**
 * Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.
 *
 * Return the quotient after dividing dividend by divisor.
 *
 * The integer division should truncate toward zero, which means losing its fractional part. For example, truncate(8.345) = 8 and truncate(-2.7335) = -2.
 *
 * Note:
 *
 * Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For this problem, assume that your function returns 231 − 1 when the division result overflows.
 *
 *
 * Example 1:
 *
 * Input: dividend = 10, divisor = 3
 * Output: 3
 * Explanation: 10/3 = truncate(3.33333..) = 3.
 * Example 2:
 *
 * Input: dividend = 7, divisor = -3
 * Output: -2
 * Explanation: 7/-3 = truncate(-2.33333..) = -2.
 * Example 3:
 *
 * Input: dividend = 0, divisor = 1
 * Output: 0
 * Example 4:
 *
 * Input: dividend = 1, divisor = 1
 * Output: 1
 */
public class DivideTwoIntegers {
    public static void main(String[] args) {

    }

    public int divide(int A, int B) {
        if (Integer.MIN_VALUE == A && B == -1) {
            return Integer.MAX_VALUE;
        }

        int a = Math.abs(A);
        int b = Math.abs(B);
        int res = 0;

        while (a - b >= 0) {
            int tmp = b;
            int cnt = 1;
            while (a - (tmp << 1) >= 0) {
                tmp <<= 1;
                cnt <<= 1;
            }
            a -= tmp;
            res += cnt;
        }

        return (A > 0) == (B > 0) ? res : -res;
    }

    public int divideV0(int dividend, int divisor) {
        if(divisor == 0)
        {
            return Integer.MAX_VALUE;
        }
        boolean isNeg = (dividend^divisor)>>>31 == 1;
        int res = 0;
        if(dividend == Integer.MIN_VALUE)
        {
            dividend += Math.abs(divisor);
            if(divisor == -1)
            {
                return Integer.MAX_VALUE;
            }
            res++;
        }
        if(divisor == Integer.MIN_VALUE)
        {
            return res;
        }
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        int digit = 0;
        while(divisor <= (dividend>>1))
        {
            divisor <<= 1;
            digit++;
        }
        while(digit>=0)
        {
            if(dividend>=divisor)
            {
                res += 1<<digit;
                dividend -= divisor;
            }
            divisor >>= 1;
            digit--;
        }
        return isNeg?-res:res;
    }
}
