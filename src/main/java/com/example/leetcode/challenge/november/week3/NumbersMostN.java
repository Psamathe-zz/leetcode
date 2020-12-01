package com.example.leetcode.challenge.november.week3;

/**
 * Given an array of digits, you can write numbers using each digits[i] as many times as we want.  For example, if digits = ['1','3','5'], we may write numbers such as '13', '551', and '1351315'.
 *
 * Return the number of positive integers that can be generated that are less than or equal to a given integer n.
 *
 *
 *
 * Example 1:
 *
 * Input: digits = ["1","3","5","7"], n = 100
 * Output: 20
 * Explanation:
 * The 20 numbers that can be written are:
 * 1, 3, 5, 7, 11, 13, 15, 17, 31, 33, 35, 37, 51, 53, 55, 57, 71, 73, 75, 77.
 * Example 2:
 *
 * Input: digits = ["1","4","9"], n = 1000000000
 * Output: 29523
 * Explanation:
 * We can write 3 one digit numbers, 9 two digit numbers, 27 three digit numbers,
 * 81 four digit numbers, 243 five digit numbers, 729 six digit numbers,
 * 2187 seven digit numbers, 6561 eight digit numbers, and 19683 nine digit numbers.
 * In total, this is 29523 integers that can be written using the digits array.
 * Example 3:
 *
 * Input: digits = ["7"], n = 8
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= digits.length <= 9
 * digits[i].length == 1
 * digits[i] is a digit from '1' to '9'.
 * All the values in digits are unique.
 * 1 <= n <= 109
 */
public class NumbersMostN {
    public static void main(String[] args) {

    }

    /**
     * https://www.cnblogs.com/grandyang/p/11062193.html
     * @param digits
     * @param n
     * @return
     */
    public int atMostNGivenDigitSet(String[] digits, int n) {
        String str = String.valueOf(n);
        int res = 0;
        int digitsLength = digits.length;
        int len = str.length();
        for (int i = 1; i < len; ++i)
            res += Math.pow(digitsLength, i);
        for (int i = 0; i < len; ++i) {
            boolean hasSameNum = false;
            for (String digit : digits) {
                if (digit.charAt(0) < str.charAt(i))
                    res += Math.pow(digitsLength, len - 1 - i);
                else if (digit.charAt(0) == str.charAt(i))
                    hasSameNum = true;
            }
            if (!hasSameNum) return res;
        }
        return res + 1;
    }
}
