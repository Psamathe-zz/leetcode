package com.example.leetcode.challenge.July.week3;

/**
 * Given two binary strings, return their sum (also a binary string).
 *
 * The input strings are both non-empty and contains only characters 1 or 0.
 *
 * Example 1:
 *
 * Input: a = "11", b = "1"
 * Output: "100"
 * Example 2:
 *
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 *
 *
 * Constraints:
 *
 * Each string consists only of '0' or '1' characters.
 * 1 <= a.length, b.length <= 10^4
 * Each string is either "0" or doesn't contain any leading zero.
 */
public class AddBinary {
    public static void main(String[] args) {

    }

    public String addBinary(String a, String b) {
        int indexA = a.length() - 1;
        int indexB = b.length() - 1;
        int aValue;
        int bValue;
        int next = 0;
        StringBuffer stringBuffer = new StringBuffer();
        while (indexA >= 0 || indexB >= 0){
            if(indexA>=0){
                aValue = a.charAt(indexA) - '0';
                indexA--;
            } else {
                aValue = 0;
            }
            if(indexB>=0){
                bValue = b.charAt(indexB) - '0';
                indexB--;
            } else {
                bValue = 0;
            }
            int value = (aValue + bValue + next) % 2;
            next = (aValue + bValue + next) / 2;
            stringBuffer.append(value);
        }
        if(next == 1)
            stringBuffer.append(next);
        return stringBuffer.reverse().toString();
    }


    public String addBinaryV2(String a, String b) {
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
