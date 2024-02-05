package com.example.leetcode.weeklycontest.old.test190;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s and an integer k.
 *
 * Return the maximum number of vowel letters in any substring of s with length k.
 *
 * Vowel letters in English are (a, e, i, o, u).
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abciiidef", k = 3
 * Output: 3
 * Explanation: The substring "iii" contains 3 vowel letters.
 * Example 2:
 *
 * Input: s = "aeiou", k = 2
 * Output: 2
 * Explanation: Any substring of length 2 contains 2 vowels.
 * Example 3:
 *
 * Input: s = "leetcode", k = 3
 * Output: 2
 * Explanation: "lee", "eet" and "ode" contain 2 vowels.
 * Example 4:
 *
 * Input: s = "rhythms", k = 4
 * Output: 0
 * Explanation: We can see that s doesn't have any vowel letters.
 * Example 5:
 *
 * Input: s = "tryhard", k = 4
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s consists of lowercase English letters.
 * 1 <= k <= s.length
 * "weallloveyou"
 * 7
 *
 * "pdzndkhhoujpqyex"
 * 5
 */
public class MaximumNumberVowels {
    public static void main(String[] args) {
        String s = "pdzndkhhoujpqyex";
        int k = 5;
        MaximumNumberVowels maximumNumberVowels = new MaximumNumberVowels();
        int result = maximumNumberVowels.maxVowels(s,k);
        System.out.println(result);
    }
    public int maxVowels(String s, int k) {
        int temp = 0;
        int max = 0;
        List<Character> list = new ArrayList<>();
        list.add('a');
        list.add('e');
        list.add('i');
        list.add('o');
        list.add('u');
        char[] chars = s.toCharArray();

        for (int i = 0; i < Math.min(chars.length,k); i++) {
            if(list.contains(chars[i])){
                temp++;
            }
        }
        max=temp;
        if(max == k)
            return max;
        for (int i = k;i < chars.length;i++){
            if(list.contains(chars[i-k])){
                temp--;
            }
            if(list.contains(chars[i])){
                temp++;
            }
            if (max < temp){
                max = temp;
            }

            if(max == k)
                return max;
        }
        return max;
    }

}
