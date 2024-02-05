package com.example.leetcode.weeklycontest.old.test213;


import java.util.Arrays;

/**
 * Given an integer n, return the number of strings of length n that consist only of vowels (a, e, i, o, u) and are lexicographically sorted.
 *
 * A string s is lexicographically sorted if for all valid i, s[i] is the same as or comes before s[i+1] in the alphabet.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: 5
 * Explanation: The 5 sorted strings that consist of vowels only are ["a","e","i","o","u"].
 * Example 2:
 *
 * Input: n = 2
 * Output: 15
 * Explanation: The 15 sorted strings that consist of vowels only are
 * ["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"].
 * Note that "ea" is not a valid string since 'e' comes after 'a' in the alphabet.
 * Example 3:
 *
 * Input: n = 33
 * Output: 66045
 *
 *
 * Constraints:
 *
 * 1 <= n <= 50
 */
public class CountSortedVowel {
    public static void main(String[] args) {
        CountSortedVowel countSortedVowel = new CountSortedVowel();
        int res = countSortedVowel.countVowelStrings(33);
        System.out.println(res);
    }

    public int countVowelStrings(int n) {
        int[] count = new int[5];
        Arrays.fill(count,1);
        while (n > 1){
            for (int i = 0; i < 5; i++) {
                for (int j = i + 1; j < 5; j++) {
                    count[i] += count[j];
                }
            }
            n--;
        }
        int res = 0;
        for (int i = 0; i < 5; i++) {
            res += count[i];
        }
        return res;
    }

    public int countVowelStringsV1(int n) {
        int[][] dp = new int[n][5];
        for (int i = 0; i < 5; i++) {
            dp[n-1][i] = 5-i;
        }
        for (int i = n-2; i >= 0; i--) {
            for (int j = 0; j < 5; j++) {
                for (int k = j; k < 5; k++) {
                    dp[i][j] += dp[i+1][k];
                }
            }
        }
        return dp[0][0];
    }
}
