package com.example.leetcode.easy;

import java.util.Arrays;

/**
 * You are given an array of strings words and a string chars.
 *
 * A string is good if it can be formed by characters from chars (each character can only be used once).
 *
 * Return the sum of lengths of all good strings in words.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["cat","bt","hat","tree"], chars = "atach"
 * Output: 6
 * Explanation:
 * The strings that can be formed are "cat" and "hat" so the answer is 3 + 3 = 6.
 * Example 2:
 *
 * Input: words = ["hello","world","leetcode"], chars = "welldonehoneyr"
 * Output: 10
 * Explanation:
 * The strings that can be formed are "hello" and "world" so the answer is 5 + 5 = 10.
 *
 *
 * Note:
 *
 * 1 <= words.length <= 1000
 * 1 <= words[i].length, chars.length <= 100
 * All strings contain lowercase English letters only.
 */
public class FindWordsThatCanBeFormedCharacters {
    public static void main(String[] args) {
        String[] words = new String[]{"cat","bt","hat","tree"};
        String chars = "atach";
        FindWordsThatCanBeFormedCharacters findWordsThatCanBeFormedCharacters = new FindWordsThatCanBeFormedCharacters();
        findWordsThatCanBeFormedCharacters.countCharacters(words,chars);
    }

    public int countCharacters(String[] words, String chars) {
        int result = 0;
        int[] count = new int[26];
        for (char c : chars.toCharArray()){
            count[c - 'a']++;
        }
        int[] temp;
        boolean isOk;
        for(String str : words){
            temp = Arrays.copyOf(count,26);
            isOk = true;
            for (char c : str.toCharArray()){
                temp[c - 'a']--;
                if(temp[c-'a'] < 0){
                    isOk = false;
                    break;
                }
            }
            if(isOk)
                result += str.length();
        }
        return result;
    }
}
