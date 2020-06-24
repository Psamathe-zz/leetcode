package com.example.leetcode.medium;


/**
 * Implement atoi which converts a string to an integer.
 *
 * The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.
 *
 * The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.
 *
 * If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
 *
 * If no valid conversion could be performed, a zero value is returned.
 *
 * Note:
 *
 * Only the space character ' ' is considered as whitespace character.
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.
 * Example 1:
 *
 * Input: "42"
 * Output: 42
 * Example 2:
 *
 * Input: "   -42"
 * Output: -42
 * Explanation: The first non-whitespace character is '-', which is the minus sign.
 *              Then take as many numerical digits as possible, which gets 42.
 * Example 3:
 *
 * Input: "4193 with words"
 * Output: 4193
 * Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
 * Example 4:
 *
 * Input: "words and 987"
 * Output: 0
 * Explanation: The first non-whitespace character is 'w', which is not a numerical
 *              digit or a +/- sign. Therefore no valid conversion could be performed.
 * Example 5:
 *
 * Input: "-91283472332"
 * Output: -2147483648
 * Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
 *              Thefore INT_MIN (−231) is returned.
 * Accepted
 * 556,744
 * Submissions
 * 3,643,578
 */
public class StringToInteger {
    public static void main(String[] args) {
        String str = "4193 with words";
        StringToInteger stringToInteger = new StringToInteger();
        stringToInteger.myAtoi(str);
    }

    public int myAtoi(String str) {
        str = str.trim();
        char[] chars = str.toCharArray();
        if(chars.length == 0){
            return 0;
        } else if(chars[0]== '+'){
            return helper(chars,1,false);
        } else if(chars[0]== '-'){
           return helper(chars,1,true);
        } else if (chars[0] >= '0' && chars[0] <= '9'){
            return helper(chars,0,false);
        } else {
            return 0;
        }

    }

    public int helper(char[] chars , int start, boolean isNegative){
        long result = 0;
        for (int i = start; i < chars.length; i++) {
            if(chars[i] < '0' || chars[i] > '9')
                break;
            result = result * 10 + chars[i] - '0';
            if(result > Integer.MAX_VALUE && !isNegative)
                return Integer.MAX_VALUE;
            if(-result < Integer.MIN_VALUE && isNegative)
                return Integer.MIN_VALUE;
        }
        return isNegative ? -(int)result:(int)result;
    }


    public int myAtoiV1(String str) {
        boolean converted = false;
        boolean negative = false;
        boolean positive = false;
        int answer = 0;

        for (char c : str.toCharArray()) {

            if (!converted && c == ' ') {
                continue;
            }

            if (c == '-') {
                if (converted || negative || positive) {
                    return answer;
                }

                negative = true;
                converted = true;
                continue;
            }

            if (c == '+' && !positive && !negative) {
                positive = true;
                converted = true;
                continue;
            }

            if (c < '0' || c > '9') {
                return answer;
            }

            converted = true;
            int digit = c - '0';
            if (answer == 0) {
                answer = negative ? -digit : digit;
                continue;
            }

            int temp = answer;
            if (negative) {
                answer = answer * 10 - digit;
            }
            else {
                answer = answer * 10 + digit;
            }

            if (answer / 10 != temp) {
                return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
        }

        return answer;
    }
}
