package com.example.leetcode.challenge.test2022.january;

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
        AddBinary addBinary = new AddBinary();
        String res = addBinary.addBinary("11","1");
        System.out.println(res);
    }

    public String addBinary(String a, String b) {
        int len1 = a.length();
        int len2 = b.length();
        int index1 = len1 - 1;
        int index2 = len2 - 1;
        int val1;
        int val2;
        int cur = 0;
        StringBuilder stringBuilder = new StringBuilder();

        while (index1 >= 0 || index2 >= 0) {
            val1 = 0;
            val2 = 0;
            if(index1 >= 0) {
                val1 = a.charAt(index1) - '0';
                index1--;
            }

            if(index2 >= 0) {
                val2 = b.charAt(index2) - '0';
                index2--;
            }

            stringBuilder.insert(0, (cur + val1 + val2) % 2 );
            cur = (cur + val1 + val2) / 2;
        }
        if(cur == 1)
            stringBuilder.insert(0, cur);
        return stringBuilder.toString();
    }
}
