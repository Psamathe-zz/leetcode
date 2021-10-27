package com.example.leetcode.challenge.test2021.september.week2;

/**
 * Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.
 *
 * You can use each character in text at most once. Return the maximum number of instances that can be formed.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: text = "nlaebolko"
 * Output: 1
 * Example 2:
 *
 *
 *
 * Input: text = "loonbalxballpoon"
 * Output: 2
 * Example 3:
 *
 * Input: text = "leetcode"
 * Output: 0
 */
public class MaximumNumberBalloons {
    public static void main(String[] args) {

    }

    public int maxNumberOfBalloons(String text) {
        int countB = 0;
        int countA = 0;
        int countL = 0;
        int countO = 0;
        int countN = 0;

        for(char c: text.toCharArray()){
            switch (c){
                case 'a': countA++; break;
                case 'b': countB++; break;
                case 'l': countL++; break;
                case 'o': countO++; break;
                case 'n': countN++; break;
            }
        }
        return  Math.min(Math.min(Math.min(countA, countB),Math.min(countL/2, countO/2)), countN);
    }
}
