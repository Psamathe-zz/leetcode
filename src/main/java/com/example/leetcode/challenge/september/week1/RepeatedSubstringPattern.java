package com.example.leetcode.challenge.september.week1;

/**
 * Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.
 *
 *
 *
 * Example 1:
 *
 * Input: "abab"
 * Output: True
 * Explanation: It's the substring "ab" twice.
 * Example 2:
 *
 * Input: "aba"
 * Output: False
 * Example 3:
 *
 * Input: "abcabcabcabc"
 * Output: True
 * Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
 */
public class RepeatedSubstringPattern {
    public static void main(String[] args) {

    }

    public boolean repeatedSubstringPattern(String s) {
        int length = s.length();
        for (int i = length / 2; i >= 1 ; i--) {
            if (length % i == 0) {
                int c = length / i;
                StringBuilder stringBuilder = new StringBuilder();
                for (int j = 0; j < c; ++j) {
                    stringBuilder.append(s.substring(0, i));
                }
                if (stringBuilder.toString().equals(s))
                    return true;
            }
        }

        return false;
    }

    /**
     * faster solution
     * @param s
     * @return
     */
    public boolean repeatedSubstringPatternV1(String s) {
        int len = s.length();

        for(int i = len / 2; i >= 1; i--){
            if(len % i == 0){
                //because the substring must be multiple so that we can add it 'x' times to get the result string.
                //Ex result = "abc" substring = "ab"  then result.length() / substring.length() is not divisible so we cant build result
                //from substring
                //Ex result = "abc" and substring = "a" then it is possible
                String substring  = s.substring(0,i);
                int times =  len / i;//calculates how many times we have to repeat substring
                //if len / i == 0 means it should be only 1 time but repeat creates a empty string for value 0 so we set it to 1.

                if(substring.repeat(times).equals(s)){
                    return true;
                }

            }
        }
        return false;
    }
}
