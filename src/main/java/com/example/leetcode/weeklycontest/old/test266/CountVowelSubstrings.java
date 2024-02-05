package com.example.leetcode.weeklycontest.old.test266;


import java.util.HashSet;

/**
 * A substring is a contiguous (non-empty) sequence of characters within a string.
 *
 * A vowel substring is a substring that only consists of vowels ('a', 'e', 'i', 'o', and 'u') and has all five vowels present in it.
 *
 * Given a string word, return the number of vowel substrings in word.
 *
 *
 *
 * Example 1:
 *
 * Input: word = "aeiouu"
 * Output: 2
 * Explanation: The vowel substrings of word are as follows (underlined):
 * - "aeiouu"
 * - "aeiouu"
 * Example 2:
 *
 * Input: word = "unicornarihan"
 * Output: 0
 * Explanation: Not all 5 vowels are present, so there are no vowel substrings.
 * Example 3:
 *
 * Input: word = "cuaieuouac"
 * Output: 7
 * Explanation: The vowel substrings of word are as follows (underlined):
 * - "cuaieuouac"
 * - "cuaieuouac"
 * - "cuaieuouac"
 * - "cuaieuouac"
 * - "cuaieuouac"
 * - "cuaieuouac"
 * - "cuaieuouac"
 * Example 4:
 *
 * Input: word = "bbaeixoubb"
 * Output: 0
 * Explanation: The only substrings that contain all five vowels also contain consonants, so there are no vowel substrings.
 *
 */
public class CountVowelSubstrings {
    public static void main(String[] args) {
        CountVowelSubstrings countVowelSubstrings = new CountVowelSubstrings();
        countVowelSubstrings.countVowelSubstrings("cuaieuouac");
    }

    public int countVowelSubstrings(String word) {
        int count = 0;

        // 遍历一遍字符串
        for (int i = 0; i < word.length()-1; i++) {
            // 字符串开头要是元音字母开头，否则跳过
            char temp = word.charAt(i);
            if (temp != 'a' && temp != 'e' && temp != 'i' && temp != 'o' && temp != 'u') {
                continue;
            }
            // 使用哈希表存储元音字母
            HashSet<String> set = new HashSet<>();
            for (int j = i; j < word.length(); j++) {
                char c = word.charAt(j);
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                    set.add(c+"");
                    // 元音字母个数达到5个，count就加1
                    if (set.size() == 5) {
                        count++;
                    }
                } else {
                    // 如果出现了非元音字母，就结束当前字串的遍历
                    break;
                }
            }
        }
        return count;
    }
}
