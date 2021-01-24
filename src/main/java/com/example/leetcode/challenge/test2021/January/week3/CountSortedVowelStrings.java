package com.example.leetcode.challenge.test2021.January.week3;


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
public class CountSortedVowelStrings {
    public static void main(String[] args) {
        CountSortedVowelStrings countSortedVowelStrings = new CountSortedVowelStrings();
        int res = countSortedVowelStrings.countVowelStrings(3);
        System.out.println(res);
    }

    int[][] map;
    public int countVowelStrings(int n) {
        map = new int[n][5];
        int res =  count(n - 1, 0) +  count(n - 1, 1) +  count(n - 1, 2) +  count(n - 1, 3) +  count(n - 1, 4);
        return res;
    }

    public int count(int n, int c){
        if(map[n][c] > 0)
            return map[n][c];
        if(n == 0) {
            map[n][c] = 1;
            return 1;
        }
        if(n == 1 && c == 4) {
            map[n][c] = 1;
            return 1;
        }
        int res = count(n-1,4);
        if(c <= 3)
            res += count(n-1,3);
        if(c <= 2)
            res += count(n-1,2);
        if(c <= 1)
            res += count(n-1,1);
        if(c <= 0)
            res += count(n-1,0);
        map[n][c] = res;
        return res;
    }

    /**
     * faster solution
     * @param n
     * @return
     */
    public int countVowelStringsV1(int n) {
        int[][] dp = new int[n + 1][6];
        for (int vowels = 1; vowels <= 5; vowels++)
            dp[1][vowels] = vowels;
        for (int nValue = 2; nValue <= n; nValue++) {
            dp[nValue][1] = 1;
            for (int vowels = 2; vowels <= 5; vowels++) {
                dp[nValue][vowels] = dp[nValue][vowels - 1] + dp[nValue - 1][vowels];
            }
        }
        return dp[n][5];
    }
}
