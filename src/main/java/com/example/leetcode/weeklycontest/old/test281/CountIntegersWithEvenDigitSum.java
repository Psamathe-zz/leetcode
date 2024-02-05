package com.example.leetcode.weeklycontest.old.test281;

/**
 * Given a positive integer num, return the number of positive integers less than or equal to num whose digit sums are even.
 *
 * The digit sum of a positive integer is the sum of all its digits.
 *
 *
 *
 * Example 1:
 *
 * Input: num = 4
 * Output: 2
 * Explanation:
 * The only integers less than or equal to 4 whose digit sums are even are 2 and 4.
 * Example 2:
 *
 * Input: num = 30
 * Output: 14
 * Explanation:
 * The 14 integers less than or equal to 30 whose digit sums are even are
 * 2, 4, 6, 8, 11, 13, 15, 17, 19, 20, 22, 24, 26, and 28.
 */
public class CountIntegersWithEvenDigitSum {
    public static void main(String[] args) {
        CountIntegersWithEvenDigitSum countIntegersWithEvenDigitSum = new CountIntegersWithEvenDigitSum();
        countIntegersWithEvenDigitSum.countEven(100);
    }

    public int countEven(int num) {
        int res = 0;
        for (int i = 2;i <= num;i++){
            if (check(i))
                res++;
        }
        return res;
    }

    public boolean check(int num){
        int checkNum = 0;
        while (num > 0){
            checkNum = checkNum + (num % 10);
            num /= 10;
        }
        return checkNum % 2 == 0 ;
    }
}
