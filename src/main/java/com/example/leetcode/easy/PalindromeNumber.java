package com.example.leetcode.easy;

/**
 * Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
 *
 * Example 1:
 *
 * Input: 121
 * Output: true
 * Example 2:
 *
 * Input: -121
 * Output: false
 * Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
 * Example 3:
 *
 * Input: 10
 * Output: false
 * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 */
public class PalindromeNumber {
    public static void main(String[] args) {
        PalindromeNumber palindromeNumber = new PalindromeNumber();
        boolean result = palindromeNumber.isPalindrome(121);
        System.out.println(result);
    }

    public boolean isPalindrome(int x) {
        if(x<0)
            return false;
        else if(x == 0)
            return true;
        else {
            String input = String.valueOf(x);
            return input.equals(new StringBuilder(input).reverse().toString());
        }
    }

    public boolean isPalindromeV1(int x) {
        int copy  = x;
        int temp = 0;
        while(x>0){
            temp *= 10;
            temp += x%10;
            x /= 10;
        }
        return temp == copy;
    }
}
