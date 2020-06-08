package com.example.leetcode.easy;

/**
 * Given an integer n. No-Zero integer is a positive integer which doesn't contain any 0 in its decimal representation.
 *
 * Return a list of two integers [A, B] where:
 *
 * A and B are No-Zero integers.
 * A + B = n
 * It's guarateed that there is at least one valid solution. If there are many valid solutions you can return any of them.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: [1,1]
 * Explanation: A = 1, B = 1. A + B = n and both A and B don't contain any 0 in their decimal representation.
 * Example 2:
 *
 * Input: n = 11
 * Output: [2,9]
 * Example 3:
 *
 * Input: n = 10000
 * Output: [1,9999]
 * Example 4:
 *
 * Input: n = 69
 * Output: [1,68]
 * Example 5:
 *
 * Input: n = 1010
 * Output: [11,999]
 *
 *
 * Constraints:
 *
 * 2 <= n <= 10^4
 */
public class ConvertIntegerToSumNoZeroIntegers {
    public static void main(String[] args) {
        ConvertIntegerToSumNoZeroIntegers convertIntegerToSumNoZeroIntegers = new ConvertIntegerToSumNoZeroIntegers();
        int[] result = convertIntegerToSumNoZeroIntegers.getNoZeroIntegers(1001);
        result.toString();
    }
    public int[] getNoZeroIntegers(int n) {
        int a = 0;
        int b = 0;
        int rest = n % 10;

        int power = 1;

        while (n > 1){
            n = n / 10;
            if(rest > 1){
                a += power;
                b += (rest - 1) * power;
            } else {
                a += (rest + 1) * power;
                b += 9 * power;
                n -= 1;
            }
            rest = n % 10;
            power *= 10;
        }
        if(n == 1)
            a += power;
        return new int[]{a,b};
    }

}
