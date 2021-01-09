package com.example.leetcode.challenge.test2020.september.week2;

/**
 * You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses and hints to eventually derive the secret number.
 *
 * Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows.
 *
 * Please note that both secret number and friend's guess may contain duplicate digits.
 *
 * Example 1:
 *
 * Input: secret = "1807", guess = "7810"
 *
 * Output: "1A3B"
 *
 * Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.
 * Example 2:
 *
 * Input: secret = "1123", guess = "0111"
 *
 * Output: "1A1B"
 *
 * Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.
 * Note: You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.
 */
public class BullsCows {
    public static void main(String[] args) {

    }

    public String getHint(String secret, String guess) {
        StringBuilder stringBuilder = new StringBuilder();
        int length = secret.length();
        int bullCount = 0;
        int cowCount = 0;
        char[] chars1 = secret.toCharArray();
        char[] chars2 = guess.toCharArray();
        int[] bullCharCount = new int[10];
        int[] cowCharCount = new int[10];
        for (int i = 0; i < length; i++) {
            if(chars1[i] == chars2[i])
                bullCount++;
            else {
                bullCharCount[chars1[i] - '0']++;
                cowCharCount[chars2[i] - '0']++;
            }
        }
        stringBuilder.append(bullCount).append('A');
        for (int i = 0; i < 10; i++) {
            cowCount += Math.min(bullCharCount[i],cowCharCount[i]);
        }
        stringBuilder.append(cowCount).append('B');
        return stringBuilder.toString();
    }
}
