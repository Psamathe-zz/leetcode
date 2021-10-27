package com.example.leetcode.challenge.test2021.august.week4;

/**
 * Given a non-negative integer c, decide whether there're two integers a and b such that a2 + b2 = c.
 *
 *
 *
 * Example 1:
 *
 * Input: c = 5
 * Output: true
 * Explanation: 1 * 1 + 2 * 2 = 5
 * Example 2:
 *
 * Input: c = 3
 * Output: false
 * Example 3:
 *
 * Input: c = 4
 * Output: true
 * Example 4:
 *
 * Input: c = 2
 * Output: true
 * Example 5:
 *
 * Input: c = 1
 * Output: true
 */
public class SumOfSquareNumbers {
    public static void main(String[] args) {
        SumOfSquareNumbers sumOfSquareNumbers = new SumOfSquareNumbers();
        boolean res = sumOfSquareNumbers.judgeSquareSum(3);
        System.out.println(res);
    }

    public boolean judgeSquareSum(int c) {
        for (int i = (int)Math.sqrt(c); i >= 0; --i) {
            if (i * i == c) return true;
            int d = c - i * i, t = (int)Math.sqrt(d);
            if (t * t == d) return true;
        }
        return false;
    }

    /**
     * faster solution
     * @param c
     * @return
     */
    public boolean judgeSquareSumV1(int c) {
        int start = 0, end = (int)Math.sqrt(c);
        while(start<=end)
        {
            int sum =(start*start) + (end*end);
            if(sum < c)start++;
            else if(sum>c)end--;
            else return true;
        }
        return false;

    }
}
