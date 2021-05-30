package com.example.leetcode.weeklycontest.test243;


/**
 * You are given a very large integer n, represented as a string,​​​​​​ and an integer digit x. The digits in n and the digit x are in the inclusive range [1, 9], and n may represent a negative number.
 *
 * You want to maximize n's numerical value by inserting x anywhere in the decimal representation of n​​​​​​. You cannot insert x to the left of the negative sign.
 *
 * For example, if n = 73 and x = 6, it would be best to insert it between 7 and 3, making n = 763.
 * If n = -55 and x = 2, it would be best to insert it before the first 5, making n = -255.
 * Return a string representing the maximum value of n​​​​​​ after the insertion.
 *
 *
 *
 * Example 1:
 *
 * Input: n = "99", x = 9
 * Output: "999"
 * Explanation: The result is the same regardless of where you insert 9.
 * Example 2:
 *
 * Input: n = "-13", x = 2
 * Output: "-123"
 * Explanation: You can make n one of {-213, -123, -132}, and the largest of those three is -123.
 * "-132"
 * 3
 *
 */
public class MaximumValue {
    public static void main(String[] args) {
        MaximumValue maximumValue = new MaximumValue();
        maximumValue.maxValue("-132",3);
    }

    public String maxValue(String n, int x) {
        char[] chars = n.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        boolean find = false;
        if(chars[0] == '-'){
            stringBuilder.append(chars[0]);
            for (int i = 1; i < chars.length; i++) {
                if(!find && (chars[i] - '0') > x){
                    stringBuilder.append(x);
                    find = true;
                }
                stringBuilder.append(chars[i]);
            }
        } else {
            for (int i = 0; i < chars.length; i++) {
                if(!find && (chars[i] - '0') < x){
                    stringBuilder.append(x);
                    find = true;
                }
                stringBuilder.append(chars[i]);
            }
        }
        if(!find)
            stringBuilder.append(x);
        return stringBuilder.toString();
    }
}
