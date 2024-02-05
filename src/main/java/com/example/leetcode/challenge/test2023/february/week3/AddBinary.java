package com.example.leetcode.challenge.test2023.february.week3;

/**
 * Given two binary strings a and b, return their sum as a binary string.
 *
 *
 *
 * Example 1:
 *
 * Input: a = "11", b = "1"
 * Output: "100"
 * Example 2:
 *
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 */
public class AddBinary {
    public static void main(String[] args) {

    }

    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder("");

        int carry = 0;
        int i = a.length()-1;
        int j = b.length()-1;

        while (i >= 0 || j >= 0) {
            int count = 0;
            if (i >= 0 && a.charAt(i--) == '1') count++;
            if (j >= 0 && b.charAt(j--) == '1') count++;
            if (carry == 1) count++;
            sb.append(count % 2 == 0 ? '0' : '1');
            carry = count < 2 ? 0 : 1;
        }
        if (carry == 1) sb.append('1');
        return sb.reverse().toString();

    }
}
