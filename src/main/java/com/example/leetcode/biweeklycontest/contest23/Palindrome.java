package com.example.leetcode.biweeklycontest.contest23;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * 5362. Construct K Palindrome Strings
 * Given a string s and an integer k. You should construct k non-empty palindrome strings using all the characters in s.
 *
 * Return True if you can use all the characters in s to construct k palindrome strings or False otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "annabelle", k = 2
 * Output: true
 * Explanation: You can construct two palindromes using all characters in s.
 * Some possible constructions "anna" + "elble", "anbna" + "elle", "anellena" + "b"
 * Example 2:
 *
 * Input: s = "leetcode", k = 3
 * Output: false
 * Explanation: It is impossible to construct 3 palindromes using all the characters of s.
 * Example 3:
 *
 * Input: s = "true", k = 4
 * Output: true
 * Explanation: The only possible solution is to put each character in a separate string.
 * Example 4:
 *
 * Input: s = "yzyzyzyzyzyzyzy", k = 2
 * Output: true
 * Explanation: Simply you can put all z's in one string and all y's in the other string. Both strings will be palindrome.
 * Example 5:
 *
 * Input: s = "cr", k = 7
 * Output: false
 * Explanation: We don't have enough characters in s to construct 7 palindromes.
 */
public class Palindrome {

    public static void main(String[] args) {

        Palindrome palindrome = new Palindrome();
        boolean result = palindrome.canConstructV2("leetcode",3);
        System.out.println(result);
    }
    public boolean canConstruct(String s, int k) {
        int length = s.length();
        Map<String,Integer> integerMap = new HashMap<>();
        if( length < k)
            return false;

        if( length == k)
            return true;

        Arrays.stream(s.split("")).forEach(e-> {
            if( integerMap.containsKey(e) ){
                integerMap.put(e,integerMap.get(e) + 1);
            } else {
                integerMap.put(e,1);
            }
        });
        int min = 0;
        for(int value : integerMap.values()){
            if(value%2 == 1)
                min++;
        }

        return k >= min && k<= length;

    }

    /**
     *  faster solution
     * @param s
     * @param k
     * @return
     */

    public boolean canConstructV2(String s, int k) {
        if(s.length() < k) return false;
        int [] chars = new int [26];
        for(char ch : s.toCharArray()) ++chars[ch - 'a'];
        int n_odds = 0;
        for(int i = 0; i < 26; ++i) n_odds += chars[i] % 2;
        return n_odds <= k;
    }



}
